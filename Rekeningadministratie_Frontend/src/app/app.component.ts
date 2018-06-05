import { Component } from '@angular/core';

import fontawesome from '@fortawesome/fontawesome';
import { faHome, faFileAlt, faCar } from '@fortawesome/fontawesome-free-solid';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'app';

  constructor() {
    fontawesome.library.add(faHome, faFileAlt, faCar);
  }
}
