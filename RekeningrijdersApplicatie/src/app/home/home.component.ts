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
        this.getUser(+sessionStorage.getItem('userId'));
    }

    getUser(userId: number) {
        this.http.getUser(userId)
            .then(user => {
                if (user) {
                    this.user = user;
                    this.fillUserInvoices(userId);
                    this.fillUserVehicles(userId);
                }
            });
    }

    fillUserInvoices(userId: number) {
        this.http.getUserInvoices(userId)
            .then(invoices => {
                this.user.invoices = invoices;
            });
    }

    fillUserVehicles(userId: number) {
        this.http.getUserVehicles(userId)
            .then(vehicles => {
                this.user.vehicles = vehicles;
            });
    }

}
