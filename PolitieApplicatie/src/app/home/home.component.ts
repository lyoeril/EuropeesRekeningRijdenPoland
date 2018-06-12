import { Component, OnInit } from '@angular/core';
import { User } from '../_model/User';
import { Vehicle } from '../_model/Vehicle';
import { Invoice } from '../_model/Invoice';
import { HttpService } from '../_services/http.service';

@Component({
    selector: 'app-home',
    templateUrl: 'home.component.html'
})

export class HomeComponent implements OnInit {
    user: User = null;
    session = sessionStorage;

    constructor(private http: HttpService) {
    }

    ngOnInit() {
        this.getUser();
    }

    getUser() {
        this.http.getUser()
            .then(user => {
                if (user) {
                    this.user = user;
                    this.fillUserInvoices();
                    this.fillUserVehicles();
                }
            });
    }

    fillUserInvoices() {
        this.http.getUserInvoices()
            .then(invoices => {
                this.user.invoices = invoices;
            });
    }

    fillUserVehicles() {
        this.http.getUserVehicles()
            .then(vehicles => {
                this.user.vehicles = vehicles;
            });
    }

}
