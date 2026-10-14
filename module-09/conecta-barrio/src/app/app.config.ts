import { ApplicationConfig, provideBrowserGlobalErrorListeners } from '@angular/core';
import { provideRouter, withComponentInputBinding } from '@angular/router';
import { provideHttpClient } from '@angular/common/http';
import { routes } from './app.routes';

export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideRouter(routes, withComponentInputBinding()),  // recibe un parámetro que lo pasa directamente a un @input que se llame igual en el componente
    provideHttpClient()                                 // HttplClient es un servicio de angular que permite enviar peticiones HTTP, provideHttpClient() habilita el cliente http
  ]
};
