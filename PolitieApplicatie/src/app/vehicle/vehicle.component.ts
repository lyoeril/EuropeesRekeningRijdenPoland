import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpService} from '../_service/http.service';
import {Vehicle} from '../_model/Vehicle';
import {VehicleType} from '../_model/VehicleType';

@Component({
  selector: 'app-vehicle',
  templateUrl: './vehicle.component.html',
  styleUrls: ['./vehicle.component.css'],
  providers: [HttpService]
})

export class VehicleComponent implements OnInit {

  vehicleId: number;
// vehicle = {};
  vehicle: Vehicle = new Vehicle(0, '', VehicleType.UNKNOWN);

// historyVehicles: HistoryVehicles[];

  constructor(private route: ActivatedRoute, private http: HttpService) {
    this.route.params.subscribe(params => this.vehicleId = params['id']);
  }

  ngOnInit() {
    this.getVehicle(this.vehicleId);
  }

  getVehicle(vehicleId: number) {
    this.http.findVehicleById(vehicleId)
      .then(vehicle => {
        this.vehicle = vehicle;
        console.log(this.vehicle);
      });
  }
}
