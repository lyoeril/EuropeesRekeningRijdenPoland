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
    private kmRates: KmRate[] = [];
    private filteredKmRates: KmRate[] = [];
    private search = '';
    private searchProperty = 'Region';
    private filteredType: VehicleType | string = 'All';

    private listLimit = 50;

    private editKmRate: KmRate = null;

    private isLoading = true;

    constructor(private http: HttpService) { }

    ngOnInit() {
        this.getKmRates();
    }

    getKmRates() {
        this.http.getKmRates()
            .then(response => {
                if (response !== null) {
                    this.kmRates = response;
                    this.filteredKmRates = response;
                    this.isLoading = false;
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
                (k.vehicleType === this.filteredType));
        }
    }

    edit(kmRate: KmRate) {
        this.editKmRate = new KmRate(kmRate.id, kmRate.region, kmRate.vehicleType, kmRate.rate);
    }

    updateKmRate() {
        this.http.updateKmRate(this.editKmRate)
            .then(response => {
                location.reload();
            });
    }
}
