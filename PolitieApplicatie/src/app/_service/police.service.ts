import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {VehicleStolen} from '../_model/VehicleStolen';
import {Ride} from '../_model/Ride';

@Injectable()
export class PoliceService {

  private policeUrl = 'http://192.168.25.35:8080/RegistratieCentrale/webapi/';

  constructor(private http: HttpClient) {
  }

  private getHeaders(): HttpHeaders {
    if (sessionStorage.getItem('token') !== null) {
      return new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded',
        'Authorization': sessionStorage.getItem('token')
      });
    } else {
      return new HttpHeaders({
        'Content-Type': 'application/x-www-form-urlencoded'
      });
    }
  }

  private mergeHeaders(options: HttpHeaders): HttpHeaders {
    const headers = this.getHeaders();

    if (options != null) {
      for (const key of options.keys()) {
        headers.append(key, options.get(key));
      }
    }
    return headers;
  }

  private get<T>(url: string, options?: HttpHeaders): Observable<T> {
    return this.http.get<T>(`${this.policeUrl}${url}`, {headers: this.mergeHeaders(options)});
  }

  private put<T>(url: string, options?: HttpHeaders): Observable<T> {
    return this.http.put<T>(`${this.policeUrl}${url}`, {headers: this.mergeHeaders(options)});
  }

  getStolenVehicles(options?: HttpHeaders): Observable<VehicleStolen[]> {
    return this.get<VehicleStolen[]>('police/stolenVehicles');
  }

  getStolenVehicle(trackerId: string, options?: HttpHeaders) {
    return this.get<VehicleStolen>(`police/europe/${trackerId}/location`);
  }

  getVehicleRides(trackerId: string, options?: HttpHeaders) {
    return this.get<Ride[]>(`cartrackerdata/get/RidesByCode/${trackerId}`);
  }

  putReportStolen(trackerId: string, options?: HttpHeaders) {
    return this.put(`police/europe/${trackerId}/reportStolen`);
  }
}
