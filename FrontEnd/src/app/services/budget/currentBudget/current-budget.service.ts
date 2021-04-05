import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {API_URL} from '../../../app.constants';
import {map} from 'rxjs/operators';

export class Hello {
  constructor(public message: string) {
  }
}

@Injectable({
  providedIn: 'root'
})
export class CurrentBudgetService {

  constructor(private http: HttpClient) {
  }

  getCurrentBudget() {
    return this.http.get<any>(
      `${API_URL}/findPersonByUsername`).pipe(
      map(
        data => {
          return data;
        }
      )
    );
  }
}
