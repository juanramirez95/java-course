import {Injectable, signal,computed} from '@angular/core';

export interface Solicitud{
    id: number;
    userId: string;
    title: string;
    body: string;
    fechaCreacion: Date;
    
}

@Injectable({
    providedIn: 'root'
})
export class SolicitudesService{
    private listaSolicitudes = signal<Solicitud[]>([
       {
    id: 1,
    userId: "Esteban",
    title: "Apoyo escolar",
    body: "Asistir la salida de buses al final de la jornada escolar",
    fechaCreacion: new Date('2026-10-07')
  },
  {
    id:2,
    userId: "María",
    title: "Jornada de limpieza",
    body: "Organizar voluntarios para limpiar el parque central",
    fechaCreacion: new Date('2026-10-07')
  },
  {
    id:3,
    userId: "Carlos",
    title: "Reunión comunitaria",
    body: "Convocar a los vecinos para discutir mejoras en seguridad",
    fechaCreacion: new Date('2026-10-07')
  },
  {
    id:4,
    userId: "Lucía",
    title: "Huerta comunitaria",
    body: "Coordinar la siembra de hortalizas en el lote comunal",
    fechaCreacion: new Date('2026-10-07')
  },
  {
    id:5,
    userId: "Andrés",
    title: "Clases de deporte",
    body: "Promover actividades deportivas para niños y jóvenes",
    fechaCreacion: new Date('2026-10-07')
  },
  {
    id:6,
    userId: "Andrés",
    title: "Clases de deporte",
    body: "Promover actividades deportivas para niños y jóvenes",
    fechaCreacion: new Date('2026-10-07')
  } 
    ]);

    solicitudes = this.listaSolicitudes.asReadonly();

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