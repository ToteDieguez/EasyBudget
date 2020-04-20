import { Component, OnInit } from '@angular/core';
import { HandleAuthenticationService } from 'src/app/services/authentication/handle-authentication.service';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  constructor(public handleAuthenticationService: HandleAuthenticationService) {

  }

  ngOnInit() {
  }

  logOut(){
    this.handleAuthenticationService.logOut();
  }

}
