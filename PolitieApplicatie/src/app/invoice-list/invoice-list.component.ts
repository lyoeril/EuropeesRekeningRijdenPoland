import { Component, OnInit } from '@angular/core';
import { HttpService } from '../_service/http.service';
import { Invoice } from '../_model/Invoice';

@Component({
    selector: 'app-invoice-list',
    templateUrl: 'invoice-list.component.html'
})

export class InvoiceListComponent implements OnInit {

    invoices: Invoice[] = [];
    filteredInvoices: Invoice[] = [];

    constructor(private http: HttpService) { }

    ngOnInit() {
        this.getInvoices();
    }

    getInvoices() {
        this.http.getInvoices()
            .then(response => {
                this.invoices = response;
                this.filteredInvoices = response;
            });
    }

    filterInvoices(filter: string) {
        if (filter !== '') {
            filter = filter.toLowerCase();
            this.filteredInvoices = this.invoices.filter(i => (
                i.id.toString().toLowerCase().search(filter) >= 0 ||
                i.status.toLowerCase().search(filter) >= 0
            ));
        } else {
            this.filteredInvoices = this.invoices;
        }
    }
}
