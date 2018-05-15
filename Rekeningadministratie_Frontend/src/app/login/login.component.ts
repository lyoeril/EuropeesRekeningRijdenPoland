import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {AuthService} from '../auth.service';
import {FormControl, FormGroup} from '@angular/forms';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  encapsulation: ViewEncapsulation.None,
  providers: [
    AuthService
  ]
})
export class LoginComponent implements OnInit {

  error = '';

    loginForm = new FormGroup({
    username: new FormControl(),
    password: new FormControl()
  });

  constructor(private authService: AuthService, private router: Router) {
  }

  ngOnInit() {
  }

  login(): void {
    this
      .authService
      .login(this.loginForm.get('username').value, this.loginForm.get('password').value)
      .subscribe(result => {
        localStorage.setItem('token', result.headers.get('Authorization'));
        this.router.navigate(['/']);
      }, err => {
        if (err.status === 401) {
          this.error = 'Username or password incorrect';
        } else {
          this.error = 'An unknown error occured';
        }
      });
  }
}
