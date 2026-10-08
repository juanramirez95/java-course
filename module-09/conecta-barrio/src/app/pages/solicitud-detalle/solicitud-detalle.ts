import { Component, computed, inject, Input } from '@angular/core';
import { RouterLink } from '@angular/router';
import { SolicitudesService } from '../../services/solicitudes.service';

@Component({
  selector: 'app-solicitud-detalle',
  imports: [RouterLink],
  templateUrl: './solicitud-detalle.html',
  styleUrl: './solicitud-detalle.css',
})
export class SolicitudDetalle {

  @Input() id ='';

  private solicitudesService = inject(SolicitudesService)

  solicitud = computed(() =>
    this.solicitudesService.solicitudes().find(s => s.id === Number(this.id))
  );

  esPrioritario = computed(() =>
    this.solicitudesService.esPrioritario(Number(this.id))
  );

  alternarPrioritario(){
    this.solicitudesService.alternarPrioritario(Number(this.id));
  }


}
