import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { HttpClientModule } from '@angular/common/http';
import { TicketsearchComponent } from './components/ticketsearch/ticketsearch.component';
import { MyprofileComponent } from './components/myprofile/myprofile.component';
import { BuyticketComponent } from './components/buyticket/buyticket.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DropListComponent } from './components/drop-list/drop-list.component';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatIconModule } from '@angular/material/icon';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    TicketsearchComponent,
    MyprofileComponent,
    BuyticketComponent,
    DropListComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule,
    MatFormFieldModule,
    MatSelectModule,
    FormsModule,
    MatIconModule,
    ReactiveFormsModule,

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
