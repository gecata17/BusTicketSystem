import { Trip } from './trip-model';
import { User } from './user-model';

export enum Status {
    available = 'AVAILABLE',
    booked = 'BOOKED',
    purchased = 'PURCHASED'
}


export class Ticket {


    id: number;
    title: string | null;
    status: Status | null;
    assignedTo: string | null; 
    price: number | null;
    trip: Trip | null;
    duration: string = "";
    distance: string= "";


    constructor(id: number, title: string | null, status: Status | null, assignedTo: string | null, price: number | null, trip: Trip | null) {
        this.id = id;
        this.title = title  ;
        this.status = status;
        this.assignedTo = assignedTo;
        this.price = price;
        this.trip = trip;
    }
}

export class TicketUpdateRequest {
    assignedTo: string | null;
    trip : number | null;
    status: Status | null;

    constructor(assignedTo: string | null, status: Status | null = null, trip: number | null = null) {
        this.assignedTo = assignedTo;
        this.trip = trip;
        this.status = status;
    }
}