import { TestBed } from '@angular/core/testing';

import { TicketSearchService } from './ticket-search.service';

describe('TicketSearchService', () => {
  let service: TicketSearchService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TicketSearchService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
