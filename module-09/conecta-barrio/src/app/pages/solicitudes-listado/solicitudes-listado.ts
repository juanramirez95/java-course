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
  busquedaLider = signal('');

  solicitudesFiltradas = computed(() => {
    const asunto = this.busqueda().toLowerCase();
    const lider = this.busquedaLider().toLowerCase();

    return this.solicitudes().filter(s =>
      s.title.toLowerCase().includes(asunto) ||
      s.userId.toLowerCase().includes(lider)
    );
  });


  esPrioritario(id:number){
    return this.solicitudesService.esPrioritario(id);
  }

  alternarPrioritario(id:number){
    return this.solicitudesService.alternarPrioritario(id);
  }

}
