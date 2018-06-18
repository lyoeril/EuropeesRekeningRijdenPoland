import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Invoice } from '../_model/Invoice';
import { Ride } from '../_model/Ride';
import { Status } from '../_model/Status';
import { LatLng } from '../_model/LatLng';
import { Vehicle } from '../_model/Vehicle';
import { VehicleType } from '../_model/VehicleType';
import * as moment from 'moment';
import { HttpService } from '../_services/http.service';
import { TranslateService } from '@ngx-translate/core';

declare let paypal: any;

@Component({
    selector: 'app-invoice',
    templateUrl: 'invoice.component.html',
    styleUrls: ['invoice.component.css']
})

export class InvoiceComponent implements OnInit {
    id: number;
    invoice: Invoice = null;

    didPaypalScriptLoad = false;
    loading = true;

    public paypalConfig: any = {
        env: 'sandbox',
        client: {
            sandbox: 'AUo9_iqEudFdZsiFzcYr8jBK5PlPUpxFJurMeEVxLB9sttCr31ptR6kAU3v0q-jwpsIpS7s6yh4E9HWO',
            production: 'xxxxxxxxxx'
        },
        commit: true,
        payment: (data, actions) => {
            return actions.payment.create({
                payment: {
                    transactions: [
                        { amount: { total: this.invoice.totalSum, currency: 'EUR' } }
                    ]
                }
            });
        },
        onAuthorize: (data, actions) => {
            return actions.payment.execute().then((payment) => {
                this.payInvoice(this.id);
            });
        }
    };

    constructor(
        public translate: TranslateService,
        private http: HttpService,
        private route: ActivatedRoute) {
        this.route.params
            .subscribe((params: Params) => {
                this.id = +params['id'];
            });
    }

    ngOnInit() {
        this.getInvoice(this.id);
    }

    getInvoice(year: number) {
        this.http.getInvoice(year)
            .then(invoice => {
                this.invoice = invoice;

                this.getRides();
                if (!this.didPaypalScriptLoad && this.invoice.status.toLowerCase() === Status.OPEN) {
                    this.loadPaypalScript().then(() => {
                        paypal.Button.render(this.paypalConfig, '#paypal-button');
                    });
                }
            });
    }

    getRides() {
        this.http.getRidesOfInvoice(this.invoice)
            .then(response => {
                this.invoice.rides = response;
            });
    }

    payInvoice(id: number) {
        this.http.payInvoice(id)
            .then(response => {
                if (response === true) {
                    this.invoice.status = Status.PAID;
                }
            });
    }

    dateToString(date: Date): string {
        return moment(date).format('YYYY-MM-DD HH:mm');
    }

    loadPaypalScript(): Promise<any> {
        this.didPaypalScriptLoad = true;
        return new Promise((resolve, reject) => {
            const scriptElement = document.createElement('script');
            scriptElement.src = 'https://www.paypalobjects.com/api/checkout.js';
            scriptElement.onload = resolve;
            document.body.appendChild(scriptElement);
            this.loading = false;
        });
    }
}
