import { Invoice } from './Invoice';
import { Vehicle } from './Vehicle';


export class User {
    id: number;
    username: string;

    invoices: Invoice[];
    vehicles: Vehicle[];

    constructor(_id: number, _username: string) {
        this.id = _id;
        this.username = _username;

        this.invoices = [];
        this.vehicles = [];
    }



}
