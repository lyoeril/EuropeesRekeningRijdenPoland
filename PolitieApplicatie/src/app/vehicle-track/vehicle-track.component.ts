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
  vehicle: Vehicle = new Vehicle(0, '', VehicleType.UNKNOWN);

  // google maps initZoom level
  initZoom = 8;
  // initial center position for the map
  // initLat = 51.673858;
  // initLng = 7.815982;
  strokeColor = 'blue';

  points: LatLng[] = [
    {latitude: 51.37549219, longitude: 21.01700706},
    {latitude: 54.48840076, longitude: 17.69540708},
    {latitude: 52.34433101, longitude: 16.33552231},
    {latitude: 54.34100669, longitude: 20.44279629},
    {latitude: 50.97168638, longitude: 21.87112246},
    {latitude: 50.15178873, longitude: 22.59289910},
    {latitude: 53.32828199, longitude: 14.44203025},
    {latitude: 52.63737885, longitude: 22.95445161},
    {latitude: 51.41950412, longitude: 18.67633493},
    {latitude: 51.18235159, longitude: 16.78935665},
    {latitude: 51.30320306, longitude: 21.35172063},
    {latitude: 53.52185293, longitude: 17.82326733},
    {latitude: 52.52156934, longitude: 19.09924290},
    {latitude: 53.32432259, longitude: 19.28711134},
    {latitude: 51.85982371, longitude: 16.78749147},
    {latitude: 53.24707253, longitude: 20.03987768}
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
    // this.websocket.subscribe(this.vehicle);
  }
}
