import { Component, OnInit } from '@angular/core';
import { HandleAuthenticationService } from 'src/app/services/authentication/handle-authentication.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  username = "totedieguez";
  password: any;

  constructor(private route: Router, private handleAuthenticationService: HandleAuthenticationService) { }

  ngOnInit() {
  }

  logIn() {
    if (this.handleAuthenticationService.authenticateUser(this.username, this.password)) {
      this.route.navigate(['budgets']);
    }
  }
}
