import {Component, OnInit} from '@angular/core';
import {HttpService} from '../_service/http.service';
import {Vehicle} from '../_model/Vehicle';
import {Router} from '@angular/router';
import {PoliceService} from '../_service/police.service';

@Component({
  selector: 'app-vehicle-list',
  templateUrl: 'vehicle-list.component.html'
})

export class VehicleListComponent implements OnInit {
  vehicles: Vehicle[] = [];
  filteredVehicles: Vehicle[] = [];

  constructor(private httpService: HttpService,
              private policeService: PoliceService,
              private router: Router) {
  }

  ngOnInit() {
    this.getVehicles();
  }

  getVehicles() {
    this.httpService.getVehicles()
      .then(vehicles => {
        this.policeService.getStolenVehicles()
          .subscribe(stolenVehicles => {
            stolenVehicles.forEach(stolenVehicle => {
              const stolen = vehicles.find(v => v.cartrackerHardware === stolenVehicle.uuid);
              stolen.isStolen = true;
              stolen.currentLocation = stolenVehicle.currentLocation;
            });
          });
        this.vehicles = vehicles;
        this.filteredVehicles = vehicles;
      });

    // this.httpService.getCartrackers();
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
