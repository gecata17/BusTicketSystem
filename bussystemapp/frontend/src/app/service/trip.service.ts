import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Trip } from '../model/trip-model';

@Injectable({
  providedIn: 'root'
})
export class TripService {

  private apiServerUrl = environment.apiBaseUrl;
  constructor(private http:HttpClient) { }


  createTrip(trip:Trip){
    const url = `${this.apiServerUrl}/private/api/trips`;
    return this.http.post<Trip>(url,trip);
  }

  updateTrip(trip:Trip){
    return this.http.put<Trip>(`${this.apiServerUrl}/private/api/trips/update/${trip.description}`,trip);
  }

  getTripsByTown(title:string | null){
    const url = `${this.apiServerUrl}/private/api/trips/${title}`;
    return this.http.get<Trip[]>(url);

  }

  deleteTrip(trip:Trip){
    return this.http.delete<Trip>(`${this.apiServerUrl}/private/api/trips/${trip.description}`);
  }
}
