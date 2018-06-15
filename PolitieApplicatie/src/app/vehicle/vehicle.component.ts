import { Component, OnInit } from '@angular/core';
import {ActivatedRoute } from '@angular/router';
import {HttpService} from '../_service/http.service';

@Component({
  selector: 'app-vehicle',
  templateUrl: './vehicle.component.html',
  styleUrls: ['./vehicle.component.css'],
  providers: [HttpService]
})

export class VehicleComponent implements OnInit {

vehicleId: number;
vehicle = {};
//historyVehicles: HistoryVehicles[];

constructor(private route: ActivatedRoute, private http: HttpService) {
  	this.route.params.subscribe(params => this.vehicleId = params['id']);
  }


ngOnInit() {
   /* if (this.vehicleId && this.vehicleId.length > 0) {
      this.http.findVehicleById(this.vehicleId).subscribe(res => {
        if (res && res.length === 1) {
          this.vehicle = res[0];
          this.http.getHistory(this.vehicleId, 10).subscribe(historyVehicles => {
            this.historyVehicles = historyVehicles;
          });
        }
      });
    }
    */
  }
}


	
	

