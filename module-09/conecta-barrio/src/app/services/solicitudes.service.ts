import {Injectable, signal,computed,effect, inject} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { flushMicrotasks } from '@angular/core/testing';


export interface Solicitud{
    id: number;
    userId: number;
    title: string;
    body: string;
    fechaCreacion: Date;
}

export interface NuevaSolicitud{
  
    userId: number;
    title: string;
    body:string;
    fechaCreacion: Date;
}

@Injectable({
    providedIn: 'root'
})
export class SolicitudesService{
      private http = inject(HttpClient)
      private apiUrl = 'https://jsonplaceholder.typicode.com/posts'
      
      
      
      private listaSolicitudes = signal<Solicitud[]>([]);  // lista de solicitudes cargadas mediante la URL
      private idsPrioritarios = signal<Set<number>>(new Set());  // lista de ids que estan marcados como prioritarios.
      private cargandoSignal = signal<boolean>(true);
      private errorSignal = signal<string | null>(null);


      solicitudes = this.listaSolicitudes.asReadonly();  // lee listaSolicitudes que es un signal de la lista
      asuntosMarcados = this.idsPrioritarios.asReadonly(); // lee la lista de los id que están marcados como prioritarios.
      cargando = this.cargandoSignal.asReadonly(); // lee signal de cargandoSignal
      error=this.errorSignal.asReadonly();// lee signal de errorSignal

      totalPrioritarios = computed(() => this.idsPrioritarios().size); //devuelve la cantidad de solicitudes que estan marcadas como prioritarias

      constructor() {
        this.cargarSolicitudes();
        }

      // GET solicitudes
      cargarSolicitudes(){
        this.cargandoSignal.set(true);
        this.errorSignal.set(null);

        this.http.get<Solicitud[]>(this.apiUrl).subscribe({
          next: (datos) => {
            this.listaSolicitudes.set(datos);
            this.cargandoSignal.set(false)
          },
          error: () => {
            this.errorSignal.set(
              'No pudimos cargar el servidor de ConectaBarrio. Verifica que haya conexión a internet '
            );
            this.cargandoSignal.set(false);
          }
        })
      }

      // POST solicitudes

      crearSolicitud(nueva:NuevaSolicitud) {
        this.http.post<Solicitud>(this.apiUrl, nueva).subscribe({
          next: (creada) =>{
            this.listaSolicitudes.update(actuales => [...actuales, creada]);
          },
          error: () => {
            this.errorSignal.set('No se pudo registrar la solicitud. Revisa los campos.')
          }
        })
      }

    
      esPrioritario(id:number):boolean{
          return this.idsPrioritarios().has(id);
    }

    alternarPrioritario(id: number){
          this.idsPrioritarios.update(actuales => {
        const nuevos = new Set(actuales);
        if (nuevos.has(id)) {
          nuevos.delete(id);
        } else {
          nuevos.add(id);
        }
        return nuevos;
      });
    }


}