import { Component, OnInit } from '@angular/core';
import { HttpService } from '../_service/http.service';
import { Router } from '@angular/router';

@Component({
    selector: 'app-login',
    templateUrl: 'login.component.html',
    styleUrls: ['login.component.css']
})

export class LoginComponent implements OnInit {

    usercreds = {
        username: '',
        password: '',
    };

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
                }
                console.log(response);
            });
    }
}
