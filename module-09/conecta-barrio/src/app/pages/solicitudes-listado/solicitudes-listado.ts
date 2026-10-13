import { Component, signal,computed, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import { SolicitudCard } from '../../components/solicitud-card/solicitud-card';
import { SolicitudesService } from '../../services/solicitudes.service';
import { LideresService } from '../../services/lideres.service';

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
  busquedaBody = signal('');


  solicitudesFiltradas = computed(() => {    // El computed puede buscar en solicitudes-lista ya sea por asunto 
    const asunto = this.busqueda().toLowerCase();
    const descripcion = this.busquedaBody().toLowerCase();

    return this.solicitudes().filter(s =>
      s.title.toLowerCase().includes(asunto)||
      s.body.toLowerCase().includes(descripcion)
    );
  });

  esPrioritario(id:number){
    return this.solicitudesService.esPrioritario(id);
  }

  alternarPrioritario(id:number){
    return this.solicitudesService.alternarPrioritario(id);
  }
}
