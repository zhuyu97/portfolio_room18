import { TestBed } from '@angular/core/testing';

import { AllassetsvalueService } from './allassetsvalue.service';

describe('AllassetsvalueService', () => {
  let service: AllassetsvalueService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AllassetsvalueService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
