import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Invoice } from '../_model/Invoice';
import { Movement } from '../_model/Movement';
import { LatLng } from '../_model/LatLng';
import { Car } from '../_model/Car';
import * as moment from 'moment';

@Component({
    selector: 'app-invoice',
    templateUrl: 'invoice.component.html',
    styleUrls: ['invoice.component.css']
})

export class InvoiceComponent implements OnInit {
    invoice: Invoice;

    // Temp
    invoices: Invoice[];

    constructor(private route: ActivatedRoute) {
        // Temp
        this.invoices = [];
        this.invoices.push(new Invoice(1, 120, true, new Date(2018, 0, 1)));
        this.invoices.push(new Invoice(2, 150.50, true, new Date(2018, 1, 1)));
        this.invoices.push(new Invoice(3, 200, true, new Date(2018, 2, 1)));
        this.invoices.push(new Invoice(4, 220.30, true, new Date(2018, 3, 1)));
        this.invoices.push(new Invoice(5, 122.63, false, new Date(2018, 4, 1)));

        const movements = [];
        movements.push(new Movement(1, new Date(2018, 0, 2, 12, 30), new Date(2018, 0, 2, 13, 30),
            new LatLng(51.12345, 7.12345), new LatLng(51.22345, 8.12345), new Car(1, '12-AB-34')));
        movements.push(new Movement(1, new Date(2018, 0, 2, 15, 30), new Date(2018, 0, 2, 16, 30),
            new LatLng(51.12345, 7.12345), new LatLng(51.22345, 8.12345), new Car(1, '12-AB-34')));
        movements.push(new Movement(1, new Date(2018, 0, 15, 12, 30), new Date(2018, 0, 15, 13, 30),
            new LatLng(51.12345, 7.12345), new LatLng(51.22345, 8.12345), new Car(1, '12-AB-34')));
        movements.push(new Movement(1, new Date(2018, 0, 24, 12, 30), new Date(2018, 0, 24, 13, 30),
            new LatLng(51.12345, 7.12345), new LatLng(51.22345, 8.12345), new Car(1, '34-CD-56')));

        this.invoices[0].movements = movements;

        this.route.params
            .subscribe((params: Params) => {
                const year = +params['year'];
                const month = +params['month'] - 1;
                this.invoice = this.invoices.find(i =>
                    i.date.getFullYear() === year &&
                    i.date.getMonth() === month
                );
            });
    }

    ngOnInit() { }

    dateToString(date: Date): string {
        return moment(date).format('YYYY-MM-DD HH:mm');
    }
}
