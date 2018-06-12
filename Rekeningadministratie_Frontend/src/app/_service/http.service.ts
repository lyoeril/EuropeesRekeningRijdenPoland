import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Http, Headers, Response } from '@angular/http';
import * as queryString from 'querystring';
import { Vehicle } from '../_model/Vehicle';
import { Invoice } from '../_model/Invoice';
import { Cartracker } from '../_model/Cartracker';
import { KmRate } from '../_model/KmRate';

@Injectable()
export class HttpService {
    static administrationUrl = 'http://192.168.25.14:8080/Rekeningadministratie_Overheid/api';
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


    // Users ========================================================================================== Users
    login(usercreds: any, options?: Headers): Promise<boolean | string> {
        return new Promise(resolve => {
            this.post('/login', usercreds)
                .subscribe(data => {
                    sessionStorage.setItem('token', data.headers.get('Authorization'));
                    this.get('/overheid')
                        .subscribe(data2 => {
                            if (data2.status === 202) {
                                resolve(true);
                            } else {
                                sessionStorage.removeItem('token');
                                resolve(null);
                            }
                        }, error => {
                            this.handleError(error); resolve(null);
                        });
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

    // Cartrackers ============================================================================== Cartrackers
    getCartrackers(options?: Headers): Promise<Cartracker[]> {
        return new Promise(resolve => {
            this.get('/overheid/cartrackers')
                .subscribe(data => {
                    const cartrackers = [];
                    data.json().forEach(c => {
                        cartrackers.push(new Cartracker(c.id, c.hardware));
                    });
                    resolve(cartrackers);
                }, error => {
                    this.handleError(error); resolve(null);
                });
        });
    }

    addCartracker(cartracker: any, options?: Headers): Promise<Cartracker> {
        return new Promise(resolve => {
            this.post('/overheid/cartrackers/new', cartracker)
                .subscribe(data => {
                    resolve(new Cartracker(data.json().id, data.json().hardware));
                }, error => {
                    this.handleError(error); resolve(null);
                });
        });
    }

    // Invoices ==================================================================================== Invoices
    getInvoices(options?: Headers): Promise<Invoice[]> {
        return new Promise(resolve => {
            this.get('/overheid/invoices')
                .subscribe(data => {
                    const invoices = [];
                    data.json().forEach(i => {
                        invoices.push(new Invoice(i.id, new Date(i.year, i.month - 1), i.cartrackerId, i.totalAmount, i.status));
                    });
                    resolve(invoices);
                }, error => {
                    this.handleError(error); resolve(null);
                });
        });
    }

    // Invoices ==================================================================================== Invoices
    getKmRates(options?: Headers): Promise<KmRate[]> {
        return new Promise(resolve => {
            this.get('/overheid/kmrates')
                .subscribe(data => {
                    const kmRates = [];
                    console.log(data.json());
                    resolve(kmRates);
                }, error => {
                    this.handleError(error); resolve(null);
                });
        });
    }

    // Vehicles ==================================================================================== Vehicles
    getVehicles(options?: Headers): Promise<Vehicle[]> {
        return new Promise(resolve => {
            this.get('/overheid/vehicles')
                .subscribe(data => {
                    const vehicles = [];
                    console.log(data.json());
                    data.json().forEach(v => {
                        const newVehicle = new Vehicle(v.id, v.licensePlate, v.vehicleType, v.cartrackerId);
                        v.ownersHistory.forEach(u => {
                            newVehicle.ownerHistory.push(u);
                        });
                        vehicles.push(newVehicle);
                    });
                    resolve(vehicles);
                }, error => {
                    this.handleError(error); resolve(null);
                });
        });
    }

    getUser(options?: Headers): Promise<any> {
        return new Promise(resolve => {
            this.get('/overheid')
                .subscribe(data => {
                    console.log(data.json());
                    resolve(data.json());
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
