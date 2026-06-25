import { Component } from '@angular/core';

@Component({
  selector: 'app-orders-page',
  template: `
    <section class="feature-page">
      <p class="eyebrow">Order Management</p>
      <h1>Orders</h1>
      <p>Order status, delivery, payment, and activity tracking will be implemented here.</p>
    </section>
  `,
  styles: `
    .feature-page {
      padding: 2rem;
    }

    .eyebrow {
      color: #2563eb;
      font-weight: 700;
      letter-spacing: 0.08em;
      text-transform: uppercase;
    }
  `,
})
export class OrdersPage {}
