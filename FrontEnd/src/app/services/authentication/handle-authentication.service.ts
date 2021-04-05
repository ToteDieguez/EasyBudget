import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { API_URL } from '../../app.constants';

export const TOKEN = 'token'
export const AUTHENTICATED_USER = 'authenticaterUser'

@Injectable({
  providedIn: 'root'
})
export class HandleAuthenticationService {

  constructor(private http: HttpClient) { }

  authenticateUser(username, password) {
    return this.http.post<any>(
      `${API_URL}/authenticate`, {
      username,
      password
    }).pipe(
      map(
        data => {
          sessionStorage.setItem(AUTHENTICATED_USER, username);
          sessionStorage.setItem(TOKEN, `Bearer ${data.token}`);
          return data;
        }
      )
    );
  }


  isUserLoggedIn() {
    return sessionStorage.getItem(AUTHENTICATED_USER) !== null;
  }

  getAuthenticatedUser() {
    return sessionStorage.getItem(AUTHENTICATED_USER);
  }

  getAuthenticatedToken() {
    if (this.isUserLoggedIn) {
      return sessionStorage.getItem(TOKEN);
    }
  }

  logOut() {
    sessionStorage.removeItem(AUTHENTICATED_USER);
    sessionStorage.removeItem(TOKEN);
  }
}
