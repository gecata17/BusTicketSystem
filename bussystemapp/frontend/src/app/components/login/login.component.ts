import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from 'src/app/service/login.service';
import { TokenStorageService } from 'src/app/service/token.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})


export class LoginComponent implements OnInit {
  isLoggedIn = false;
  success = false;
  errorMessage = "";
  

  constructor(private loginService: LoginService, private tokenStorage: TokenStorageService,private router:Router) { }

  ngOnInit(): void {
    this.isLoggedIn = false;
  }

  login(username: any, password: any) {
    this.loginService.login(username, password).subscribe({
      next: (response) => {
        if (response) {
          console.log(response);
          this.tokenStorage.saveUser(response.username);
          this.tokenStorage.saveToken(response.token);
          this.isLoggedIn = true;
          this.router.navigateByUrl("/ticketsearch");
        }
      },
      error: (err) => {
        this.errorMessage = "Invalid credentials";
      }

    })

  }

}
