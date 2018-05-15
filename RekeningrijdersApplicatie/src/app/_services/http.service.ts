import { Injectable } from '@angular/core';
import { Http, Headers, Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import * as queryString from 'querystring';

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
    login(usercreds: any, options?: Headers): Promise<any> {
        return null;
    }

    logout(options?: Headers): Promise<any> {
        return null;
    }

    register(options?: Headers): Promise<any> {
        return null;
    }

    getUser(options?: Headers): Promise<any> {
        return null;
    }

    updateUser(options?: Headers): Promise<any> {
        return null;
    }

    // Invoices ==================================================================================== Invoices
    getInvoice(body: any, options?: Headers): Promise<any> {
        return null;
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
