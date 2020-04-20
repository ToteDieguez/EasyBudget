import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatSliderModule } from '@angular/material/slider';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BudgetComponent } from './components/budget/budget.component';
import { ErrorComponent } from './components/error/error/error.component';
import { PageNotFoundComponent } from './components/error/page-not-found/page-not-found.component';
import { FooterComponent } from './components/footer/footer.component';
import { LoginComponent } from './components/login/login/login.component';
import { MenuComponent } from './components/menu/menu.component';
import { MaterialModule } from './material-module';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ErrorComponent,
    BudgetComponent,
    PageNotFoundComponent,
    MenuComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    MatSliderModule,
    BrowserAnimationsModule,
    MaterialModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
