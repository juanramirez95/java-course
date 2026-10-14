import { Component, inject } from '@angular/core';
import { SolicitudesService } from '../../services/solicitudes.service';
import { LideresService } from '../../services/lideres.service';

@Component({
  selector: 'app-solicitud-nueva',
  templateUrl: './solicitud-nueva.html',
  styleUrl: './solicitud-nueva.css',
})
export class SolicitudNueva {
  private solicitudesService = inject(SolicitudesService)
  private lideresService = inject(LideresService)


  lideres = this.lideresService.lideres;

  
  

registrarSolicitud(
    evento: SubmitEvent,
    inputTitle: HTMLInputElement,
    inputBody: HTMLInputElement,
    selectUserId: HTMLSelectElement
    

  ){
    evento.preventDefault();

    if(!inputTitle.value || !inputBody.value || !selectUserId){
      return;
    }
     this.solicitudesService.crearSolicitud({
      title: inputTitle.value,
      body: inputBody.value,
      userId: Number(selectUserId.value),
      fechaCreacion: new Date()
     });
     (evento.target as HTMLFormElement).reset();
  }
} 
