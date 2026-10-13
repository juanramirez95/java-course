import { Component,signal,computed, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { SolicitudCard } from '../../components/solicitud-card/solicitud-card';
import { SolicitudesService } from '../../services/solicitudes.service';
@Component({
  selector: 'app-solicitudes-prioritario',
  imports: [RouterLink,SolicitudCard],
  templateUrl: './solicitudes-prioritario.html',
  styleUrl: './solicitudes-prioritario.css',
})
export class SolicitudesPrioritario {

private solicitudService = inject(SolicitudesService);

asuntosPrioritarios = computed(()=> 
this.solicitudService.solicitudes().filter(s => this.solicitudService.asuntosMarcados().has(s.id)
))


esPrioritario(id:number){
    return this.solicitudService.esPrioritario(id);
  }

  alternarPrioritario(id:number){
    return this.solicitudService.alternarPrioritario(id);
  }



}
