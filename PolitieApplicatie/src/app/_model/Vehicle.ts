import { VehicleType } from './VehicleType';
import { User } from './User';

export class Vehicle {
    id: number;
    licensePlate: string;
    vehicleType: VehicleType;

    ownerHistory: number[];

    constructor(_id: number, _licensePlate: string, _type: VehicleType) {
        this.id = _id;
        this.licensePlate = _licensePlate;
        this.vehicleType = _type;

        this.ownerHistory = [];
    }
}
