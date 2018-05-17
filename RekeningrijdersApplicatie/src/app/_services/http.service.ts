import { Injectable } from '@angular/core';
import { Http, Headers, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import * as queryString from 'querystring';
import { User } from '../_model/User';
import { Invoice } from '../_model/Invoice';
import { Vehicle } from '../_model/Vehicle';
import { VehicleType } from '../_model/VehicleType';
import { Movement } from '../_model/Movement';
import { LatLng } from '../_model/LatLng';

@Injectable()
export class HttpService {

    static administrationUrl = '';
    constructor(private http: Http) { }

    getGetHeaders(): Headers {
        return new Headers({
            'Content-Type': 'application/json'
        });
    }

    getPostHeaders(): Headers {
        return new Headers({
            'Content-Type': 'application/x-www-form-urlencoded'
        });
    }

    get(url: string, options?: Headers): Observable<Response> {
        const headers: Headers = this.getGetHeaders();

        if (options != null) {
            for (const key of options.keys()) {
                headers.append(key, options.get(key));
            }
        }

        return this.http.get(`${HttpService.administrationUrl}${url}`, { headers: headers });
    }

    post(url: string, body: any, options?: Headers): Observable<Response> {
        const headers: Headers = this.getPostHeaders();

        if (options != null) {
            for (const key of options.keys()) {
                headers.append(key, options.get(key));
            }
        }

        body = this.convertToUrlEncoded(body);
        return this.http.post(`${HttpService.administrationUrl}${url}`, body, { headers: headers });
    }

    // Users ========================================================================================== Users
    login(usercreds: any, options?: Headers): Promise<boolean> {
        return new Promise(resolve => {
            sessionStorage.setItem('userId', '1');
            resolve(true);
        });
    }

    logout(options?: Headers): Promise<boolean> {
        return new Promise(resolve => {
            sessionStorage.clear();
            resolve(true);
        });
    }

    register(options?: Headers): Promise<any> {
        return null;
    }

    getUser(userId: number, options?: Headers): Promise<User> {
        return new Promise(resolve => {
            if (userId === 1) {
                resolve(new User(1, 'xXQuickScope360Xx', 'jan-peter@mail.com', 'Rachelsmolen 1, Eindhoven'));
            } else {
                resolve(null);
            }
        });
    }

    updateUser(options?: Headers): Promise<any> {
        return null;
    }

    // Invoices ==================================================================================== Invoices
    getInvoice(invoiceId: number, options?: Headers): Promise<Invoice> {
        return new Promise(resolve => {
            switch (invoiceId) {
                case 1:
                    const invoice = new Invoice(1, 120, true, new Date(2018, 4));
                    const movements = [];
                    movements.push(new Movement(1, new Date(2018, 0, 2, 12, 30), new Date(2018, 0, 2, 13, 30),
                        new LatLng(51.12345, 7.12345), new LatLng(51.22345, 8.12345), new Vehicle(1, '12-AB-34', VehicleType.Car)));
                    movements.push(new Movement(1, new Date(2018, 0, 2, 15, 30), new Date(2018, 0, 2, 16, 30),
                        new LatLng(51.12345, 7.12345), new LatLng(51.22345, 8.12345), new Vehicle(1, '12-AB-34', VehicleType.Car)));
                    movements.push(new Movement(1, new Date(2018, 0, 15, 12, 30), new Date(2018, 0, 15, 13, 30),
                        new LatLng(51.12345, 7.12345), new LatLng(51.22345, 8.12345), new Vehicle(1, '12-AB-34', VehicleType.Car)));
                    movements.push(new Movement(1, new Date(2018, 0, 24, 12, 30), new Date(2018, 0, 24, 13, 30),
                        new LatLng(51.12345, 7.12345), new LatLng(51.22345, 8.12345), new Vehicle(1, '34-CD-56', VehicleType.Motorcycle)));
                    invoice.movements = movements;
                    resolve(invoice);
                    break;
                case 2:
                    resolve(new Invoice(2, 150.50, true, new Date(2018, 3)));
                    break;
                case 3:
                    resolve(new Invoice(3, 200, true, new Date(2018, 2)));
                    break;
                case 4:
                    resolve(new Invoice(4, 220.30, true, new Date(2018, 1)));
                    break;
                case 5:
                    resolve(new Invoice(5, 122.63, false, new Date(2018, 0)));
                    break;
                default:
                    resolve(null);
                    break;
            }
        });
    }

    getUserInvoices(userId: number, options?: Headers): Promise<Invoice[]> {
        return new Promise(resolve => {
            if (userId === 1) {
                const invoices = [];
                invoices.push(new Invoice(1, 120, true, new Date(2018, 4)));
                invoices.push(new Invoice(2, 150.50, true, new Date(2018, 3)));
                invoices.push(new Invoice(3, 200, true, new Date(2018, 2)));
                invoices.push(new Invoice(4, 220.30, true, new Date(2018, 1)));
                invoices.push(new Invoice(5, 122.63, false, new Date(2018, 0)));
                resolve(invoices);
            } else {
                resolve(null);
            }
        });
    }

    // Vehicles ==================================================================================== Vehicles
    getUserVehicles(userId: number, options?: Headers): Promise<Vehicle[]> {
        return new Promise(resolve => {
            if (userId === 1) {
                const vehicles = [];
                vehicles.push(new Vehicle(1, '12-AB-34', VehicleType.Car));
                vehicles.push(new Vehicle(2, '34-CD-56', VehicleType.Motorcycle));
                vehicles.push(new Vehicle(3, '56-EF-78', VehicleType.Car));
                resolve(vehicles);
            } else {
                resolve(null);
            }
        });
    }

    // Other ========================================================================================== Other
    handleError(error) {
        const message = 'Http Error: ' + error.status + ' - ' + error.statusText;
        console.log(message);
    }

    convertToUrlEncoded(obj): any {
        return queryString.stringify(obj);
    }
}
