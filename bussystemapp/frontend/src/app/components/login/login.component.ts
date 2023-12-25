import { Component, OnInit } from '@angular/core';
import { LoginService } from 'src/app/service/login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

@Component({
  selector: 'login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})


export class LoginComponent implements OnInit {


  hide = true;

  form: any = {
    username: null,
    password: null
  };

  isLoggedIn = false;
  success = false;
  errorMessage = "";

  constructor(private loginService: LoginService) { }



  ngOnInit(): void {
    this.isLoggedIn = false;
  }


  login() {

    const loginData = { username: this.form.username, password: this.form.password };

    this.loginService.login(loginData.username, loginData.password).subscribe({

      next: (response) => {

        if (response) {
          this.isLoggedIn = true;

        }
      },
      error: (err) => {
        alert(err.message);
      }

    })

  }

}
