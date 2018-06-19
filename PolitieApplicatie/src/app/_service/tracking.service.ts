import {Injectable} from '@angular/core';
import {Subject} from 'rxjs/Subject';
import {WebsocketService} from './websocket.service';
import {LocationTimed} from '../_model/LocationTimed';
import 'rxjs/add/operator/map';

@Injectable()
export class TrackingService {
  private wsUrl = 'ws://192.168.25.35:8080/RegistratieCentrale/policeSocket/';
  public messages: Subject<LocationTimed>;

  constructor(private websocketService: WebsocketService) {
  }

  subscribe(uuid: String) {
    this.messages = <Subject<LocationTimed>>this.websocketService
      .connect(this.wsUrl + uuid)
      .map((response: MessageEvent): LocationTimed => {
        // console.log(response);
        const data = JSON.parse(response.data);
        return data as LocationTimed;
      });
  }
}
