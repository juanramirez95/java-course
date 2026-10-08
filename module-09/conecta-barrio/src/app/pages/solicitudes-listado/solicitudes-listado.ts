import { Component, signal,computed, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { SolicitudCard } from '../../components/solicitud-card/solicitud-card';
import { SolicitudesService } from '../../services/solicitudes.service';

@Component({
  selector: 'app-solicitudes-listado',
  imports: [RouterLink,SolicitudCard],
  templateUrl: './solicitudes-listado.html',
  styleUrl: './solicitudes-listado.css',
})


export class SolicitudesListado {

  private solicitudesService = inject(SolicitudesService)

  solicitudes = this.solicitudesService.solicitudes;

  busqueda = signal('');  //este buscador pertenece solo a listado, filtrará en cada tecla, sin boton y sin recarga
  
  solicitudesFiltradas = computed(()=>  //modifica su valor a partir de otras signals y se recalcula cuando camnia alguna dependencia
  this.solicitudes().filter(s=>
    s.title.toLowerCase().includes(this.busqueda().toLowerCase())
  ));

  esPrioritario(id:number){
    return this.solicitudesService.esPrioritario(id);
  }

  alternarPrioritario(id:number){
    return this.solicitudesService.alternarPrioritario(id);
  }

}
