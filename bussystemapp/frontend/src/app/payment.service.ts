import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  private apiUrl = 'https://api.test.paysafe.com/cardpayments/v1/accounts/{account_id}';
  private apiKey = 'YOUR_BASE64_ENCODED_API_KEY';

  constructor(private http: HttpClient) {}

  private getHeaders(): HttpHeaders {
    return new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Basic ${this.apiKey}`,
    });
  }

  validateCard(cardNUmber: string) {
    var headers = new HttpHeaders({
      "apikey": "Z73fuM0mtX7lv38srpapZli7lbRstpXv"
    });

    this.http.get<any>("https://api.apilayer.com/bincheck/"+cardNUmber, { headers: headers })
      .subscribe((result: any) => alert("Card is valid.Payment was succesful."), (error: any) => alert("Card is invalid.Unsuccesful payment."));
    }

}
