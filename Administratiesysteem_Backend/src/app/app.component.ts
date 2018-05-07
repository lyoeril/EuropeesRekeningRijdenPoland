import {Component} from '@angular/core';
import {Router} from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  isNavbarCollapsed = true;

  constructor(private router: Router) {
  }


  isLoggedIn(): boolean {
    return !(localStorage.getItem('token') === null);
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }
}
