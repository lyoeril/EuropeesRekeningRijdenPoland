import { Component, OnInit, Input } from '@angular/core';
import { Vehicle } from '../_model/Vehicle';
import { TranslateService } from '@ngx-translate/core';

@Component({
    selector: 'app-vehicle-list',
    templateUrl: 'vehicle-list.component.html',
    styleUrls: ['vehicle-list.component.css']
})

export class VehicleListComponent implements OnInit {
    @Input() vehicles: Vehicle[];

    constructor(public translate: TranslateService) { }

    ngOnInit() { }
}
