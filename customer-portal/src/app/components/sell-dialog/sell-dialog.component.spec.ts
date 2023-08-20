import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SellDialogComponent } from './sell-dialog.component';

describe('DialogComponent', () => {
  let component: SellDialogComponent;
  let fixture: ComponentFixture<SellDialogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [SellDialogComponent]
    });
    fixture = TestBed.createComponent(SellDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
