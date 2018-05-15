import { Component, OnInit } from '@angular/core';
import { User } from '../_model/User';

@Component({
    selector: 'app-user',
    templateUrl: 'user.component.html',
    styleUrls: ['user.component.css']
})

export class UserComponent implements OnInit {
    user: User;

    constructor() {
        this.user = new User(1, 'test', 'test@test.nl');
    }

    ngOnInit() { }
}
