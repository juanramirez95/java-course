import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SolicitudNueva } from './solicitud-nueva';

describe('SolicitudNueva', () => {
  let component: SolicitudNueva;
  let fixture: ComponentFixture<SolicitudNueva>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SolicitudNueva],
    }).compileComponents();

    fixture = TestBed.createComponent(SolicitudNueva);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
