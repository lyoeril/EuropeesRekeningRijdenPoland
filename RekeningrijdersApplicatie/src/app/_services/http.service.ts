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
import { CommonService } from './common.service';
import * as moment from 'moment';

@Injectable()
export class HttpService {

    static administrationUrl = 'http://192.168.25.35:8080/Rekeningadministratie_Overheid/api';
    constructor(
        private http: Http,
        public common: CommonService
    ) { }

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

                    this.common.username = usercreds.username;
                    this.common.password = usercreds.password;
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
                    this.common.rekeningrijderId = data.json().id;
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
                    const i = data.json();
                    resolve(new Invoice(i.id, i.cartrackerId, i.rekeningrijderId, i.totalAmount, i.status,
                        new Date(i.year, i.month)));
                }, error => {
                    this.handleError(error); resolve(null);
                });
        });
    }

    getRidesOfInvoice(invoice: Invoice, options?: Headers): Promise<Ride[]> {
        const year = invoice.date.getFullYear();
        const month = invoice.date.getMonth();
        return new Promise(resolve => {
            this.get('/rekeningrijder/rides/cartracker/' + invoice.cartrackerId + '/date/' + year + '/' + month)
                .subscribe(data => {
                    console.log(data.json());
                    const rides = [];
                    data.json().forEach(r => {
                        const ride = new Ride(r.id, moment(r.startDate, 'YYYY-MM-DDTHH:mm:ssZ[Etc/UTC]').toDate(),
                            moment(r.endDate, 'YYYY-MM-DDTHH:mm:ssZ[Etc/UTC]').toDate(), invoice.cartrackerId);
                        const locations = [];
                        r.locations.forEach(l => {
                            locations.push(new LatLng(l.latitude, l.longitude, moment(l.date, 'YYYY-MM-DDTHH:mm:ssZ[Etc/UTC]').toDate()));
                        });
                        ride.locations = locations;
                        rides.push(ride);
                    });
                    resolve(rides);
                }, error => {
                    this.handleError(error); resolve(null);
                });
        });
    }

    getUserInvoices(options?: Headers): Promise<Invoice[]> {
        return new Promise(resolve => {
            this.get('/rekeningrijder/invoices')
                .subscribe(data => {
                    const invoices = [];
                    data.json().forEach(i => {
                        invoices.push(new Invoice(i.id, i.cartrackerId, i.rekeningrijderId,
                            i.totalAmount, i.status, new Date(i.year, i.month)));
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
