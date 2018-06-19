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

  // google maps initZoom level
  initZoom = 8;
  // initial center position for the map
  // initLat = 51.673858;
  // initLng = 7.815982;
  strokeColor = 'blue';

  points: LatLng[] = [
    /*{lat: 51.37549219, lng: 21.01700706},
    {lat: 54.48840076, lng: 17.69540708},
    {lat: 52.34433101, lng: 16.33552231},
    {lat: 54.34100669, lng: 20.44279629},
    {lat: 50.97168638, lng: 21.87112246},
    {lat: 50.15178873, lng: 22.59289910},
    {lat: 53.32828199, lng: 14.44203025},
    {lat: 52.63737885, lng: 22.95445161},
    {lat: 51.41950412, lng: 18.67633493},
    {lat: 51.18235159, lng: 16.78935665},
    {lat: 51.30320306, lng: 21.35172063},
    {lat: 53.52185293, lng: 17.82326733},
    {lat: 52.52156934, lng: 19.09924290},
    {lat: 53.32432259, lng: 19.28711134},
    {lat: 51.85982371, lng: 16.78749147},
    {lat: 53.24707253, lng: 20.03987768}*/
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
      }
    );
  }
}
