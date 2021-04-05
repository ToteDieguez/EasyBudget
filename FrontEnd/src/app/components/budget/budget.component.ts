import { Component, OnInit } from '@angular/core';
import { CurrentBudgetService } from 'src/app/services/budget/currentBudget/current-budget.service';
import {formatDate} from '@angular/common';

@Component({
  selector: 'app-budget',
  templateUrl: './budget.component.html',
  styleUrls: ['./budget.component.css']
})
export class BudgetComponent implements OnInit {

  currentBudgetMonthYear: any;

  constructor(private currentBudgetService: CurrentBudgetService) { }

  ngOnInit() {
    const currentDate = new Date();
    this.currentBudgetMonthYear = formatDate(currentDate, 'MMMM yyyy', 'en-US');
  }

  getHello() {
    this.currentBudgetService.getCurrentBudget().subscribe(
        response => {
          // tslint:disable-next-line:no-unused-expression
          response.message;
        },
        error => {
          console.log("An error happened");
        }
    );
  }

}
