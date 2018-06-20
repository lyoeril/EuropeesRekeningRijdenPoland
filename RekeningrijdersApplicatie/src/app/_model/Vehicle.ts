import { VehicleType } from './VehicleType';

export class Vehicle {
    id: number;
    licensePlate: string;
    vehicleType: VehicleType;

    constructor(_id: number, _licensePlate: string, _type: VehicleType) {
        this.id = _id;
        this.licensePlate = _licensePlate;
        this.vehicleType = _type;
    }
}
