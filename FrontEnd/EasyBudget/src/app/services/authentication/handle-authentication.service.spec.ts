import { TestBed } from '@angular/core/testing';

import { HandleAuthenticationService } from './handle-authentication.service';

describe('HandleAuthenticationService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: HandleAuthenticationService = TestBed.get(HandleAuthenticationService);
    expect(service).toBeTruthy();
  });
});
