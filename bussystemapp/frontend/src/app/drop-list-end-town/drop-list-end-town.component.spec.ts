import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DropListEndTownComponent } from './drop-list-end-town.component';

describe('DropListEndTownComponent', () => {
  let component: DropListEndTownComponent;
  let fixture: ComponentFixture<DropListEndTownComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DropListEndTownComponent]
    });
    fixture = TestBed.createComponent(DropListEndTownComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
