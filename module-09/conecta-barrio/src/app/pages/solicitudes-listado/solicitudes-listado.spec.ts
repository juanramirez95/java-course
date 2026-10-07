import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SolicitudesListado } from './solicitudes-listado';

describe('SolicitudesListado', () => {
  let component: SolicitudesListado;
  let fixture: ComponentFixture<SolicitudesListado>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SolicitudesListado],
    }).compileComponents();

    fixture = TestBed.createComponent(SolicitudesListado);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
