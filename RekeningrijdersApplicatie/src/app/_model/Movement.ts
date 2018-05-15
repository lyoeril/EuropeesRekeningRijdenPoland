import { LatLng } from './LatLng';
import { Car } from './Car';

export class Movement {
    id: number;
    startDate: Date;
    endDate: Date;
    startLocation: LatLng;
    endLocation: LatLng;

    car: Car;

    constructor(_id: number, _startDate: Date, _endDate: Date, _startLocation: LatLng, _endLocation: LatLng, _car: Car) {
        this.id = _id;
        this.startDate = _startDate;
        this.endDate = _endDate;
        this.startLocation = _startLocation;
        this.endLocation = _endLocation;
        this.car = _car;
    }
}
