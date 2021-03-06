/// <reference path="../../node_modules/moment/moment.d.ts"" />

import { Component } from '@angular/core';

import fontawesome from '@fortawesome/fontawesome';
import { faHome } from '@fortawesome/fontawesome-free-solid';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';

  constructor() {
    fontawesome.library.add(faHome);
  }
}
