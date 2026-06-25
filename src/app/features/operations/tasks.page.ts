import { Component } from '@angular/core';

@Component({
  selector: 'app-tasks-page',
  template: `
    <section class="feature-page">
      <p class="eyebrow">Operations</p>
      <h1>Tasks</h1>
      <p>Operations task board and task completion workflows will be implemented here.</p>
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
export class TasksPage {}
