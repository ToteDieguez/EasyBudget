import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { HandleAuthenticationService } from '../../../services/authentication/handle-authentication.service';

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
    this.handleAuthenticationService.authenticateUser(this.username, this.password).subscribe(
      data => {
        this.route.navigate(['budgets']);
      },
      error => {
        console.log(error);
      });
  }
}
