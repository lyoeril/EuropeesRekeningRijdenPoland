import { Component, OnInit } from '@angular/core';
import { User } from '../_model/User';
import { Car } from '../_model/Car';
import { Invoice } from '../_model/Invoice';

@Component({
    selector: 'app-home',
    templateUrl: 'home.component.html'
})

export class HomeComponent implements OnInit {
    user: User;

    constructor() {
        this.user = new User(1, 'xXQuickScope360Xx', 'jan-peter@mail.com');

        const invoices = [];
        invoices.push(new Invoice(1, 120, true, new Date(2018, 4)));
        invoices.push(new Invoice(2, 150.50, true, new Date(2018, 3)));
        invoices.push(new Invoice(3, 200, true, new Date(2018, 2)));
        invoices.push(new Invoice(4, 220.30, true, new Date(2018, 1)));
        invoices.push(new Invoice(5, 122.63, false, new Date(2018, 0)));
        this.user.invoices = invoices;

        const cars = [];
        cars.push(new Car(1, '12-AB-34'));
        cars.push(new Car(2, '34-CD-56'));
        cars.push(new Car(3, '56-EF-78'));
        this.user.cars = cars;
    }

    ngOnInit() { }

}
