import { Invoice } from './Invoice';
import { Car } from './Car';


export class User {
    id: number;
    username: string;
    email: string;

    invoices: Invoice[];
    cars: Car[];

    constructor(_id: number, _username: string, _email: string) {
        this.id = _id;
        this.username = _username;
        this.email = _email;

        this.invoices = [];
        this.cars = [];
    }
}
