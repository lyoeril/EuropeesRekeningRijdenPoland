import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { HttpService } from '../_services/http.service';
import { TranslateService } from '@ngx-translate/core';

@Component({
    selector: 'app-header',
    templateUrl: 'header.component.html',
    styleUrls: ['header.component.css']
})

export class HeaderComponent implements OnInit {

    session = sessionStorage;
    usingLang;

    usercreds = {
        username: '',
        password: '',
    };

    registercreds = {
        email: '',
        username: '',
        password: '',
    };

    constructor(public translate: TranslateService, private http: HttpService) {
        translate.addLangs(['en', 'nl']);
        translate.setDefaultLang('en');

        if (localStorage.getItem('lang') !== null) {
            this.changeLang(localStorage.getItem('lang'));
        } else {
            this.changeLang('en');
        }
    }

    changeLang(lang: string) {
        this.usingLang = lang;
        this.translate.use(lang);
        localStorage.setItem('lang', lang);
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
