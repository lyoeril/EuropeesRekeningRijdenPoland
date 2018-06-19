import {Component, OnInit} from '@angular/core';
import {Vehicle} from '../_model/Vehicle';
import {VehicleType} from '../_model/VehicleType';
import {ActivatedRoute} from '@angular/router';
import {HttpService} from '../_service/http.service';
import {AgmPolylinePoint} from '@agm/core';
import {LatLng} from '../_model/LatLng';
import {TrackingService} from '../_service/tracking.service';

@Component({
  selector: 'app-vehicle-track',
  templateUrl: './vehicle-track.component.html',
  styleUrls: ['./vehicle-track.component.css']
})
export class VehicleTrackComponent implements OnInit {

  vehicleId: number;
  vehicle: Vehicle = new Vehicle(0, '', '', VehicleType.UNKNOWN);

  // google maps zoom level
  zoom = 8;
  // initial center position for the map
  // initLat = 51.673858;
  // initLng = 7.815982;
  strokeColor = 'blue';

  points: LatLng[] = [
    {lat: 51.7730347, lng: 19.3405098}
  ];

  constructor(private route: ActivatedRoute,
              private http: HttpService,
              private websocket: TrackingService) {
    this.route.params.subscribe(params => this.vehicleId = params['id']);
  }

  ngOnInit() {
    this.http.findVehicleById(this.vehicleId)
      .then(
        vehicle => {
          this.vehicle = vehicle;
          this.subscribeTracking();
        }
      );
  }

  subscribeTracking() {
    this.websocket.subscribe(this.vehicle.cartrackerHardware);
    this.websocket.messages.subscribe(
      msg => {
        this.points.unshift(msg.location);
        if (this.points.length === 2) {
          const pnt = this.points[1];
          if (pnt.lat === 51.7730347 &&
            pnt.lng === 19.3405098) {
            this.points.pop();
            this.zoom = 12;
          }
        }
      }
    );
  }
}
