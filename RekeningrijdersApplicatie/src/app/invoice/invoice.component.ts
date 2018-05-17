import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Invoice } from '../_model/Invoice';
import { Movement } from '../_model/Movement';
import { LatLng } from '../_model/LatLng';
import { Vehicle } from '../_model/Vehicle';
import { VehicleType } from '../_model/VehicleType';
import * as moment from 'moment';
import { HttpService } from '../_services/http.service';

@Component({
    selector: 'app-invoice',
    templateUrl: 'invoice.component.html',
    styleUrls: ['invoice.component.css']
})

export class InvoiceComponent implements OnInit {
    invoiceId: number;
    invoice: Invoice = null;


    constructor(private http: HttpService,
        private route: ActivatedRoute) {
        this.route.params
            .subscribe((params: Params) => {
                this.invoiceId = +params['invoiceId'];
            });
    }

    ngOnInit() {
        this.getInvoice(this.invoiceId);
    }

    getInvoice(invoiceId) {
        this.http.getInvoice(invoiceId)
            .then(invoice => {
                this.invoice = invoice;
            });
    }

    dateToString(date: Date): string {
        return moment(date).format('YYYY-MM-DD HH:mm');
    }
}
