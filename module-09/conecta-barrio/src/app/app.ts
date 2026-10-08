import { Component, inject, signal } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';
import { SolicitudesService } from './services/solicitudes.service';


@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RouterLink],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  private solicitudesService = inject(SolicitudesService);
  totalFavoritos = this.solicitudesService.totalPrioritarios;
  
  protected nombreMVP = 'RedManos'
  sidebarVisible=false;
  
  toggleSideBar(){
    this.sidebarVisible = !this.sidebarVisible; /** Binding que permite deplegar el contenido del Menu */
  }

}
