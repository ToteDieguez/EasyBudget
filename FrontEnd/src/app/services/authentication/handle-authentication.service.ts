import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class HandleAuthenticationService {

  constructor() { }

  authenticateUser(username, password) {
    if (username == 'totedieguez' && password == "dummy") {
      sessionStorage.setItem('authenticaterUser', username);
      return true;
    }
    return false;
  }

  isUserLoggedIn() {
    return sessionStorage.getItem('authenticaterUser') !== null;
  }

  logOut() {
    sessionStorage.removeItem('authenticaterUser');
  }
}
