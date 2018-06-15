import { Component, OnInit } from '@angular/core';
import { HttpService } from '../_service/http.service';
import { Vehicle } from '../_model/Vehicle';
import { VehicleType } from '../_model/VehicleType';
import { EnumEx } from '../_util/EnumEx';

@Component({
    selector: 'app-vehicle-list',
    templateUrl: 'vehicle-list.component.html'
})

export class VehicleListComponent implements OnInit {
    vehicles: Vehicle[] = [];
    filteredVehicles: Vehicle[] = [];
    search = '';
    searchProperty = 'ID';
    filteredType = 'All';

    listLimit = 50;

    constructor(private http: HttpService) { }

    ngOnInit() {
        this.getVehicles();
        // this.http.getUser();
    }

    getVehicles() {
        this.http.getVehicles()
            .then(response => {
                if (response !== null) {
                    this.vehicles = response;
                    this.filteredVehicles = response;
                }
            });
    }

    setListLimit(limit) {
        this.listLimit = limit;
    }

    setSearchFilter(searchProperty) {
        this.searchProperty = searchProperty;
        this.filterVehicles();
    }

    types(): string[] {
        return EnumEx.getNames(VehicleType);
    }

    setTypeFilter(type) {
        console.log(type);
        this.filteredType = type;
        this.filterVehicles();
    }

    filterVehicles() {
        if (this.search !== '') {
            this.search = this.search.toLowerCase();
            switch (this.searchProperty) {
                case 'ID':
                    this.filteredVehicles = this.vehicles.filter(v =>
                        (v.id.toString().toLowerCase().search(this.search) >= 0));
                    break;
                case 'License Plate':
                    this.filteredVehicles = this.vehicles.filter(v =>
                        (v.licensePlate.toString().toLowerCase().search(this.search) >= 0));
                    break;
                case 'Cartracker':
                    this.filteredVehicles = this.vehicles.filter(v =>
                        (v.cartrackerId.toString().toLowerCase().search(this.search) >= 0));
                    break;
            }
        } else {
            this.filteredVehicles = this.vehicles;
        }

        if (this.filteredType !== 'All') {
            this.filteredVehicles = this.filteredVehicles.filter(v =>
                (v.vehicleType.valueOf().toLowerCase().replace('_', '') === this.filteredType));
        }
    }

    edit(vehicle: Vehicle) {
        console.log('Editting...');
    }
}
