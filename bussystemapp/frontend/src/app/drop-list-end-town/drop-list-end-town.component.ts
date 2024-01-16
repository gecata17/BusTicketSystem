import { Component, EventEmitter, Input, OnInit, Output, forwardRef } from '@angular/core';
import { NG_VALUE_ACCESSOR ,ControlValueAccessor} from '@angular/forms';
import { Town } from '../model/town-model';
import { TownService } from '../service/town.service';

@Component({
  selector: 'app-drop-list-end-town',
  templateUrl: './drop-list-end-town.component.html',
  styleUrls: ['./drop-list-end-town.component.css'],
  providers: [{
    provide: NG_VALUE_ACCESSOR,
    useExisting: forwardRef(()=>DropListEndTownComponent),
    multi: true
  }]
})
export class DropListEndTownComponent implements OnInit, ControlValueAccessor {

  @Input() options: string[] = [];
  @Output() selectedEndTown: string = '';

  @Output() selectedValueChangeEndTown: EventEmitter<string> = new EventEmitter<string>();
  townOptions: Town[] = [];

  constructor(private townService: TownService) {}

  writeValue(value: any): void {
    this.selectedEndTown = value;
    this.onChangeEndTown(value); // Notify Angular that the value has changed
  }

  

  registerOnTouched(fn: any): void {
    this.onTouchedEndTown = fn;
  }
  
 

  registerOnChange(fn: any): void {
    this.onChangeEndTown = fn;
  }
 

  private onChangeEndTown: (value: any) => void = () => {};

  private onTouchedEndTown: () => void = () => {};
  

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
