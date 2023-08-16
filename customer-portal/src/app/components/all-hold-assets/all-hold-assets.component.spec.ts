import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AllHoldAssetsComponent } from './all-hold-assets.component';

describe('AllHoldAssetsComponent', () => {
  let component: AllHoldAssetsComponent;
  let fixture: ComponentFixture<AllHoldAssetsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AllHoldAssetsComponent]
    });
    fixture = TestBed.createComponent(AllHoldAssetsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
