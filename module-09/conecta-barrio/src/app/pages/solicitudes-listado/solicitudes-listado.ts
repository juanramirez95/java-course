import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { SolicitudCard } from '../../components/solicitud-card/solicitud-card';
@Component({
  selector: 'app-solicitudes-listado',
  imports: [RouterLink,SolicitudCard],
  templateUrl: './solicitudes-listado.html',
  styleUrl: './solicitudes-listado.css',
})
export class SolicitudesListado {
  solicitudes = [
    
  {
    id: '1',
    userId: "Esteban",
    title: "Apoyo escolar",
    body: "Asistir la salida de buses al final de la jornada escolar"
  },
  {
    id:'2',
    userId: "María",
    title: "Jornada de limpieza",
    body: "Organizar voluntarios para limpiar el parque central"
  },
  {
    id:'3',
    userId: "Carlos",
    title: "Reunión comunitaria",
    body: "Convocar a los vecinos para discutir mejoras en seguridad"
  },
  {
    id:'4',
    userId: "Lucía",
    title: "Huerta comunitaria",
    body: "Coordinar la siembra de hortalizas en el lote comunal"
  },
  {
    id:'5',
    userId: "Andrés",
    title: "Clases de deporte",
    body: "Promover actividades deportivas para niños y jóvenes"
  }


  ]
}
