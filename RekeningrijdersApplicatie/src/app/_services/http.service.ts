import { Injectable } from '@angular/core';
import { Http, Headers, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import * as queryString from 'querystring';
import { User } from '../_model/User';
import { Invoice } from '../_model/Invoice';
import { Vehicle } from '../_model/Vehicle';
import { VehicleType } from '../_model/VehicleType';
import { Ride } from '../_model/Ride';
import { LatLng } from '../_model/LatLng';

@Injectable()
export class HttpService {

    static administrationUrl = 'http://192.168.25.35:8080/Rekeningadministratie_Overheid/api';
    constructor(private http: Http) { }

    getHeaders(): Headers {
        if (sessionStorage.getItem('token') !== null) {
            return new Headers({
                'Content-Type': 'application/x-www-form-urlencoded',
                'Authorization': sessionStorage.getItem('token')
            });
        } else {
            return new Headers({
                'Content-Type': 'application/x-www-form-urlencoded'
            });
        }
    }

    get(url: string, options?: Headers): Observable<Response> {
        const headers: Headers = this.getHeaders();

        if (options != null) {
            for (const key of options.keys()) {
                headers.append(key, options.get(key));
            }
        }

        return this.http.get(`${HttpService.administrationUrl}${url}`, { headers: headers });
    }

    post(url: string, body: any, options?: Headers): Observable<Response> {
        const headers: Headers = this.getHeaders();

        if (options != null) {
            for (const key of options.keys()) {
                headers.append(key, options.get(key));
            }
        }

        body = this.convertToUrlEncoded(body);
        return this.http.post(`${HttpService.administrationUrl}${url}`, body, { headers: headers });
    }

    delete(url: string, body: any, options?: Headers): Observable<Response> {
        const headers: Headers = this.getHeaders();

        if (options != null) {
            for (const key of options.keys()) {
                headers.append(key, options.get(key));
            }
        }

        body = this.convertToUrlEncoded(body);
        return this.http.delete(`${HttpService.administrationUrl}${url}`, { headers: headers });
    }

    put(url: string, body: any, options?: Headers): Observable<Response> {
        const headers: Headers = this.getHeaders();

        if (options != null) {
            for (const key of options.keys()) {
                headers.append(key, options.get(key));
            }
        }

        body = this.convertToUrlEncoded(body);
        return this.http.put(`${HttpService.administrationUrl}${url}`, body, { headers: headers });
    }


    // Users ========================================================================================== Users
    login(usercreds: any, options?: Headers): Promise<boolean | string> {
        return new Promise(resolve => {
            this.post('/login', usercreds, options)
                .subscribe(data => {
                    sessionStorage.setItem('token', data.headers.get('Authorization'));
                    sessionStorage.setItem('password', usercreds.password);
                    resolve(true);
                }, error => {
                    if (error.status === 401) {
                        resolve('usernamepasswordnomatch');
                    }
                    this.handleError(error); resolve(null);
                });
        });
    }

    logout(options?: Headers): Promise<boolean> {
        return new Promise(resolve => {
            sessionStorage.clear();
            resolve(true);
        });
    }

    register(user: any, options?: Headers): Promise<boolean | string> {
        return new Promise(resolve => {
            this.post('/register/rekeningrijder', user, options)
                .subscribe(data => {
                    resolve(true);
                }, error => {
                    if (error.status === 409) {
                        resolve('accountexists');
                    }
                    this.handleError(error); resolve('error');
                });
        });
    }

    getUser(options?: Headers): Promise<User> {
        return new Promise(resolve => {
            this.get('/rekeningrijder')
                .subscribe(data => {
                    resolve(new User(data.json().id, data.json().username, data.json().email, data.json().address));
                }, error => {
                    this.handleError(error); resolve(null);
                });
        });
    }

    updateUser(userData: any, options?: Headers): Promise<any> {
        return new Promise(resolve => {
            this.put('/rekeningrijder/update', userData)
                .subscribe(data => {
                    resolve(true);
                }, error => {
                    this.handleError(error); resolve(null);
                });
        });
    }

    // Invoices ==================================================================================== Invoices
    getInvoice(id: number, options?: Headers): Promise<Invoice> {
        return new Promise(resolve => {
            this.get('/rekeningrijder/invoices/' + id)
                .subscribe(data => {
                    console.log(data);
                    resolve(new Invoice(data.json().id, data.json().cartrackerId, data.json().totalAmount, data.json().status,
                        new Date(data.json().year, data.json().month)));
                }, error => {
                    this.handleError(error); resolve(null);
                });
            // switch (invoiceId) {
            //     case 1:
            //         const invoice = new Invoice(1, 120, true, new Date(2018, 4));
            //         const movements = [];
            //         movements.push(new Movement(1, new Date(2018, 0, 2, 12, 30), new Date(2018, 0, 2, 13, 30),
            //             new LatLng(51.12345, 7.12345), new LatLng(51.22345, 8.12345), new Vehicle(1, '12-AB-34', VehicleType.Car)));
            //         movements.push(new Movement(1, new Date(2018, 0, 2, 15, 30), new Date(2018, 0, 2, 16, 30),
            //             new LatLng(51.12345, 7.12345), new LatLng(51.22345, 8.12345), new Vehicle(1, '12-AB-34', VehicleType.Car)));
            //         movements.push(new Movement(1, new Date(2018, 0, 15, 12, 30), new Date(2018, 0, 15, 13, 30),
            //             new LatLng(51.12345, 7.12345), new LatLng(51.22345, 8.12345), new Vehicle(1, '12-AB-34', VehicleType.Car)));
            //         movements.push(new Movement(1, new Date(2018, 0, 24, 12, 30), new Date(2018, 0, 24, 13, 30),
            // tslint:disable-next-line:max-line-length
            //             new LatLng(51.12345, 7.12345), new LatLng(51.22345, 8.12345), new Vehicle(1, '34-CD-56', VehicleType.Motorcycle)));
            //         invoice.movements = movements;
            //         resolve(invoice);
            //         break;
            //     case 2:
            //         resolve(new Invoice(2, 150.50, true, new Date(2018, 3)));
            //         break;
            //     case 3:
            //         resolve(new Invoice(3, 200, true, new Date(2018, 2)));
            //         break;
            //     case 4:
            //         resolve(new Invoice(4, 220.30, true, new Date(2018, 1)));
            //         break;
            //     case 5:
            //         resolve(new Invoice(5, 122.63, false, new Date(2018, 0)));
            //         break;
            //     default:
            //         resolve(null);
            //         break;
            // }
        });
    }

    // getRidesOfInvoice(vehicles: Vehicle[], options?: Headers): Promise<Ride[]> {
    //     return new Promise(resolve => {
    //         vehicles.forEach(v => {
    //             this
    //         })
    //     })
    // }

    getUserInvoices(options?: Headers): Promise<Invoice[]> {
        return new Promise(resolve => {
            this.get('/rekeningrijder/invoices')
                .subscribe(data => {
                    const invoices = [];
                    data.json().forEach(i => {
                        invoices.push(new Invoice(i.id, i.cartrackerId, i.totalAmount, i.status, new Date(i.year, i.month)));
                    });
                    resolve(invoices);
                }, error => {
                    this.handleError(error); resolve(null);
                });
        });
    }

    payInvoice(id: number, options?: Headers): Promise<any> {
        const body = {};
        return new Promise(resolve => {
            this.put('/rekeningrijder/invoices/' + id, body)
                .subscribe(data => {
                    resolve(true);
                }, error => {
                    this.handleError(error); resolve(null);
                });
        });
    }

    // Vehicles ==================================================================================== Vehicles
    getUserVehicles(options?: Headers): Promise<Vehicle[]> {
        return new Promise(resolve => {
            this.get('/rekeningrijder/cars')
                .subscribe(data => {
                    const vehicles = [];
                    data.json().forEach(v => {
                        vehicles.push(new Vehicle(v.id, v.licensePlate, v.vehicleType));
                    });
                    resolve(vehicles);
                }, error => {
                    this.handleError(error); resolve(null);
                });
        });
    }

    removeVehicle(vehicleId: number, options?: Headers): Promise<boolean> {
        const body = {};
        return new Promise(resolve => {
            this.delete('/rekeningrijder/cars/' + vehicleId, body)
                .subscribe(data => {
                    console.log(data);
                    resolve(true);
                }, error => {
                    this.handleError(error); resolve(null);
                });
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
