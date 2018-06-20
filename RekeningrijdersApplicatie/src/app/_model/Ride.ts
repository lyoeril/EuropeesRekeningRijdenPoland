import { LatLng } from './LatLng';
import { Vehicle } from './Vehicle';

export class Ride {
    id: number;
    startDate: Date;
    endDate: Date;
    locations: LatLng[];
    cartrackerId: number;

    constructor(_id: number, _startDate: Date, _endDate: Date, _cartrackerId: number) {
        this.id = _id;
        this.startDate = _startDate;
        this.endDate = _endDate;

        this.cartrackerId = _cartrackerId;
        this.locations = [];
    }
}
