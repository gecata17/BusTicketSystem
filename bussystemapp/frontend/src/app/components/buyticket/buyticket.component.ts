import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { TokenStorageService } from 'src/app/service/token.service';

@Component({
  selector: 'app-buyticket',
  templateUrl: './buyticket.component.html',
  styleUrls: ['./buyticket.component.css']
})
export class BuyticketComponent {


  constructor(
    private tokenStorage: TokenStorageService,
    private router:Router
    ){

  }

  
  navigateToTicketSearch(): void {
    
    this.router.navigateByUrl('/ticketsearch')
  }


  logout() {
    this.tokenStorage.signOut();
    this.router.navigateByUrl('/login');
  }
}
