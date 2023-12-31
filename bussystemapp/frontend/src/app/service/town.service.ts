import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Town } from '../model/town-model';

@Injectable({
  providedIn: 'root'
})
export class TownService {

  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http:HttpClient) { }


  createTown(town:Town){
    const url = `${this.apiServerUrl}/private/api/towns`;
    return this.http.post<Town>(url,town);
  }


  deleteTown(townId:number){
    return this.http.delete<Town>(`${this.apiServerUrl}/private/api/towns/${townId}`);
  }


  getAllTowns(){

    const url = `${this.apiServerUrl}/private/api/towns/all`;
    return this.http.get<Town[]>(url);
    
  }
}
