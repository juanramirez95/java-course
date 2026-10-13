import { Component, computed, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { SolicitudCard } from '../../components/solicitud-card/solicitud-card';
import { SolicitudesService } from '../../services/solicitudes.service';
import { LideresService } from '../../services/lideres.service';
@Component({
  selector: 'app-inicio',
  imports: [RouterLink],
  templateUrl: './inicio.html',
  styleUrl: './inicio.css',
})
export class Inicio {
  private solicitudesSerevice = inject(SolicitudesService)
  private lideresService = inject(LideresService)


  private solicitudes = this.solicitudesSerevice.solicitudes;

  //---Indicadores---//

  solicitudesTotales = computed(() => this.solicitudes().length);
  totalPrioritarios =  this.solicitudesSerevice.totalPrioritarios;
  
}
