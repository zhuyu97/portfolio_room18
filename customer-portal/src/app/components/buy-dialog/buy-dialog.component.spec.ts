import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BuyDialogComponent } from './buy-dialog.component';

describe('DialogComponent', () => {
  let component: BuyDialogComponent;
  let fixture: ComponentFixture<BuyDialogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BuyDialogComponent]
    });
    fixture = TestBed.createComponent(BuyDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
