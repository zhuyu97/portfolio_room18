import { TestBed } from '@angular/core/testing';

import { AllholdassetsService } from './allholdassets.service';

describe('AllholdassetsService', () => {
  let service: AllholdassetsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AllholdassetsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
