import { Component, OnInit } from '@angular/core';
import { HttpService } from '../_service/http.service';
import { Router } from '@angular/router';

@Component({
    selector: 'app-login',
    templateUrl: 'login.component.html'
})

export class LoginComponent implements OnInit {

    private usercreds = {
        username: '',
        password: '',
    };

    private alert = null;

    constructor(
        private http: HttpService,
        private router: Router
    ) { }

    ngOnInit() { }

    login() {
        this.http.login(this.usercreds)
            .then(response => {
                if (response === true) {
                    this.router.navigate(['']);
                } else if (response !== null) {
                    this.alert = response;
                }
            });
    }
}
