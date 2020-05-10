import { TestBed } from '@angular/core/testing';

import { CurrentBudgetService } from './current-budget.service';

describe('CurrentBudgetService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CurrentBudgetService = TestBed.get(CurrentBudgetService);
    expect(service).toBeTruthy();
  });
});
