import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Http, Headers, Response } from '@angular/http';
import * as queryString from 'querystring';
import { Vehicle } from '../_model/Vehicle';
import { Invoice } from '../_model/Invoice';
import { Cartracker } from '../_model/Cartracker';
import { KmRate } from '../_model/KmRate';
import { User } from '../_model/User';
import { VehicleType } from '../_model/VehicleType';

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
            this.post('/login', usercreds)
                .subscribe(data => {
                    sessionStorage.setItem('token', data.headers.get('Authorization'));
                    this.get('/overheid')
                        .subscribe(data2 => {
                            if (data2.status === 202) {
                                sessionStorage.setItem('Authorization', data.headers.get('Authorization'));
                                resolve(true);
                            } else {
                                sessionStorage.removeItem('token');
                                resolve(null);
                            }
                        }, error => {
                            sessionStorage.removeItem('token');
                            if (error.status === 401) {
                                resolve('This account is not authorized to login here');
                            }
                            this.handleError(error); resolve(null);
                        });
                }, error => {
                    if (error.status === 401) {
                        resolve('Username and password do not match');
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

    getUsersByUsername(username: string, options?: Headers): Promise<User[]> {
        return new Promise(resolve => {
            this.get('/overheid/rekeningrijders/username/' + username)
                .subscribe(data => {
                    const users = [];
                    data.json().forEach(u => {
                        users.push(new User(u.id, u.username));
                    });
                    resolve(users);
                }, error => {
                    this.handleError(error); resolve(null);
                });

        });
    }

    getUser(id: number, options?: Headers): Promise<User> {
        return new Promise(resolve => {
            this.get('/overheid/rekeningrijders/' + id)
                .subscribe(data => {
                    const user = new User(data.json().id, data.json().username);
                    resolve(user);
                }, error => {
                    this.handleError(error); resolve(null);
                });
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

    getCartracker(id: number, options?: Headers): Promise<Cartracker> {
        return new Promise(resolve => {
            if (id !== 0) {
                this.get('/overheid/cartrackers/' + id)
                    .subscribe(data => {
                        const cartracker = new Cartracker(data.json().id, data.json().hardware);
                        resolve(cartracker);
                    }, error => {
                        this.handleError(error); resolve(null);
                    });
            } else {
                resolve(null);
            }
        });
    }

    getCartrackersByHardware(hardware: string, options?: Headers): Promise<Cartracker[]> {
        return new Promise(resolve => {
            this.get('/overheid/cartrackers/hardware/' + hardware)
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
                        this.getUser(i.rekeningrijderId)
                            .then(user => {
                                invoices.push(new Invoice(i.id, new Date(i.year, i.month - 1),
                                    i.cartrackerId, i.totalAmount, i.status, user));
                            });
                    });
                    resolve(invoices);
                }, error => {
                    this.handleError(error); resolve(null);
                });
        });
    }

    recalculateInvoice(invoice: Invoice, options?: Headers): Promise<Invoice> {
        const year = invoice.date.getFullYear();
        const month = invoice.date.getMonth();
        return new Promise(resolve => {
            this.get('/overheid/invoices/' + invoice.user.id + '/' + invoice.cartrackerId + '/' + year + '/' + month)
                .subscribe(data => {
                    const i = data.json();
                    this.getUser(i.rekeningrijderId)
                        .then(user => {
                            resolve(new Invoice(i.id, new Date(i.year, i.month), i.cartrackerId, i.totalAmount, i.status, user));
                        });
                }, error => {
                    this.handleError(error); resolve(null);
                });
        });
    }

    // KmRates ==================================================================================== KmRates
    getKmRates(options?: Headers): Promise<KmRate[]> {
        return new Promise(resolve => {
            this.get('/overheid/kmrates')
                .subscribe(data => {
                    const kmRates = [];
                    data.json().forEach(k => {
                        kmRates.push(new KmRate(k.id, k.region, VehicleType.AUTOBUS, k.rates.AUTOBUS));
                        kmRates.push(new KmRate(k.id, k.region, VehicleType.PASSENGER_CAR, k.rates.PASSENGER_CAR));
                        kmRates.push(new KmRate(k.id, k.region, VehicleType.TRUCK, k.rates.TRUCK));
                        kmRates.push(new KmRate(k.id, k.region, VehicleType.UNKNOWN, k.rates.UNKNOWN));
                        kmRates.push(new KmRate(k.id, k.region, VehicleType.VAN, k.rates.VAN));
                    });
                    resolve(kmRates);
                }, error => {
                    this.handleError(error); resolve(null);
                });
        });
    }

    updateKmRate(kmRate: KmRate, options?: Headers): Promise<KmRate> {
        const body = { rate: kmRate.rate };
        return new Promise(resolve => {
            this.put('/overheid/kmrates/' + kmRate.region + '/' + kmRate.vehicleType.toString(), body)
                .subscribe(data => {
                    resolve(new KmRate(data.json().id, data.json().region, data.json().vehicleType, data.json().rate));
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
                    data.json().forEach(v => {
                        this.getCartracker(v.cartrackerId)
                            .then(cartracker => {
                                let newVehicle;
                                if (cartracker !== null) {
                                    newVehicle = new Vehicle(v.id, v.licensePlate, v.vehicleType,
                                        new Cartracker(cartracker.id, cartracker.hardware));
                                } else {
                                    newVehicle = new Vehicle(v.id, v.licensePlate, v.vehicleType, null);
                                }

                                v.ownersHistory.forEach(u => {
                                    this.getUser(u).then(user => {
                                        if (user !== null) {
                                            newVehicle.ownerHistory.push(user);
                                            if (v.ownersHistory[v.ownersHistory.length - 1] === u) {
                                                newVehicle.currentOwner = user;
                                            }
                                        }
                                    });
                                });
                                vehicles.push(newVehicle);
                            });
                    });
                    resolve(vehicles);
                }, error => {
                    this.handleError(error); resolve(null);
                });
        });
    }

    updateVehicle(vehicle: Vehicle, options?: Headers): Promise<any> {
        const cartrackerId = vehicle.cartracker !== null ? vehicle.cartracker.id : 0;
        const body = { licensePlate: vehicle.licensePlate, vehicleType: vehicle.vehicleType, cartrackerId: cartrackerId };
        return new Promise(resolve => {
            this.put('/overheid/vehicles/' + vehicle.id + '/update', body)
                .subscribe(data => {
                    resolve(true);
                }, error => {
                    this.handleError(error); resolve(null);
                });
        });
    }

    linkVehicle(vehicle: Vehicle, cartracker: Cartracker, options?: Headers): Promise<any> {
        const body = { cartrackerUUID: cartracker.hardware };
        return new Promise(resolve => {
            this.put('/overheid/vehicles/' + vehicle.id + '/link', body)
                .subscribe(data => {
                    resolve(true);
                }, error => {
                    this.handleError(error); resolve(null);
                });
        });
    }

    // Other ========================================================================================== Other
    handleError(error) {
        if (error.status === 401) {
            sessionStorage.clear();
            location.reload();
        }
        const message = 'Http Error: ' + error.status + ' - ' + error.statusText;
        console.log(message);
    }

    convertToUrlEncoded(obj): any {
        return queryString.stringify(obj);
    }
}
