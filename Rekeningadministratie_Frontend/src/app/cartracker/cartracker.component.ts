import { Component, OnInit, ViewChild } from '@angular/core';
import { HttpService } from '../_service/http.service';
import { Cartracker } from '../_model/Cartracker';
import { CartrackerListComponent } from '../cartracker-list/cartracker-list.component';

@Component({
    selector: 'app-cartracker',
    templateUrl: 'cartracker.component.html'
})

export class CartrackerComponent implements OnInit {

    @ViewChild(CartrackerListComponent) cartrackerList: CartrackerListComponent;

    constructor() { }

    ngOnInit() { }

    onNewCartracker(cartracker: Cartracker) {
        if (this.cartrackerList !== null) {
            this.cartrackerList.addCartracker(cartracker);
        }
    }
}
