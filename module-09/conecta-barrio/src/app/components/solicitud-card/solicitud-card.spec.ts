import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SolicitudCard } from './solicitud-card';

describe('SolicitudCard', () => {
  let component: SolicitudCard;
  let fixture: ComponentFixture<SolicitudCard>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SolicitudCard],
    }).compileComponents();

    fixture = TestBed.createComponent(SolicitudCard);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
