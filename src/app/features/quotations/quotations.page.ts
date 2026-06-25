import { Component } from '@angular/core';

@Component({
  selector: 'app-quotations-page',
  template: `
    <section class="feature-page">
      <p class="eyebrow">Sales</p>
      <h1>Quotations</h1>
      <p>Quotation drafting, approval, and conversion flows will be implemented here.</p>
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
export class QuotationsPage {}
