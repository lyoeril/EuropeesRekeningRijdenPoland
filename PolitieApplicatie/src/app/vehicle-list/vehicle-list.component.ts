import {Component, OnInit} from '@angular/core';
import {HttpService} from '../_service/http.service';
import {Vehicle} from '../_model/Vehicle';
import {Router} from '@angular/router';

@Component({
  selector: 'app-vehicle-list',
  templateUrl: 'vehicle-list.component.html'
})

export class VehicleListComponent implements OnInit {
  vehicles: Vehicle[] = [];
  filteredVehicles: Vehicle[] = [];

  constructor(private http: HttpService,
              private router: Router) {
  }

  ngOnInit() {
    this.getVehicles();
  
  }

  getVehicles() {
    this.http.getVehicles()
      .then(response => {
        this.vehicles = response;
        this.filteredVehicles = response;
      });

    this.http.getCartrackers();
  }

  filterVehicles(filter: string) {
    if (filter !== '') {
      filter = filter.toLowerCase();
      this.filteredVehicles = this.vehicles.filter(v =>
        (v.id.toString().toLowerCase().search(filter) >= 0 ||
          v.licensePlate.toLowerCase().search(filter) >= 0 ||
          v.vehicleType.toLowerCase().search(filter) >= 0));
    } else {
      this.filteredVehicles = this.vehicles;
    }
  }

  goToVehicleDetails(vehicle: Vehicle) {
    this.router.navigate(['/vehicles', vehicle.id]);
  }

}
