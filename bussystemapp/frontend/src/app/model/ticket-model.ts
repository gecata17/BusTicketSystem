import { Trip } from './trip-model';
import { User } from './user-model';

export enum Status {
    available = 'AVAILABLE',
    booked = 'BOOKED',
    purchased = 'PURCHASED'
}


export class Ticket {


    id: number;
    title: string;
    status: Status;
    assignedTo: User; 
    price: number;
    trip: Trip;


    constructor(id: number, title: string, status: Status, assignedTo: User, price: number, trip: Trip) {

        this.id = id;
        this.title = title;
        this.status = status;
        this.assignedTo = assignedTo;
        this.price = price;
        this.trip = trip;
    }
}