import { Component, OnInit } from '@angular/core';
import {Invoice} from '../_model/Invoice';
import { Router } from '@angular/router';

@Component({
    selector: 'app-invoice-list',
    templateUrl: 'invoice-list.component.html',
    styleUrls: ['invoice-list.component.css']
})

export class InvoiceListComponent implements OnInit {

    invoices: Invoice[];

    constructor(private router: Router) {
        this.invoices = [];
        this.invoices.push(new Invoice(1, 120, true, new Date()));
        this.invoices.push(new Invoice(2, 150.50, true, new Date()));
        this.invoices.push(new Invoice(3, 200, true, new Date()));
        this.invoices.push(new Invoice(4, 220.30, true, new Date()));
        this.invoices.push(new Invoice(5, 122.63, false, new Date()));
    }

    ngOnInit() { }

    goToInvoice(invoice: Invoice) {
        const link = ['/invoice', invoice.id];
        this.router.navigate(link);
    }
}
