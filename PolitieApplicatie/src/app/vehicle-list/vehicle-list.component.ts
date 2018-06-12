import { Component, OnInit, Input } from '@angular/core';
import { Vehicle } from '../_model/Vehicle';
import { TranslateService } from '@ngx-translate/core';
import { HttpService } from '../_services/http.service';

@Component({
    selector: 'app-vehicle-list',
    templateUrl: 'vehicle-list.component.html',
    styleUrls: ['vehicle-list.component.css']
})

export class VehicleListComponent implements OnInit {
    @Input() vehicles: Vehicle[];

    toBeRemoved: Vehicle = null;

    constructor(
        public translate: TranslateService,
        private http: HttpService) { }

    ngOnInit() { }

    setToBeRemoved(vehicle: Vehicle) {
        this.toBeRemoved = vehicle;
    }

    removeVehicle() {
        this.http.removeVehicle(this.toBeRemoved.id)
            .then(response => {
                if (response === true) {
                    this.vehicles = this.vehicles.filter(v => v.id === this.toBeRemoved.id);
                    location.reload();
                }
            });
    }
}
