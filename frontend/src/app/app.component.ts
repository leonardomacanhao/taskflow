import { Component, inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { CommonModule } from '@angular/common';
import { TranslationService } from './core/services/translation.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, MatButtonModule, MatIconModule, MatMenuModule, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  protected t = inject(TranslationService);

  ngOnInit() {
    this.t.setTheme(this.t.currentTheme());
  }

  toggleTheme() {
    this.t.setTheme(this.t.currentTheme() === 'light' ? 'dark' : 'light');
  }

  setLang(lang: 'pt' | 'en') {
    this.t.setLanguage(lang);
  }
}