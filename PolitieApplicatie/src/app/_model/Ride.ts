import {LocationTimed} from './LocationTimed';

export class Ride {
  id: number;
  startDate: Date;
  endDate: Date;
  locations: LocationTimed[];

  constructor(_id: number, _startDate: Date, _endDate: Date) {
    this.id = _id;
    this.startDate = _startDate;
    this.endDate = _endDate;
    this.locations = [];
  }
}
