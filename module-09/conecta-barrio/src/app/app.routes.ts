import { Routes } from '@angular/router';
import { Inicio } from './pages/inicio/inicio';
import { Optional } from '@angular/core';


export const routes: Routes = [
   {path:'',
      component: Inicio}, //Es la única que se carga desde que se arranca la app, ya que es la principal


   {path:'solicitudes', 
      loadComponent: () => 
         import('./pages/solicitudes-listado/solicitudes-listado').then(s => s.SolicitudesListado)
   },
   {path:'solicitudes/:id',
      loadComponent:() =>
         import('./pages/solicitud-detalle/solicitud-detalle').then(s=>s.SolicitudDetalle)
   },
   {path:'solicitudes/nueva',
      loadComponent:() =>
         import('./pages/solicitud-nueva/solicitud-nueva').then(s => s.SolicitudNueva)
   },
   {path:'lideres',
      loadComponent:() =>
         import('./pages/lideres-listado/lideres-listado').then(s=>s.LideresListado)
   },
    {
    path: '**',
    redirectTo: '' 
  }

];
