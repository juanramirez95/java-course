import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SolicitudDetalle } from './solicitud-detalle';

describe('SolicitudDetalle', () => {
  let component: SolicitudDetalle;
  let fixture: ComponentFixture<SolicitudDetalle>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SolicitudDetalle],
    }).compileComponents();

    fixture = TestBed.createComponent(SolicitudDetalle);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
