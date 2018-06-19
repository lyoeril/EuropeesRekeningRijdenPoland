import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { HttpModule } from '@angular/http';
import { AgmCoreModule } from '@agm/core';

// Routing
import { routing } from './app.routing';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { LoginComponent } from './login/login.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { VehicleListComponent } from './vehicle-list/vehicle-list.component';

import { HttpService } from './_service/http.service';
import { AuthService } from './_service/auth.service';
import { VehicleComponent } from './vehicle/vehicle.component';
import { VehicleTrackComponent } from './vehicle-track/vehicle-track.component';
import {PoliceService} from './_service/police.service';
import {TrackingService} from './_service/tracking.service';
import {WebsocketService} from './_service/websocket.service';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    LoginComponent,
    NotFoundComponent,
    VehicleListComponent,
    VehicleComponent,
    VehicleTrackComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    // TODO: insert valid Google Maps JavaScript API key
    AgmCoreModule.forRoot({apiKey: 'AIzaSyD_BmYEJ2Ma40WMi_KIQ7nCorSVo3eeVL8'}),
    HttpClientModule,
    HttpModule,
    routing
  ],
  providers: [
    HttpService,
    PoliceService,
    TrackingService,
    WebsocketService,
    AuthService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
