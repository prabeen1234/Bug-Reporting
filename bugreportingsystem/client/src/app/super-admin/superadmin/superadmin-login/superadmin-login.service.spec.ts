import { TestBed } from '@angular/core/testing';

import { SuperadminLoginService } from './superadmin-login.service';

describe('SuperadminLoginService', () => {
  let service: SuperadminLoginService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SuperadminLoginService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
