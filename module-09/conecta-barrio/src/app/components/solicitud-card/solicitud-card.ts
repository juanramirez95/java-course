import { Component,Input } from '@angular/core';
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
}
