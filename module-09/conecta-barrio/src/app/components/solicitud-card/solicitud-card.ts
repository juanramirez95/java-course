import { Component,Input, output } from '@angular/core';
import { DatePipe } from '@angular/common';


@Component({
  selector: 'app-solicitud-card',
  imports: [DatePipe],
  templateUrl: './solicitud-card.html',
  styleUrl: './solicitud-card.css',
})
export class SolicitudCard {
  @Input() id=0;
  @Input() userId ='';
  @Input()title ='';
  @Input() prioridad='';
  @Input() body='';
  @Input() fechaCreacion: Date | string= '';
  @Input() esPrioritario = false;


  prioritarioCambiado = output<void>(); //declara que solo viaja el aviso y no un dato adicional

  alClicPrioritario(evento: Event){
    evento.stopPropagation();
    evento.preventDefault();
    this.prioritarioCambiado.emit(); //Dispara el evento para que quien esccha reaccione. 
  }
}


