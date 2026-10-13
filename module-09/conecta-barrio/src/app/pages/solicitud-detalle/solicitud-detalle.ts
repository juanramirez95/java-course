import { Component, computed, inject, Input } from '@angular/core';
import { RouterLink } from '@angular/router';
import { SolicitudesService } from '../../services/solicitudes.service';
import { LideresService } from '../../services/lideres.service';
import { DatePipe } from '@angular/common';


@Component({
  selector: 'app-solicitud-detalle',
  imports: [RouterLink,DatePipe],
  templateUrl: './solicitud-detalle.html',
  styleUrl: './solicitud-detalle.css',
})
export class SolicitudDetalle {

  @Input() id =''; // recibe el id del URL en formato String

  private solicitudesService = inject(SolicitudesService)
  private lideresService = inject(LideresService)

  

  solicitud = computed(() =>
    this.solicitudesService.solicitudes().find(s => s.id === Number(this.id)))  //Solicitud encuentra el id de la solicitud y Number(this.id) transforma el string en numero para comparar

  userId = computed(() =>
    this.solicitud()?.userId)  //Este computed obtiene el userId 

  lideresFiltrados = computed(() =>
    this.lideresService.lideres().find(l=>l.id === this.userId())) //busca en la lista de lideres el id que sea igual al obtenido en userId. 


  esPrioritario = computed(() =>
    this.solicitudesService.esPrioritario(Number(this.id))
  );

  alternarPrioritario(){
    this.solicitudesService.alternarPrioritario(Number(this.id));
  }

  
}
