import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'partners',
    pathMatch: 'full',
  },

  {
    path: 'partners',
    loadComponent: () =>
      import('./features/partners/partner-list/partner-list.component').then(
        (m) => m.PartnerListComponent,
      ),
  },

  {
    path: 'partners/:id',
    loadComponent: () =>
      import('./features/partners/partner-detail/partner-detail.component').then(
        (m) => m.PartnerDetailComponent,
      ),
  },

  {
    path: 'reports',
    loadComponent: () =>
      import('./features/reports/report/report.component').then(
        (m) => m.ReportComponent,
      ),
  },
];
