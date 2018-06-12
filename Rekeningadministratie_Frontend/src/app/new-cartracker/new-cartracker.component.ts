import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import { HttpService } from '../_service/http.service';
import { Cartracker } from '../_model/Cartracker';

@Component({
    selector: 'app-new-cartracker',
    templateUrl: 'new-cartracker.component.html'
})

export class NewCartrackerComponent implements OnInit {

    @Output() newCartracker: EventEmitter<Cartracker> = new EventEmitter();
    cartracker = { hardware: '' };

    constructor(private http: HttpService) { }

    ngOnInit() { }

    save() {
        this.http.addCartracker(this.cartracker)
            .then(response => {
                if (response !== null) {
                    this.newCartracker.emit(response);
                }
            });
    }
}
