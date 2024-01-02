import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { TicketSearchService } from 'src/app/service/ticket-search.service';
import { TokenStorageService } from 'src/app/service/token.service';

@Component({
  selector: 'app-ticketsearch',
  templateUrl: './ticketsearch.component.html',
  styleUrls: ['./ticketsearch.component.css']
})
export class TicketsearchComponent {
  destination: string = ''; // Variable to store the entered destination
  selectedDate: string = ''; // Variable to store the selected date

  //TO DO
  dateOfDeparture: any;
  startTown: any; 
  endTown: any;

  constructor(
    private tokenStorage:TokenStorageService,
    private router:Router
    ){}

  searchTickets(): void {
    // TO DO
    console.log('Searching for tickets...');
    console.log('Destination:', this.destination);
    console.log('Date:', this.selectedDate);
    
  }

  logout(){
    this.tokenStorage.signOut();
    this.router.navigateByUrl('/login');
  }

}
