import { Component, OnInit } from '@angular/core';
import { HttpService } from '../_service/http.service';
import { Router } from '@angular/router';

@Component({
    selector: 'app-header',
    templateUrl: 'header.component.html',
    styleUrls: ['header.component.css']
})

export class HeaderComponent implements OnInit {
    session = sessionStorage;

    constructor(
        private http: HttpService,
        private router: Router
    ) {
    }

    ngOnInit() { }

    logout() {
        this.http.logout()
            .then(response => {
                location.reload();
            });
    }
}
