import { Component } from '@angular/core';

@Component({
  selector: 'app-dashboard-page',
  template: `
    <main class="dashboard-shell">
      <section>
        <p class="eyebrow">Sales & Operations Control Center</p>
        <h1>SME management dashboard</h1>
        <p>
          Angular 22 application shell with strict TypeScript, Angular Router,
          and feature folders prepared for CRM, quotations, orders, operations,
          cashflow, reports, and settings.
        </p>
      </section>
    </main>
  `,
  styles: `
    .dashboard-shell {
      display: grid;
      min-height: 100vh;
      place-items: center;
      padding: 2rem;
    }

    section {
      max-width: 48rem;
      border: 1px solid #d9e2ef;
      border-radius: 1.5rem;
      background: #ffffff;
      box-shadow: 0 24px 60px rgb(15 23 42 / 10%);
      padding: 3rem;
    }

    .eyebrow {
      color: #2563eb;
      font-weight: 700;
      letter-spacing: 0.08em;
      text-transform: uppercase;
    }

    h1 {
      margin: 0 0 1rem;
      font-size: clamp(2rem, 4vw, 4rem);
      line-height: 1;
    }

    p {
      font-size: 1.125rem;
      line-height: 1.7;
    }
  `,
})
export class DashboardPage {}
