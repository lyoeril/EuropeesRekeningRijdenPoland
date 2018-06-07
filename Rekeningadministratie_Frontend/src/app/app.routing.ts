import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home/home.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { LoginComponent } from './login/login.component';
import { AuthService } from './_service/auth.service';
import { InvoiceListComponent } from './invoice-list/invoice-list.component';
import { VehicleListComponent } from './vehicle-list/vehicle-list.component';
import { CartrackerComponent } from './cartracker/cartracker.component';

const routes: Routes = [
    {
        path: '',
        component: InvoiceListComponent,
        canActivate: [AuthService]
    },
    // {
    //     path: 'home',
    //     component: HomeComponent,
    //     canActivate: [AuthService]
    // },
    {
        path: 'invoices',
        component: InvoiceListComponent,
        canActivate: [AuthService]
    },
    {
        path: 'vehicles',
        component: VehicleListComponent,
        canActivate: [AuthService]
    },
    {
        path: 'cartracker',
        component: CartrackerComponent,
        canActivate: [AuthService]
    },
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
