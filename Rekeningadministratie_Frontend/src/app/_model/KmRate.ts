import { VehicleType } from './VehicleType';

export class KmRate {
    id: number;
    region: string;
    vehicleType: VehicleType;
    rate: number;

    constructor(_id: number, _region: string, _vehicleType: VehicleType, _rate: number) {
        this.id = _id;
        this.region = _region;
        this.vehicleType = _vehicleType;
        this.rate = _rate;
    }
}
