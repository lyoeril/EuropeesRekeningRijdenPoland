import { Component, OnInit } from '@angular/core';
import { HttpService } from '../_service/http.service';

@Component({
    selector: 'app-new-cartracker',
    templateUrl: 'new-cartracker.component.html'
})

export class NewCartrackerComponent implements OnInit {

    cartracker = { hardware: '' };

    constructor(private http: HttpService) { }

    ngOnInit() { }

    save() {
        this.http.addCartracker(this.cartracker);
    }
}
