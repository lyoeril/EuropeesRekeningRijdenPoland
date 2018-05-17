import { LatLng } from './LatLng';
import { Vehicle } from './Vehicle';

export class Movement {
    id: number;
    startDate: Date;
    endDate: Date;
    startLocation: LatLng;
    endLocation: LatLng;

    vehicle: Vehicle;

    constructor(_id: number, _startDate: Date, _endDate: Date, _startLocation: LatLng, _endLocation: LatLng, _vehicle: Vehicle) {
        this.id = _id;
        this.startDate = _startDate;
        this.endDate = _endDate;
        this.startLocation = _startLocation;
        this.endLocation = _endLocation;
        this.vehicle = _vehicle;
    }
}
