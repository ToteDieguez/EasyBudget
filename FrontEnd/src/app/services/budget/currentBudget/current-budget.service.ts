import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

export class Hello {
  constructor(public message : string){}
}

@Injectable({
  providedIn: 'root'
})
export class CurrentBudgetService {

  constructor(private http : HttpClient) { }

  getCurrentBudget(){
    return this.http.get<Hello>("hello");
  }
}
