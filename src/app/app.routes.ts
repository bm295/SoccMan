import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: 'dashboard',
  },
  {
    path: 'dashboard',
    loadComponent: () => import('./features/dashboard/dashboard.page').then((m) => m.DashboardPage),
  },
  {
    path: 'customers',
    loadComponent: () => import('./features/crm/customers.page').then((m) => m.CustomersPage),
  },
  {
    path: 'quotations',
    loadComponent: () => import('./features/quotations/quotations.page').then((m) => m.QuotationsPage),
  },
  {
    path: 'orders',
    loadComponent: () => import('./features/orders/orders.page').then((m) => m.OrdersPage),
  },
  {
    path: 'tasks',
    loadComponent: () => import('./features/operations/tasks.page').then((m) => m.TasksPage),
  },
  {
    path: '**',
    redirectTo: 'dashboard',
  },
];
