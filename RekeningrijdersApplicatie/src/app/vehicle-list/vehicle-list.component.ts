import { Component, OnInit, Input } from '@angular/core';
import { Vehicle } from '../_model/Vehicle';

@Component({
    selector: 'app-vehicle-list',
    templateUrl: 'vehicle-list.component.html',
    styleUrls: ['vehicle-list.component.css']
})

export class VehicleListComponent implements OnInit {
    @Input() vehicles: Vehicle[];

    constructor() { }

    ngOnInit() { }
}
