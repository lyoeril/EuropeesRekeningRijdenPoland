import { LatLng } from './LatLng';
import { Car } from './Car';

export class Movement {
    id: number;
    startDate: Date;
    endDate: Date;
    startLocation: LatLng;
    endLocation: LatLng;

    car: Car;

    constructor() {
    }
}
