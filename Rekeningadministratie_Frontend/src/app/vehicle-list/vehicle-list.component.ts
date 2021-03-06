import { Component, OnInit, ViewChild } from '@angular/core';
import { HttpService } from '../_service/http.service';
import { Vehicle } from '../_model/Vehicle';
import { VehicleType } from '../_model/VehicleType';
import { EnumEx } from '../_util/EnumEx';
import { Cartracker } from '../_model/Cartracker';
import { User } from '../_model/User';

@Component({
    selector: 'app-vehicle-list',
    templateUrl: 'vehicle-list.component.html'
})

export class VehicleListComponent implements OnInit {

    private vehicles: Vehicle[] = [];
    private filteredVehicles: Vehicle[] = [];
    private search = '';
    private searchProperty = 'ID';
    private filteredType: VehicleType | string = 'All';

    public listLimit = 50;
    private isLoading = true;

    editVehicle = null;

    cartrackerQuery = '';
    cartrackerResults: Cartracker[] = [];

    constructor(private http: HttpService) { }

    ngOnInit() {
        this.getVehicles();
    }

    getVehicles() {
        this.http.getVehicles()
            .then(response => {
                if (response !== null) {
                    this.vehicles = response;
                    this.filteredVehicles = response;


                    if (this.filteredVehicles.length > 0) {
                        this.editVehicle = this.filteredVehicles[0];
                    }

                    this.isLoading = false;
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
                        (v.cartracker.hardware.toString().toLowerCase().search(this.search) >= 0));
                    break;
            }
        } else {
            this.filteredVehicles = this.vehicles;
        }

        if (this.filteredType !== 'All') {
            this.filteredVehicles = this.filteredVehicles.filter(v =>
                (v.vehicleType === this.filteredType));
        }
    }

    edit(vehicle: Vehicle) {
        this.editVehicle = new Vehicle(vehicle.id, vehicle.licensePlate, vehicle.vehicleType, vehicle.cartracker);
    }

    link(vehicle: Vehicle) {
        this.editVehicle = new Vehicle(vehicle.id, vehicle.licensePlate, vehicle.vehicleType, vehicle.cartracker);
        if (this.editVehicle.cartracker !== null) {
            this.cartrackerQuery = this.editVehicle.cartracker.hardware;
        } else {
            this.cartrackerQuery = '';
        }
    }

    setEditVehicleType(type: VehicleType) {
        this.editVehicle.vehicleType = type;
    }

    searchCartracker() {
        if (this.cartrackerQuery !== '') {
            this.http.getCartrackersByHardware(this.cartrackerQuery)
                .then(response => {
                    if (response !== null) {
                        this.cartrackerResults = response;
                    } else {
                        this.cartrackerResults = [];
                    }
                });
        }
    }

    setCartracker(cartracker: Cartracker) {
        this.editVehicle.cartracker = cartracker;
        this.cartrackerQuery = cartracker.hardware;
    }

    updateVehicle() {
        this.http.updateVehicle(this.editVehicle)
            .then(response => {
                if (response !== null) {
                    location.reload();
                }
            });
    }

    linkVehicle() {
        this.http.linkVehicle(this.editVehicle, this.editVehicle.cartracker)
            .then(response => {
                if (response !== null) {
                    location.reload();
                }
            });
    }
}
