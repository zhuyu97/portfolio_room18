import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BuyStockDialogComponent } from './buy-stock-dialog.component';

describe('BuyStockDialogComponent', () => {
  let component: BuyStockDialogComponent;
  let fixture: ComponentFixture<BuyStockDialogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BuyStockDialogComponent]
    });
    fixture = TestBed.createComponent(BuyStockDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
