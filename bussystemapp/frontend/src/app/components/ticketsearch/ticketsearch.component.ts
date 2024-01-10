import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Ticket } from 'src/app/model/ticket-model';
import { Trip } from 'src/app/model/trip-model';
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
  trip:Trip[]=[];
  selectedStartTown: any;

  constructor(
    private tokenStorage: TokenStorageService,
    private router: Router,
    private ticketSearchService: TicketSearchService,
    private http: HttpClient
  ) {}

  searchTickets(): void {
    console.log('Searching for tickets...');
    console.log('Destination:', this.destination);
    console.log('Date:', this.selectedDate);

    // Assuming you have a method in your service to fetch tickets
    this.ticketSearchService.getAllTicketsByCurrentRoute(this.startTown, this.endTown, this.selectedDate).subscribe(tickets => {
      console.log('Tickets:', tickets);
      tickets.forEach(ticket => {
        this.calculateDuration(ticket);
      });
      this.tickets=tickets;
    });
  }


  // Helper method to calculate duration using Google API
  calculateDuration(ticket: Ticket): void {
    console.log(ticket);
    const startTown = ticket.trip.startTown;
    const endTown = ticket.trip.endTown;

    // const origin = `${startTown.title},${startTown.title}`;
    // const destination = `${endTown.latitude},${endTown.title}`;
    const apiKey = 'AIzaSyDeVsw14v-ULtHB3IufnSa4J2SzhO6t274';

    const url = `https://maps.googleapis.com/maps/api/distancematrix/json?origins=${startTown}&destinations=${endTown}&key=AIzaSyDeVsw14v-ULtHB3IufnSa4J2SzhO6t274&departure_time=now`;

    // Fetch data from Google API
    var headers = new HttpHeaders({
      "Content-Type": "application/json"
    });
    this.http.get(url, { headers: headers}).subscribe((data)=>console.log(data), (err)=>console.error(err));
  }

  updateStartTown(selectedStartTown: any): void {
    this.startTown = selectedStartTown;
  }
  
  logout() {
    this.tokenStorage.signOut();
    this.router.navigateByUrl('/login');
  }
}
