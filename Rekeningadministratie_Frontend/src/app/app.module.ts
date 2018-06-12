import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { HttpModule } from '@angular/http';

// Routing
import { routing } from './app.routing';

import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { HeaderComponent } from './header/header.component';
import { LoginComponent } from './login/login.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { InvoiceListComponent } from './invoice-list/invoice-list.component';
import { VehicleListComponent } from './vehicle-list/vehicle-list.component';
import { CartrackerComponent } from './cartracker/cartracker.component';
import { NewCartrackerComponent } from './new-cartracker/new-cartracker.component';
import { CartrackerListComponent } from './cartracker-list/cartracker-list.component';

import { HttpService } from './_service/http.service';
import { AuthService } from './_service/auth.service';
import { KmRateListComponent } from './km-rate-list/km-rate-list.component';


@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    HeaderComponent,
    LoginComponent,
    NotFoundComponent,
    InvoiceListComponent,
    VehicleListComponent,
    CartrackerComponent,
    NewCartrackerComponent,
    CartrackerListComponent,
    KmRateListComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    HttpModule,
    routing
  ],
  providers: [
    HttpService,
    AuthService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
