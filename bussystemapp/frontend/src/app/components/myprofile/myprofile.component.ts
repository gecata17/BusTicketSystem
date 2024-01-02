import { Component } from '@angular/core';
import { Router } from '@angular/router';
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

  myProfileTickets: Ticket[] = [];
  user: User = new User('', '', '', []);
  selectedImageUrl: string | ArrayBuffer = 'assets/test.png'; // Set the initial path of your image

  handleImageChange(event: any): void {
    const file = event.target.files?.[0]; // Use optional chaining to handle undefined

    if (file) {
      const reader = new FileReader();

      reader.onload = (e) => {
        this.selectedImageUrl = e.target?.result!;
      };

      reader.readAsDataURL(file);
    } else {
      // Handle the case when no file is selected (optional)
      this.selectedImageUrl = 'assets/test.png'; // Set it back to the default image
    }
  }

  constructor(
    private ticketService: TicketSearchService,
    private tokenStorage: TokenStorageService,
    private router:Router,
  ) {
    this.ticketService.getAllTicketsByCurrentUser(tokenStorage.getUsername()).subscribe((tickets) => {
      this.myProfileTickets = tickets;
    });
  }

  logout() {
    this.tokenStorage.signOut();
    this.router.navigateByUrl('/login');
  }
}
