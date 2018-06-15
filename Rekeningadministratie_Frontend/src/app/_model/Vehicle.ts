import { VehicleType } from './VehicleType';
import { User } from './User';

export class Vehicle {
    id: number;
    licensePlate: string;
    vehicleType: VehicleType;
    cartrackerId: number;

    ownerHistory: number[];

    constructor(_id: number, _licensePlate: string, _type: VehicleType, _cartrackerId: number) {
        this.id = _id;
        this.licensePlate = _licensePlate;
        this.vehicleType = _type;
        this.cartrackerId = _cartrackerId;

        this.ownerHistory = [];
    }
}
