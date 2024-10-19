import { Routes } from '@angular/router';
import { RegleComponent } from './regle/regle.component'; 
import { AccueilComponent } from './accueil/accueil.component';
import { AdminComponent } from './admin/admin.component';
import { NiveauComponent } from './niveau/niveau.component';

export const routes: Routes = [
    { path: 'accueil', component: AccueilComponent }, 
    { path: 'regles', component: RegleComponent },
    { path: 'admin', component: AdminComponent },
    { path: 'niveau/:niveau/:id', component: NiveauComponent }, // Mise à jour de la route
    { path: '', redirectTo: '/accueil', pathMatch: 'full' },
    { path: '**', redirectTo: '/accueil' }
];


