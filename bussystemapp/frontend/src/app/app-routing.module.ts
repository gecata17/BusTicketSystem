import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { TicketsearchComponent } from './components/ticketsearch/ticketsearch.component';
import { MyprofileComponent } from './components/myprofile/myprofile.component';
import { BuyticketComponent } from './components/buyticket/buyticket.component';

const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'signup'
  },
  {path: 'login', component: LoginComponent},
  {path: 'signup',component: SignupComponent},
  {path: 'ticketsearch',component: TicketsearchComponent},
  {path: 'myprofile',component: MyprofileComponent},
  {path: 'buyticket',component: BuyticketComponent}, 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {


 }

 
