import { Component } from '@angular/core';

@Component({
  selector: 'app-customers-page',
  template: `
    <section class="feature-page">
      <p class="eyebrow">CRM Mini</p>
      <h1>Customers</h1>
      <p>Customer pipeline and account management will be implemented here.</p>
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
export class CustomersPage {}
