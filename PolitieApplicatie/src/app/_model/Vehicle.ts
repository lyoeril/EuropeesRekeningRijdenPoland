import { VehicleType } from './VehicleType';
import {LatLng} from './LatLng';

export class Vehicle {
    id: number;
    cartrackerHardware: string;
    licensePlate: string;
    vehicleType: VehicleType;
    ownerHistory: number[];
    isStolen: boolean;
    currentLocation: LatLng;

    constructor(_id: number, _cartrackerHardware: string, _licensePlate: string, _type: VehicleType) {
        this.id = _id;
        this.cartrackerHardware = _cartrackerHardware;
        this.licensePlate = _licensePlate;
        this.vehicleType = _type;
        this.ownerHistory = [];
        this.isStolen = false;
        this.currentLocation = new LatLng(0, 0);
    }
}
