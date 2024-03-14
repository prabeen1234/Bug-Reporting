import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminBugComponent } from './admin-bug.component';

describe('AdminBugComponent', () => {
  let component: AdminBugComponent;
  let fixture: ComponentFixture<AdminBugComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AdminBugComponent]
    });
    fixture = TestBed.createComponent(AdminBugComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
