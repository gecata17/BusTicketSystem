import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

import { User } from '../model/user-model';
@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http:HttpClient) { }

  private apiServerUrl = environment.apiBaseUrl;


  getAllUsers(){
    const url = `${this.apiServerUrl}/users/all`;
    return this.http.get<User[]>(url);
  }

  getUserByUsername(username:string){
    const url = `${this.apiServerUrl}/users/${username}`;
    return this.http.get<User>(url);
  }

  
}
