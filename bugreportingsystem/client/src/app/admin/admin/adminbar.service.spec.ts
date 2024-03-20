import { TestBed } from '@angular/core/testing';

import { AdminbarService } from './adminbar.service';

describe('AdminbarService', () => {
  let service: AdminbarService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminbarService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
