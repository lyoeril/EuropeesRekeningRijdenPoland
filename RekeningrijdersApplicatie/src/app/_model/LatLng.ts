export class LatLng {
    latitude: number;
    longitude: number;
    date: Date;

    constructor(_lat: number, _lng: number, _date: Date) {
        this.latitude = _lat;
        this.longitude = _lng;
        this.date = _date;
    }
}
