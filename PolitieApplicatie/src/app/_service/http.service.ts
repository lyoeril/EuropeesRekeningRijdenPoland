import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Http, Headers, Response } from '@angular/http';
import * as queryString from 'querystring';
import { Vehicle } from '../_model/Vehicle';
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


    // Users ========================================================================================== Users
    login(usercreds: any, options?: Headers): Promise<boolean | string> {
        return new Promise(resolve => {
            this.post('/login', usercreds)
                .subscribe(data => {
                    sessionStorage.setItem('token', data.headers.get('Authorization'));
                    // sessionStorage.setItem('password', usercreds.password);
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

    // Cartrackers ============================================================================== Cartrackers
    getCartrackers(options?: Headers): Promise<any> {
        return new Promise(resolve => {
            this.get('/overheid/cartrackers')
                .subscribe(data => {
                    const cartrackers = [];
                    console.log(data.json());
                    resolve(cartrackers);
                }, error => {
                    this.handleError(error); resolve(null);
                });
        });
    }

    addCartracker(cartracker: any, options?: Headers): Promise<any> {
        return new Promise(resolve => {
            this.post('/overheid/cartrackers/new', cartracker)
                .subscribe(data => {
                    console.log(data);
                    resolve(true);
                });
        });
    }



    // Vehicles ==================================================================================== Vehicles
    getVehicles(options?: Headers): Promise<Vehicle[]> {
        return new Promise(resolve => {
            this.get('/politie/vehicles')
                .subscribe(data => {
                    const vehicles = [];
                    data.json().forEach(v => {
                        const newVehicle = new Vehicle(v.id, v.licensePlate, v.vehicleType);
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


    findVehicleById(vehiclesId: Number, options?: Headers): Promise<Vehicle> {
        return new Promise(resolve => {
            this.get('/politie/vehicles/' + vehiclesId)
                .subscribe(data => {
                    console.log(data);
                    resolve(new Vehicle(data.json().id, data.json().licensePlate, data.json().vehicleType));
                }, error => {
                    this.handleError(error); resolve(null);
                });
        });
    }

//NOG TESTEN
   findOwners(vehiclesId: Number, options?: Headers): Promise<User[]> {
        return new Promise(resolve => {
             this.get('/politie/vehicle/' + vehiclesId + '/owners')
                .subscribe(data => {
                    const users = [];
                    data.json().forEach(u => {
                        const newUser = new User(u.id, u.username, u.email, u.address);
                        users.push(newUser);
                    });
                    resolve(users);
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
