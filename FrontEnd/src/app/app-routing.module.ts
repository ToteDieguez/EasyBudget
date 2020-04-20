import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BudgetComponent } from './components/budget/budget.component';
import { PageNotFoundComponent } from './components/error/page-not-found/page-not-found.component';
import { LoginComponent } from './components/login/login/login.component';
import { RouteGuardService } from './services/route/route-guard.service';


const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'login', component: LoginComponent },
  { path: 'budgets', component: BudgetComponent, canActivate:[RouteGuardService] },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
