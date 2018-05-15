import { Component, OnInit, Input } from '@angular/core';
import {Invoice} from '../_model/Invoice';
import { Router } from '@angular/router';

@Component({
    selector: 'app-invoice-list',
    templateUrl: 'invoice-list.component.html',
    styleUrls: ['invoice-list.component.css']
})

export class InvoiceListComponent implements OnInit {

    @Input() invoices: Invoice[];

    constructor(private router: Router) {
    }

    ngOnInit() { }

    goToInvoice(invoice: Invoice) {
        const link = ['/invoice', invoice.date.getFullYear(), invoice.date.getMonth() + 1];
        this.router.navigate(link);
    }
}
