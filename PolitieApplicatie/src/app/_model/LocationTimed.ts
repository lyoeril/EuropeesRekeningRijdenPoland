import { LatLng } from './LatLng';

export class LocationTimed {
  id: number;
  time: Date;
  location: LatLng;

  constructor(_id: number, _time: Date, _location: LatLng) {
    this.id = _id;
    this.time = _time;
    this.location = _location;
  }
}
