import {LatLng} from './LatLng';

export class VehicleStolen {
  uuid: string;
  currentLocation: LatLng;

  constructor(_cartrackerId: string, currentLocation: LatLng) {
    this.uuid = _cartrackerId;
    this.currentLocation = new LatLng(0, 0);
  }
}
