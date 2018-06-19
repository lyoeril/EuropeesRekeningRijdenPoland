import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {HttpService} from '../_service/http.service';
import {Vehicle} from '../_model/Vehicle';
import {VehicleType} from '../_model/VehicleType';
import {PoliceService} from '../_service/police.service';
import {Ride} from '../_model/Ride';

@Component({
  selector: 'app-vehicle',
  templateUrl: './vehicle.component.html',
  styleUrls: ['./vehicle.component.css'],
  providers: [HttpService]
})

export class VehicleComponent implements OnInit {

  vehicleId: number;
// vehicle = {};
  vehicle: Vehicle = new Vehicle(0, '', '', VehicleType.UNKNOWN);
  rides: Ride[] = [];

// historyVehicles: HistoryVehicles[];

  constructor(private route: ActivatedRoute,
              private policeService: PoliceService,
              private httpService: HttpService) {
    this.route.params.subscribe(params => this.vehicleId = params['id']);
  }

  ngOnInit() {
    this.getVehicle(this.vehicleId);
  }

  getVehicle(vehicleId: number) {
    this.httpService.findVehicleById(vehicleId)
      .then(vehicle => {
        this.policeService.getStolenVehicle(vehicle.cartrackerHardware)
          .subscribe(
            stolen => {
              if (stolen !== null) {
                vehicle.isStolen = true;
                vehicle.currentLocation = stolen.currentLocation;
              }
            }, error => {
              // nothing, HTTP 400 expected if not stolen
            });
        this.vehicle = vehicle;
        this.getVehicleRides(vehicle.cartrackerHardware);
        // console.log(this.vehicle);
      });
  }

  getVehicleRides(trackerId: string) {
    this.policeService.getVehicleRides(trackerId)
      .subscribe(rides => {
        this.rides = rides;
      });
  }

  onBtnReport() {
    this.policeService.putReportStolen(this.vehicle.cartrackerHardware)
      .subscribe(
        data => this.getVehicle(this.vehicleId)
      );
  }
}
