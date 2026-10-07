import { Component,Input } from '@angular/core';


@Component({
  selector: 'app-solicitud-card',
  imports: [],
  templateUrl: './solicitud-card.html',
  styleUrl: './solicitud-card.css',
})
export class SolicitudCard {
  @Input() id='';
  @Input() userId ='';
  @Input()title ='';
  @Input() prioridad='';
  @Input() body='';
}
