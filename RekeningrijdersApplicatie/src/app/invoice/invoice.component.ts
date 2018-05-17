import { Component, OnInit, AfterViewChecked } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Invoice } from '../_model/Invoice';
import { Movement } from '../_model/Movement';
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
    invoiceId: number;
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
                // show success page
                this.invoice.paid = true;
            });
        }
    };

    constructor(
        public translate: TranslateService,
        private http: HttpService,
        private route: ActivatedRoute) {
        this.route.params
            .subscribe((params: Params) => {
                this.invoiceId = +params['invoiceId'];
            });
    }

    ngOnInit() {
        this.getInvoice(this.invoiceId);

        if (!this.didPaypalScriptLoad) {
            this.loadPaypalScript().then(() => {
                paypal.Button.render(this.paypalConfig, '#paypal-button');
            });
        }
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