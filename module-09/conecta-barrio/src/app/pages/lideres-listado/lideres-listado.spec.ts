import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LideresListado } from './lideres-listado';

describe('LideresListado', () => {
  let component: LideresListado;
  let fixture: ComponentFixture<LideresListado>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LideresListado],
    }).compileComponents();

    fixture = TestBed.createComponent(LideresListado);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
