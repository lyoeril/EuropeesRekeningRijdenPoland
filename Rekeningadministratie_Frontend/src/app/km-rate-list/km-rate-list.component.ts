import { Component, OnInit } from '@angular/core';
import { KmRate } from '../_model/KmRate';
import { HttpService } from '../_service/http.service';
import { EnumEx } from '../_util/EnumEx';
import { VehicleType } from '../_model/VehicleType';

@Component({
    selector: 'app-km-rate-list',
    templateUrl: 'km-rate-list.component.html'
})

export class KmRateListComponent implements OnInit {
    kmRates: KmRate[] = [];
    filteredKmRates: KmRate[] = [];
    search = '';
    searchProperty = 'Region';
    filteredType = 'All';

    listLimit = 50;

    constructor(private http: HttpService) { }

    ngOnInit() {
        this.getInvoices();
    }

    getInvoices() {
        this.http.getKmRates()
            .then(response => {
                if (response !== null) {
                    this.kmRates = response;
                    this.filteredKmRates = response;
                }
            });
    }

    setListLimit(limit) {
        this.listLimit = limit;
    }

    setSearchFilter(searchProperty) {
        this.searchProperty = searchProperty;
        this.filterKmRates();
    }

    types(): string[] {
        return EnumEx.getNames(VehicleType);
    }

    setTypeFilter(type) {
        console.log(type);
        this.filteredType = type;
        this.filterKmRates();
    }

    filterKmRates() {
        if (this.search !== '') {
            this.search = this.search.toLowerCase();
            switch (this.searchProperty) {
                case 'Region':
                    this.filteredKmRates = this.kmRates.filter(k =>
                        (k.region.toLowerCase().search(this.search) >= 0));
                    break;
                case 'Rate':
                    this.filteredKmRates = this.kmRates.filter(k =>
                        (k.rate.toString().toLowerCase().search(this.search) >= 0));
                    break;
            }
        } else {
            this.filteredKmRates = this.kmRates;
        }

        if (this.filteredType !== 'All') {
            this.filteredKmRates = this.filteredKmRates.filter(k =>
                (k.vehicleType.valueOf().toLowerCase().replace('_', '') === this.filteredType));
        }
    }

    edit(kmRate: KmRate) {
        console.log('Editting...');
    }
}
