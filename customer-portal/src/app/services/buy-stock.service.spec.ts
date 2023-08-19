import { TestBed } from '@angular/core/testing';

import { BuyStockService } from './buy-stock.service';

describe('BuyStockService', () => {
  let service: BuyStockService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BuyStockService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
