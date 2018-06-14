import {ModuleWithProviders} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {NotFoundComponent} from './not-found/not-found.component';
import {LoginComponent} from './login/login.component';
import {AuthService} from './_service/auth.service';
import {VehicleListComponent} from './vehicle-list/vehicle-list.component';
import {VehicleComponent} from './vehicle/vehicle.component';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'vehicles',
    pathMatch: 'full'
  },
  // {
  //     path: 'home',
  //     component: HomeComponent,
  //     canActivate: [AuthService]
  // },
  /*{
    path: 'invoices',
    component: InvoiceListComponent,
    canActivate: [AuthService]
  },*/
  {
    path: 'vehicles',
    component: VehicleListComponent,
    canActivate: [AuthService]
  },
  {
    path: 'vehicles/:id',
    component: VehicleComponent,
    canActivate: [AuthService]
  },
  {
    path: 'vehicles/:id/track',
    component: VehicleComponent,
    canActivate: [AuthService]
  },
  /*{
    path: 'cartracker',
    component: CartrackerComponent,
    canActivate: [AuthService]
  },*/
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: '404',
    component: NotFoundComponent
  },
  {
    path: '**',
    redirectTo: '/404'
  }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(routes);
