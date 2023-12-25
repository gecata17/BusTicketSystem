import { Ticket } from "./ticket-model";
import { Town } from "./town-model";



export class Trip {

    description: string;
    startTown: Town;
    endTown: Town;
    seats: number;
    dateOfDeparture: Date;
    dateOfArrival: Date;
    assignedTickets: Ticket[];


    constructor(
        description: string,
        startTown: Town,
        endTown: Town,
        seats: number,
        dateOfDeparture: Date,
        dateOfArrival: Date,
        assignedTickets: Ticket[], 
    ) {

        this.description=description;
        this.startTown=startTown;
        this.endTown=endTown;
        this.seats=seats;
        this.dateOfDeparture=dateOfDeparture;
        this.dateOfArrival=dateOfArrival;
        this.assignedTickets=assignedTickets;
    }
}