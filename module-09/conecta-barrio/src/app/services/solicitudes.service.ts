import {Injectable, signal,computed,effect} from '@angular/core';


export interface Solicitud{
    id: number;
    userId: number;
    title: string;
    body: string;
    fechaCreacion: Date;
}

export interface nuevaSolicitud{
    id: number;
    userId: number;
    tile: string;
    body:string;
    
    fechaCreacion: Date;
}

@Injectable({
    providedIn: 'root'
})
export class SolicitudesService{
      private listaSolicitudes = signal<Solicitud[]>([
          {
        id: 1,
        userId: 1,
        title: "Apoyo escolarescolar",
        body: "Asistir ala salida de buses al final de la jornada escolar",
        fechaCreacion: new Date('2026-10-07')
      },
      {
        id:2,
        userId: 2,
        title: "Jornada de limpieza",
        body: "Organizar voluntarios para limpiar el parque central.",
        fechaCreacion: new Date('2026-10-07')
      },
      {
        id:3,
        userId: 3,
        title: "Reunión comunitaria",
        body: "Convocar a los vecinos para discutir mejoras en seguridad",
        fechaCreacion: new Date('2026-10-07')
      },
      {
        id:4,
        userId: 4,
        title: "Huerta comunitaria",
        body: "Coordinar la siembra de hortalizas en el lote comunal",
        fechaCreacion: new Date('2026-10-07')
      },
      {
        id:5,
        userId: 1,
        title: "Clases de deporte",
        body: "Promover actividades deportivas para niños y jóvenes",
        fechaCreacion: new Date('2026-10-07')
      },
      {
        id:6,
        userId: 6,
        title: "Clases de deporte",
        body: "Promover actividades deportivas para niños y jóvenes",
        fechaCreacion: new Date('2026-10-07')
      },
      {
        id:7,
        userId: 3,
        title: "Clases de deporte",
        body: "Promover actividades deportivas para niños y jóvenes",
        fechaCreacion: new Date('2026-10-07')
      },
      {
        id:9,
        userId: 1,
        title: "Organizar sesion de recolecta de basura",
        body: "Promover actividades deportivas para niños y jóvenes",
        fechaCreacion: new Date('2026-10-07')
      }  
    ]);
    
      private idsPrioritarios = signal<Set<number>>(new Set());  // lista de ids que estan marcados como prioritarios.

    

      constructor() {
        effect(() => {
          const idsActuales = [...this.idsPrioritarios()];
          localStorage.setItem('redmanos-prioritarios', JSON.stringify(idsActuales));
        });
        }

    solicitudes = this.listaSolicitudes.asReadonly();  // lee listaSolicitudes que es un signal de la lista
    asuntosMarcados = this.idsPrioritarios.asReadonly(); // lee la lista de los id que están marcados como prioritarios.


    totalPrioritarios = computed(() => this.idsPrioritarios().size); //devuelve la cantidad de solicitudes que estan marcadas como prioritarias


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