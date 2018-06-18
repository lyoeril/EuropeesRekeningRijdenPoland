import { Component, OnInit, Input } from '@angular/core';
import {Invoice} from '../_model/Invoice';
import { Router } from '@angular/router';
import { TranslateService } from '@ngx-translate/core';

@Component({
    selector: 'app-invoice-list',
    templateUrl: 'invoice-list.component.html',
    styleUrls: ['invoice-list.component.css']
})

export class InvoiceListComponent implements OnInit {

    @Input() invoices: Invoice[];

    constructor(public translate: TranslateService, private router: Router) {
    }

    ngOnInit() { }

    goToInvoice(invoice: Invoice) {
        const link = ['/invoice', invoice.id];
        this.router.navigate(link);
    }
}
