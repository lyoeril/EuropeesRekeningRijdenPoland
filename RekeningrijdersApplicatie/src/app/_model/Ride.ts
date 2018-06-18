import { LatLng } from './LatLng';
import { Vehicle } from './Vehicle';

export class Ride {
    id: number;
    startDate: Date;
    endDate: Date;
    locations: LatLng[];

    vehicle: Vehicle;

    constructor(_id: number, _startDate: Date, _endDate: Date, _vehicle: Vehicle) {
        this.id = _id;
        this.startDate = _startDate;
        this.endDate = _endDate;

        this.vehicle = _vehicle;
        this.locations = [];
    }
}
