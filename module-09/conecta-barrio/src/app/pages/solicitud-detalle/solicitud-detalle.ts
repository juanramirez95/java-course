import { Component, Input } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-solicitud-detalle',
  imports: [RouterLink],
  templateUrl: './solicitud-detalle.html',
  styleUrl: './solicitud-detalle.css',
})
export class SolicitudDetalle {

  @Input() id ='';

  solicitudes = [
    
  {
    id: 1,
    userId: "Esteban",
    title: "Apoyo escolar",
    body: "Asistir la salida de buses al final de la jornada escolar",
    fechaCreacion: "10/7/2026"
  },
  {
    id:2,
    userId: "María",
    title: "Jornada de limpieza",
    body: "Organizar voluntarios para limpiar el parque central",
    fechaCreacion: "10/7/2026"
  },
  {
    id:3,
    userId: "Carlos",
    title: "Reunión comunitaria",
    body: "Convocar a los vecinos para discutir mejoras en seguridad",
    fechaCreacion: "10/7/2026"
  },
  {
    id:4,
    userId: "Lucía",
    title: "Huerta comunitaria",
    body: "Coordinar la siembra de hortalizas en el lote comunal",
    fechaCreacion: "10/7/2026"
  },
  {
    id:5,
    userId: "Andrés",
    title: "Clases de deporte",
    body: "Promover actividades deportivas para niños y jóvenes",
    fechaCreacion: "10/7/2026"
  }
  ];
  
  get solicitud(){
    return this.solicitudes.find(s => s.id === Number(this.id)); // Busca en la lista el id que coincida con el de la URL y la devuelve
  }

}
