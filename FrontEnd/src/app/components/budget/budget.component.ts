import { Component, OnInit } from '@angular/core';
import { CurrentBudgetService } from 'src/app/services/budget/currentBudget/current-budget.service';

@Component({
  selector: 'app-budget',
  templateUrl: './budget.component.html',
  styleUrls: ['./budget.component.css']
})
export class BudgetComponent implements OnInit {

  helloMessage : any;

  constructor(private currentBudgetService: CurrentBudgetService) { }

  ngOnInit() {
  }

  getHello() {
    this.currentBudgetService.getCurrentBudget().subscribe(
        response => {
          this.helloMessage = response.message;
        },
        error => {
          console.log("An error happened");
        }
    );
  }

}
