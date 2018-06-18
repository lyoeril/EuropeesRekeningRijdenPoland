import { VehicleType } from './VehicleType';
import { User } from './User';
import { Cartracker } from './Cartracker';

export class Vehicle {
    id: number;
    licensePlate: string;
    currentOwner: User;
    vehicleType: VehicleType;
    cartracker: Cartracker;

    ownerHistory: User[];

    constructor(_id: number, _licensePlate: string, _type: VehicleType, _cartracker: Cartracker) {
        this.id = _id;
        this.licensePlate = _licensePlate;
        this.vehicleType = _type;
        this.cartracker = _cartracker;

        this.ownerHistory = [];
        this.currentOwner = null;
    }
}
