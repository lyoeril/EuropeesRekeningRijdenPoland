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
    searchProperty = 'ID';

    listLimit = 50;
    private isLoading = true;

    constructor(private http: HttpService) { }

    ngOnInit() {
        this.getInvoices();
    }

    getInvoices() {
        this.http.getInvoices()
            .then(response => {
                if (response !== null) {
                    this.invoices = response;
                    this.filteredInvoices = response;
                }
                this.isLoading = false;
            });
    }

    setListLimit(limit) {
        this.listLimit = limit;
    }

    setSearch(searchProperty) {
        this.searchProperty = searchProperty;
    }

    filterInvoices(filter: string) {
        if (filter !== '') {
            filter = filter.toLowerCase();
            switch (this.searchProperty) {
                case 'ID':
                    this.filteredInvoices = this.invoices.filter(v =>
                        (v.id.toString().toLowerCase().search(filter) >= 0));
                    break;
                case 'Date':
                    this.filteredInvoices = this.invoices.filter(v =>
                        (v.dateToString().toLowerCase().search(filter) >= 0));
                    break;
                case 'Cartracker':
                    this.filteredInvoices = this.invoices.filter(v =>
                        (v.cartrackerId.toString().toLowerCase().search(filter) >= 0));
                    break;
                case 'Total Sum':
                    this.filteredInvoices = this.invoices.filter(v =>
                        (v.totalSum.toString().toLowerCase().search(filter) >= 0));
                    break;
                case 'Status':
                    this.filteredInvoices = this.invoices.filter(v =>
                        (v.status.toString().toLowerCase().search(filter) >= 0));
                    break;
            }
        } else {
            this.filteredInvoices = this.invoices;
        }
    }

    recalculateInvoice(invoice: Invoice) {
        this.http.recalculateInvoice(invoice)
            .then(response => {
                if (response !== null) {
                    location.reload();
                }
            });
    }
}
