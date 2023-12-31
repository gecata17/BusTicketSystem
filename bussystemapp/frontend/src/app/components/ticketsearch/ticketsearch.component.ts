import { Component } from '@angular/core';
import { TicketSearchService } from 'src/app/service/ticket-search.service';

@Component({
  selector: 'app-ticketsearch',
  templateUrl: './ticketsearch.component.html',
  styleUrls: ['./ticketsearch.component.css']
})
export class TicketsearchComponent {
  destination: string = ''; // Variable to store the entered destination
  selectedDate: string = ''; // Variable to store the selected date

  searchTickets(): void {
    // Logic to perform ticket search based on destination and date
    console.log('Searching for tickets...');
    console.log('Destination:', this.destination);
    console.log('Date:', this.selectedDate);
    // You can add more complex logic or call a service to perform the actual search
  }
}
