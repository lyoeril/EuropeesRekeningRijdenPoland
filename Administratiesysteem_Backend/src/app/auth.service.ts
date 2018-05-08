import {Injectable} from '@angular/core';
import {JwtHelper, tokenNotExpired} from 'angular2-jwt';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {User} from './user';
import 'rxjs/add/operator/map';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class AuthService {

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  getToken(): string {
    return localStorage.getItem('token');
  }

  isAuthenticated(): boolean {
    const token = this.getToken();
    return tokenNotExpired(null, token);
  }

  constructor(private http: HttpClient) {
  }

  public getLoggedInUsername(): string {
    const token = this.getToken();
    const jwtHelper = new JwtHelper();
    return jwtHelper.decodeToken(token).sub;
  }

  public login(username: string, password: string): Observable<any> {
    const user = new User(username, password);
    return this
      .http
      .post('AANPASSEN NAAR SERVERURL', user, {headers: this.httpOptions.headers, observe: 'response'});
  }
}
