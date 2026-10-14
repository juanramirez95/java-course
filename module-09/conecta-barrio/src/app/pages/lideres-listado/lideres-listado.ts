import { Component, computed, inject, output, signal } from '@angular/core';
import { RouterLink } from '@angular/router';
import { LideresService } from '../../services/lideres.service'


@Component({
  selector: 'app-lideres-listado',
  imports: [RouterLink],
  templateUrl: './lideres-listado.html',
  styleUrl: './lideres-listado.css',
})
export class LideresListado {

  private liderService = inject(LideresService)

  lideres = this.liderService.lideres;
  cargando = this.liderService.cargando;
  error= this.liderService.error;
  

  busquedaLider = signal('');

  lideresFiltrados = computed(() =>
    
    this.lideres().filter( l =>
      l.name.toLocaleLowerCase().includes(
        this.busquedaLider().toLowerCase())
    )
  );

  
  
}
