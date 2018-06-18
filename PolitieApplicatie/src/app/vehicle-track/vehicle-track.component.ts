import {Component, OnInit} from '@angular/core';
import {Vehicle} from '../_model/Vehicle';
import {VehicleType} from '../_model/VehicleType';
import {ActivatedRoute} from '@angular/router';
import {HttpService} from '../_service/http.service';
import {AgmPolylinePoint} from '@agm/core';

@Component({
  selector: 'app-vehicle-track',
  templateUrl: './vehicle-track.component.html',
  styleUrls: ['./vehicle-track.component.css']
})
export class VehicleTrackComponent implements OnInit {

  vehicleId: number;
  vehicle: Vehicle = new Vehicle(0, '', VehicleType.UNKNOWN);

  // google maps zoom level
  zoom = 8;
  // initial center position for the map
  lat = 51.673858;
  lng = 7.815982;

  points: AgmPolylinePoint[] = [
    new AgmPolylinePoint()
  ];

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
