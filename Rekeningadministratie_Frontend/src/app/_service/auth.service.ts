import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class AuthService implements CanActivate {

    constructor(private router: Router) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean | Observable<boolean> | Promise<boolean> {
        if (sessionStorage.getItem('token') !== null &&
            sessionStorage.getItem('Authorization') !== null &&
            sessionStorage.getItem('token') === sessionStorage.getItem('Authorization')) {
            return true;
        }
        this.router.navigate(['login']);
        return false;
    }
}
