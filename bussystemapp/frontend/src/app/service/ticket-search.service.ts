import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Ticket } from '../model/ticket-model';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class TicketSearchService {

  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http:HttpClient){} 
   
  createTicket(ticket:Ticket){
    const url = `${this.apiServerUrl}/private/api/ticketsearch/`;
    return this.http.post<Ticket>(url,ticket);
  }

  updateTicket(ticket:Ticket){
    return this.http.put<Ticket>(`${this.apiServerUrl}/private/api/ticketsearch/update/${ticket.id}`,ticket);
  }

  deleteTicket(ticketId:number){
    return this.http.delete<Ticket>(`${this.apiServerUrl}/private/api/ticketsearch/${ticketId}`);
  }

  getAllTicketsByCurrentUser(username:string | null){
    const url = `${this.apiServerUrl}/private/api/ticketsearch/usertickets/${username}`;
    return this.http.get<Ticket[]>(url);
  }

  getAllTicketsByCurrentRoute(startTown:string, endTown: string, dateOfDeparture: string){
    const params = new HttpParams().set(
      "startTown", startTown).set(
      "endTown", endTown).set(
      "dateOfDeparture", dateOfDeparture);
    const url =`${this.apiServerUrl}/private/api/ticketsearch`;
    return this.http.get<Ticket[]>(url, {params});
  }
}
