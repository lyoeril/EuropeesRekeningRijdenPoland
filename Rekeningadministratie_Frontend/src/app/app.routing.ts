import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home/home.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { LoginComponent } from './login/login.component';
import { AuthService } from './_service/auth.service';
import { InvoiceListComponent } from './invoice-list/invoice-list.component';

const routes: Routes = [
    {
        path: '',
        component: HomeComponent,
        canActivate: [AuthService]
    },
    {
        path: 'home',
        component: HomeComponent,
        canActivate: [AuthService]
    },
    {
        path: 'invoices',
        component: InvoiceListComponent,
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
