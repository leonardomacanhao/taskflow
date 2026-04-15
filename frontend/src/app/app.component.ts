import { Component, OnInit, inject } from '@angular/core';
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
export class AppComponent implements OnInit {
  private translationService = inject(TranslationService);

  ngOnInit() {
    this.translationService.setTheme(this.translationService.currentTheme());
  }

  get translations() {
    return this.translationService.translations();
  }

  toggleTheme() {
    const next = this.translationService.currentTheme() === 'light' ? 'dark' : 'light';
    this.translationService.setTheme(next);
  }

  setLanguage(lang: 'pt' | 'en') {
    this.translationService.setLanguage(lang);
  }
}