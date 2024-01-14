import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Ticket } from 'src/app/model/ticket-model';
import { User } from 'src/app/model/user-model';
import { TicketSearchService } from 'src/app/service/ticket-search.service';
import { TokenStorageService } from 'src/app/service/token.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-myprofile',
  templateUrl: './myprofile.component.html',
  styleUrls: ['./myprofile.component.css']
})
export class MyprofileComponent implements OnInit {

  myProfileTickets: Ticket[] = [];
  user: User = new User('', '', '', []);
  selectedImageUrl: string | ArrayBuffer = 'assets/test.png';
  hidePassword: boolean = true;

  constructor(
    private ticketService: TicketSearchService,
    private userService: UserService,
    private tokenStorage: TokenStorageService,
    private router: Router,
  ) {}

  ngOnInit(): void {
    this.loadUserProfile();
    this.loadUserTickets();
  }

  togglePasswordVisibility() {
    this.hidePassword = !this.hidePassword;
  }
  
  loadUserProfile(): void {
    const username = this.tokenStorage.getUsername();
    console.log('Username:', username);
  
    // Fetch user information
    this.userService.getUserByUsername(username).subscribe((user) => {
      this.user = user;
  
    });
  }
  
  loadUserTickets(): void {
    const username = this.tokenStorage.getUsername();
    
    // Fetch tickets for the current user
    this.ticketService.getAllTicketsByCurrentUser(username).subscribe((tickets) => {
      
      this.myProfileTickets = tickets;
    });
  }
  

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

  deleteCurrentUser(): void {
    console.log('Deleting user:', this.user);
  
    if (!this.user || !this.user.username) {
      console.error('Invalid user or username');
      return;
    }
  
    this.userService.deleteUser(this.user).subscribe(
      () => {
        // Perform any additional actions after deletion if needed
        this.tokenStorage.signOut();
        this.router.navigateByUrl('/login');
      },
      (error) => {
        console.error('Error deleting user:', error);
        // Handle error as needed
      }
    );
  }
  
  navigateToTicketSearch() {
    this.router.navigate(['/ticketsearch']);
  }

  logout() {
    this.tokenStorage.signOut();
    this.router.navigateByUrl('/login');
  }

 
}
