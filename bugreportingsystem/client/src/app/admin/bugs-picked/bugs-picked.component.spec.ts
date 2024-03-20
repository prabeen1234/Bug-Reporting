import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BugsPickedComponent } from './bugs-picked.component';

describe('BugsPickedComponent', () => {
  let component: BugsPickedComponent;
  let fixture: ComponentFixture<BugsPickedComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BugsPickedComponent]
    });
    fixture = TestBed.createComponent(BugsPickedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
