import { Injectable, signal,computed, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

export interface Lider{
     id: number;
     name: string;
     email: string;
     phone: string;
     addressCity: string;
     companyName: string;
}

@Injectable({
  providedIn: 'root',
})
export class LideresService {

  private http = inject(HttpClient)
  private apiurl = 'https://jsonplaceholder.typicode.com/users'

  private listaLideres = signal<Lider[]>( []);
  private cargandoSignal = signal<boolean>(true);
  private errorSignal = signal<string | null>(null);
  private idsPrioritarios = signal<Set<number>>(new Set());

lideres = this.listaLideres.asReadonly();  // Lee listaLideres
cargando =this.cargandoSignal.asReadonly();
error = this.errorSignal.asReadonly();

constructor(){
  this.cargarLideres();
}

cargarLideres(){
  this.cargandoSignal.set(true);
  this.errorSignal.set(null);


  this.http.get<Lider[]>(this.apiurl).subscribe({
    next: (datos)=>{
      this.listaLideres.set(datos);
      this.cargandoSignal.set(false)
    },
    error: () =>{
      this.errorSignal.set( 'No pudimos cargar el servidor de ConectaBarrio. Verifica que haya conexión a internet '
      );
      this.cargandoSignal.set(false);
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
