import { Ticket } from "./ticket-model";


export class User{
    username: string;
    password: string;
    email: string;
    tickets: Ticket[]


    constructor(
        username:string,
        password: string,
        email:string,
        tickets:Ticket[]
    ){
        this.username=username;
        this.password=password;
        this.email=email;
        this.tickets=tickets;
    }
}