import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Invoice } from '../_model/Invoice';

@Component({
    selector: 'app-invoice',
    templateUrl: 'invoice.component.html',
    styleUrls: ['invoice.component.css']
})

export class InvoiceComponent implements OnInit {
    invoiceId: number;
    invoice: Invoice;

    // Temp
    invoices: Invoice[];

    constructor(private route: ActivatedRoute) {
        // Temp
        this.invoices = [];
        this.invoices.push(new Invoice(1, 120, true, new Date()));
        this.invoices.push(new Invoice(2, 150.50, true, new Date()));
        this.invoices.push(new Invoice(3, 200, true, new Date()));
        this.invoices.push(new Invoice(4, 220.30, true, new Date()));
        this.invoices.push(new Invoice(5, 122.63, false, new Date()));

        this.route.params
            .subscribe((params: Params) => {
                this.invoiceId = +params['id'];
                this.invoice = this.invoices.find(i => i.id === this.invoiceId);
            });
    }

    ngOnInit() { }
}
