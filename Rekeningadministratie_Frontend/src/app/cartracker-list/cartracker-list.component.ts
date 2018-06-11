import { Component, OnInit } from '@angular/core';
import { HttpService } from '../_service/http.service';

@Component({
    selector: 'app-cartracker-list',
    templateUrl: 'cartracker-list.component.html'
})

export class CartrackerListComponent implements OnInit {

    cartrackers: { id: number, hardware: string }[] = [];
    filteredCartrackers: { id: number, hardware: string }[] = [];

    constructor(private http: HttpService) { }

    ngOnInit() {
        this.getCartrackers();
    }

    getCartrackers() {
        this.http.getCartrackers()
            .then(response => {
                this.cartrackers = response;
                this.filteredCartrackers = response;
            });
    }

    filterCartrackers(filter: string) {
        if (filter !== '') {
            filter = filter.toLowerCase();
            this.filteredCartrackers = this.cartrackers.filter(c => (
                c.id.toString().toLowerCase().search(filter) >= 0 ||
                c.hardware.toLowerCase().search(filter) >= 0
            ));
        } else {
            this.filteredCartrackers = this.cartrackers;
        }
    }
}
