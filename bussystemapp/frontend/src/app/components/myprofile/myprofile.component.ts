import { Component } from '@angular/core';
import { Ticket } from 'src/app/model/ticket-model';
import { User } from 'src/app/model/user-model';
import { TicketSearchService } from 'src/app/service/ticket-search.service';
import { TokenStorageService } from 'src/app/service/token.service';

@Component({
  selector: 'app-myprofile',
  templateUrl: './myprofile.component.html',
  styleUrls: ['./myprofile.component.css']
})
export class MyprofileComponent {

  myProfileTickets:Ticket[]=[];
  user: User = new User('', '', '', []);

  constructor(
    private ticketService:TicketSearchService,
    private tokenStorage:TokenStorageService,
    )
    {
      this.ticketService.getAllTicketsByCurrentUser(tokenStorage.getUsername()).subscribe((tickets) => {
        this.myProfileTickets=tickets;
      });

    }
  

  
}
