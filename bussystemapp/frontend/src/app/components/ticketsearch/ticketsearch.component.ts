import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Ticket } from 'src/app/model/ticket-model';
import { TicketSearchService } from 'src/app/service/ticket-search.service';
import { TokenStorageService } from 'src/app/service/token.service';

@Component({
  selector: 'app-ticketsearch',
  templateUrl: './ticketsearch.component.html',
  styleUrls: ['./ticketsearch.component.css']
})
export class TicketsearchComponent {
  destination: string = '';
  selectedDate: string = '';
  dateOfDeparture: any;
  startTown: any;
  endTown: any;
  tickets:Ticket[]=[];
  selectedStartTown: any;

  constructor(
    private tokenStorage: TokenStorageService,
    private router: Router,
    private ticketSearchService: TicketSearchService
  ) {}

  searchTickets(): void {
    console.log('Searching for tickets...');
    console.log('Destination:', this.destination);
    console.log('Date:', this.selectedDate);

    // Assuming you have a method in your service to fetch tickets
    this.ticketSearchService.getAllTicketsByCurrentRoute(this.startTown, this.endTown, this.selectedDate).subscribe(tickets => {
      console.log('Tickets:', tickets);
      this.tickets=tickets;
      // Process tickets as needed
    });
  }

  updateStartTown(selectedStartTown: any): void {
    this.startTown = selectedStartTown;
  }
  
  logout() {
    this.tokenStorage.signOut();
    this.router.navigateByUrl('/login');
  }
}
