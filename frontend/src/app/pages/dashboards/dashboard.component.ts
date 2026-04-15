import { Component, inject } from '@angular/core';
import { TranslationService } from '../../core/services/translation.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  template: `<h2>{{ t.translations().dashboard }}</h2><p>Interface pronta para evolução.</p>`,
  styles: [`h2 { font-weight: 600; }`]
})
export class DashboardComponent {
  protected t = inject(TranslationService);
}