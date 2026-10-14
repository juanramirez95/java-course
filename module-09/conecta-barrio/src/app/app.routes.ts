import { Routes } from '@angular/router';
import { Inicio } from './pages/inicio/inicio';
import { Optional } from '@angular/core';


export const routes: Routes = [
   {path:'',  // Página de Inicio
      component: Inicio}, //Es la única que se carga desde que se arranca la app, ya que es la principal


   {path:'solicitudes',  // Página que muestra la lista de solicitues regustradas en RedManos
      loadComponent: () => 
         import('./pages/solicitudes-listado/solicitudes-listado').then(s => s.SolicitudesListado)
   },
   {path:'solicitudes/nueva', //Paginas que Registra una nueva solicitud
      loadComponent:() =>
         import('./pages/solicitud-nueva/solicitud-nueva').then(s => s.SolicitudNueva)
   },
   {path:'lideres', //  Muestra la Lista de los líderes comunitarios
      loadComponent:() =>
         import('./pages/lideres-listado/lideres-listado').then(s=>s.LideresListado)
   },
  {
   path: 'solicitudes/prioritarias', //ruta que permite ver la lista de los asuntos que están marcados como prioritarios
      loadComponent:() =>
         import('./pages/solicitudes-prioritario/solicitudes-prioritario').then(s=>s.SolicitudesPrioritario)    
   },
   {path:'solicitudes/:id', // Referencia por medio de la URL los detalles de una solicitud
      loadComponent:() =>
         import('./pages/solicitud-detalle/solicitud-detalle').then(s=>s.SolicitudDetalle)
   },
    {
    path: '**',
    redirectTo: '' 
  }

];
