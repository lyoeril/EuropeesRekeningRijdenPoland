import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Http, Headers, Response } from '@angular/http';
import * as queryString from 'querystring';

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

    // Other ========================================================================================== Other
    handleError(error) {
        const message = 'Http Error: ' + error.status + ' - ' + error.statusText;
        console.log(message);
    }

    convertToUrlEncoded(obj): any {
        return queryString.stringify(obj);
    }
}
