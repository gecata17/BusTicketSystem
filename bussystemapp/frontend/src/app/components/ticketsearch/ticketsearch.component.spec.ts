import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TicketsearchComponent } from './ticketsearch.component';

describe('TicketsearchComponent', () => {
  let component: TicketsearchComponent;
  let fixture: ComponentFixture<TicketsearchComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TicketsearchComponent]
    });
    fixture = TestBed.createComponent(TicketsearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
