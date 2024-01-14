import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Status, Ticket, TicketUpdateRequest } from 'src/app/model/ticket-model';
import { User } from 'src/app/model/user-model';
import { PaymentService } from 'src/app/payment.service';
import { TicketSearchService } from 'src/app/service/ticket-search.service';
import { TokenStorageService } from 'src/app/service/token.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-buyticket',
  templateUrl: './buyticket.component.html',
  styleUrls: ['./buyticket.component.css']
})
export class BuyticketComponent {
    
  user: User = new User('', '', '', []);
  cardNumber: string = "";
  cvv: string="";
  expirationDate: string="";
  cardholderName: string="";
  paymentForm: FormGroup;
  ticketId: number;

  constructor(
    private tokenStorage: TokenStorageService,
    private userService: UserService,
    private paymentService: PaymentService,
    private ticketService: TicketSearchService,
    private router:Router,
    private route : ActivatedRoute,
    private fb: FormBuilder
  ) {
    this.ticketId = this.route.snapshot.params["ticket_id"];
    this.paymentForm = this.fb.group({
      // ... other form controls
      cvv: ['', [Validators.required, Validators.pattern('[0-9]{3}')]],
      expirationDate: ['', [Validators.required, Validators.pattern('(0[1-9]|1[0-2])\/[0-9]{2}')]],
      cardholderName: ['', [Validators.required, Validators.pattern('[a-zA-Z ]*')]],
    })
   }
  handlePayment() {
    const cardNumber=this.cardNumber;
    console.log(this.cardNumber);
    console.log(this.ticketId);
    this.paymentService.validateCard(cardNumber)
    .subscribe((result: any) => {
        const newTicket = new TicketUpdateRequest(this.tokenStorage.getUsername(), Status.purchased);
        this.ticketService.updateTicket(this.ticketId, newTicket).subscribe(
          data => { console.log(data) },
          err => { console.log(err) }
        );
      }, (error: any) => alert("Card is invalid.Unsuccesful payment.")
    );
  }

  logout() {
    this.tokenStorage.signOut();
    this.router.navigateByUrl('/login');
  }
}