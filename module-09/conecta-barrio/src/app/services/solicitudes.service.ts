import {Injectable, signal,computed} from '@angular/core';


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
      } 
    ]);

    solicitudes = this.listaSolicitudes.asReadonly();  // lee listaSolicitudes que es un signal de la lista
      private idsPrioritarios = signal<Set<number>>(new Set());

      totalPrioritarios = computed(() => this.idsPrioritarios().size);

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