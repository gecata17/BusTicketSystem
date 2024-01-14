import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, forkJoin, map, mergeMap } from 'rxjs';
import { Status, Ticket } from 'src/app/model/ticket-model';
import { Town } from 'src/app/model/town-model';
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
    this.ticketSearchService.getAllTicketsByCurrentRoute(this.startTown, this.endTown, this.selectedDate)
      .pipe(
        mergeMap((tickets: Ticket[]) => {
          console.log(tickets);
          const durationRequests: Observable<Ticket>[] = tickets.map(ticket => this.calculateDurationAndDistance(ticket));
          return forkJoin(durationRequests);
        })
      )
      .subscribe((updatedTickets: Ticket[]) => {
        console.log('All duration and distance calculations completed.');
        this.tickets = updatedTickets;
      });
  }


  // Helper method to calculate duration and distance using Google API
  calculateDurationAndDistance(ticket: Ticket): Observable<any> {
    const startTown = ticket.trip?.startTown;
    const endTown = ticket.trip?.endTown;
    const url = `/maps/api/distancematrix/json?origins=${startTown}&destinations=${endTown}&key=AIzaSyDeVsw14v-ULtHB3IufnSa4J2SzhO6t274&departure_time=now`;

    var headers = new HttpHeaders({
      "Content-Type": "application/json"
    });

    return this.http.get(url, { headers: headers }).pipe(map((data: any) => {
      if (!data || !data.rows?.[0]?.elements?.[0] || !data.rows[0].elements[0]?.duration?.text) {
        ticket.duration = "Unknown";
      } else {
        ticket.duration = data.rows[0].elements[0].duration.text;
      }

      if (!data || !data.rows?.[0]?.elements?.[0] || !data.rows[0].elements[0]?.distance?.text) {
        ticket.distance = "Unknown";
      } else {
        ticket.distance = data.rows[0].elements[0].distance.text;
      }

      return ticket;
    }));
  }

  updateStartTown(selectedStartTown: any): void {
    this.startTown = selectedStartTown;
  }
  
  pay(ticketId:number){
     this.router.navigate(["/buyticket", { ticket_id: ticketId }]);
  }
  
  logout() {
    this.tokenStorage.signOut();
    this.router.navigateByUrl('/login');
  }
}
