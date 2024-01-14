import { HttpClient } from '@angular/common/http';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Town } from 'src/app/model/town-model';
import { Trip } from 'src/app/model/trip-model';
import { TownService } from 'src/app/service/town.service';

@Component({
  selector: 'app-drop-list',
  templateUrl: './drop-list.component.html',
  styleUrls: ['./drop-list.component.css']
})

export class DropListComponent implements OnInit {
  @Input() selectedStartTown: any;
    trips: Trip[] = []; // Initialize your trips array
    townOptions: Town[] = []; // Populate this array with available towns

  constructor(private townService: TownService) {}

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

  @Output() selectedStartTownChange = new EventEmitter<any>(); // Add this property

  updateStartTown(trip: Trip, event: any): void {
    if (event && event.target) {
      trip.startTown = event.target.value;
      this.selectedStartTownChange.emit(trip.startTown);
    }
  }

}

