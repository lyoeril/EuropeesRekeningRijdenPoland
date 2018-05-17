import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { HttpService } from '../_services/http.service';

@Component({
    selector: 'app-header',
    templateUrl: 'header.component.html',
    styleUrls: ['header.component.css']
})

export class HeaderComponent implements OnInit {

    session = sessionStorage;

    usercreds = {
        username: '',
        password: '',
    };

    registercreds = {
        email: '',
        username: '',
        password: '',
    };

    constructor(private http: HttpService) {
    }

    ngOnInit() { }

    login(usercreds) {
        if (usercreds.username !== '' && usercreds.password !== '') {
            this.http.login(usercreds).then(data => {
                if (data !== null) {
                    document.getElementById('closeLoginModal').click();
                }
            });
        }
    }

    register(registercreds) {
        if (registercreds.email !== '' && registercreds.username !== '' && registercreds.password !== '') {
            this.http.register().then(data => {
                if (data !== null) {
                    // this.session.setUserId(data.id);
                    document.getElementById('closeRegisterModal').click();
                }
            });
        }
    }

    logout() {
        this.http.logout();
    }

    goToProfile() {
        // const link = ['/profile', this.session.getUserId()];
        // this.router.navigate(link);
    }
}
