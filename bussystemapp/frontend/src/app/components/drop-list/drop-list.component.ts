
import { Component, EventEmitter, Input, OnInit, Output, forwardRef } from '@angular/core';
import { ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';
import { Town } from 'src/app/model/town-model';

import { TownService } from 'src/app/service/town.service';

@Component({
  selector: 'app-drop-list',
  templateUrl: './drop-list.component.html',
  styleUrls: ['./drop-list.component.css'],
  providers: [{
    provide: NG_VALUE_ACCESSOR,
    useExisting: forwardRef(()=>DropListComponent),
    multi: true
  }]
})

export class DropListComponent implements OnInit, ControlValueAccessor {
  @Input() options: string[] = [];
  @Output() selectedTown: string = '';

  @Output() selectedValueChange: EventEmitter<string> = new EventEmitter<string>();
  townOptions: Town[] = [];

  constructor(private townService: TownService) {}

  writeValue(value: any): void {
    this.selectedTown = value;
    this.onChange(value); // Notify Angular that the value has changed
  }

  

  registerOnTouched(fn: any): void {
    this.onTouched = fn;
  }
  
 

  registerOnChange(fn: any): void {
    this.onChange = fn;
  }
 

  private onChange: (value: any) => void = () => {};

  private onTouched: () => void = () => {};
  

  ngOnInit(): void {
    this.loadTownOptions();
  }

  loadTownOptions(): void {
    this.townService.getAllTowns().subscribe(
      (towns) => {
        this.townOptions = towns;
      },
      (error) => {
        console.error('Error fetching towns', error);
      }
    );
  }

}

