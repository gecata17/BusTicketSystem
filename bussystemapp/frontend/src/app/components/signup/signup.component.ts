import { Component } from '@angular/core';
import { SignUpUser } from 'src/app/model/signup-model';
import { SignupService } from 'src/app/service/signup.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {


    hide = true
    hideConfirmation = true 
    signUpUser?: SignUpUser


    constructor(private readonly signupService : SignupService, private router:Router){}

    form:any = {
       email:null,
       username:null,
       password:null,
       confirm_password:null
    };

    passwordsMatch() : boolean{
      return this.form.password === this.form.password;
    }


  public signup(){

    if(!this.passwordsMatch()){
      return;

    }
    const signUpData = {email:this.form.email, username:this.form.username,password:this.form.password,confirm_password:this.form.confirm_password};
    this.signupService.createUser(signUpData).subscribe({
      next: (response) => {
        if(response){
          this.signUpUser=response;
          this.router.navigateByUrl("/login");
        }
      },
      error: (err) => {
        alert(err.message);
      }
    })
  }
}
