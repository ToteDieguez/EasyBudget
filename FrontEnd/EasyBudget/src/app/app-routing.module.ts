import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './components/login/login/login.component';
import { WelcomeComponent } from './components/welcome/welcome/welcome.component';
import { PageNotFoundComponent } from './components/error/page-not-found/page-not-found.component';


const routes: Routes = [
  {path: '',            component: LoginComponent},
  {path : '/login',     component: LoginComponent},
  {path : '/welcome',   component: WelcomeComponent},
  {path : '**',         component: PageNotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
