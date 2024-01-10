import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user-model';
import { PaymentService } from 'src/app/payment.service';
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
  constructor(
    private tokenStorage: TokenStorageService,
    private userService: UserService,
    private paymentService: PaymentService,
    private router:Router,
    private fb: FormBuilder
  ) {
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
    this.paymentService.validateCard(cardNumber)
  }
  

  logout() {
    this.tokenStorage.signOut();
    this.router.navigateByUrl('/login');
  }
}