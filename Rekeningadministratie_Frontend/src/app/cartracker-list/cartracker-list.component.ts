import { Component, OnInit } from '@angular/core';
import { HttpService } from '../_service/http.service';
import { Cartracker } from '../_model/Cartracker';

@Component({
    selector: 'app-cartracker-list',
    templateUrl: 'cartracker-list.component.html'
})

export class CartrackerListComponent implements OnInit {

    cartrackers: Cartracker[] = [];
    filteredCartrackers: Cartracker[] = [];
    search = '';
    searchProperty = 'ID';

    listLimit = 50;

    constructor(private http: HttpService) { }

    ngOnInit() {
        this.getCartrackers();
    }

    getCartrackers() {
        this.http.getCartrackers()
            .then(response => {
                if (response !== null) {
                    this.cartrackers = response;
                    this.filteredCartrackers = response;
                }
            });
    }

    setListLimit(limit) {
        this.listLimit = limit;
    }

    setSearch(searchProperty) {
        this.searchProperty = searchProperty;
    }

    filterCartrackers() {
        if (this.search !== '') {
            this.search = this.search.toLowerCase();
            switch (this.searchProperty) {
                case 'ID':
                    this.filteredCartrackers = this.cartrackers.filter(v =>
                        (v.id.toString().toLowerCase().search(this.search) >= 0));
                    break;
                case 'Hardware':
                    this.filteredCartrackers = this.cartrackers.filter(v =>
                        (v.hardware.toString().toLowerCase().search(this.search) >= 0));
                    break;
            }
            this.filteredCartrackers = this.cartrackers.filter(c => (
                c.id.toString().toLowerCase().search(this.search) >= 0 ||
                c.hardware.toLowerCase().search(this.search) >= 0
            ));
        } else {
            this.filteredCartrackers = this.cartrackers;
        }
    }

    addCartracker(cartracker: Cartracker) {
        this.cartrackers.push(cartracker);
        this.filteredCartrackers = this.cartrackers;
        this.filterCartrackers();
    }
}
