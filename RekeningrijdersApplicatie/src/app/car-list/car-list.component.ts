import { Component, OnInit, Input } from '@angular/core';
import { Car } from '../_model/Car';

@Component({
    selector: 'app-car-list',
    templateUrl: 'car-list.component.html',
    styleUrls: ['car-list.component.css']
})

export class CarListComponent implements OnInit {
    @Input() cars: Car[];

    constructor() { }

    ngOnInit() { }
}
