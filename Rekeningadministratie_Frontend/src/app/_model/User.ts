import { Invoice } from './Invoice';
import { Vehicle } from './Vehicle';


export class User {
    id: number;
    username: string;
    email: string;
    address: string;

    invoices: Invoice[];
    vehicles: Vehicle[];

    constructor(_id: number, _username: string, _email: string, _address: string) {
        this.id = _id;
        this.username = _username;
        this.email = _email;
        this.address = _address;

        this.invoices = [];
        this.vehicles = [];
    }



}
