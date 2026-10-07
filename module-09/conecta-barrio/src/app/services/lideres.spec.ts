import { TestBed } from '@angular/core/testing';

import { Lideres } from './lideres';

describe('Lideres', () => {
  let service: Lideres;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(Lideres);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
