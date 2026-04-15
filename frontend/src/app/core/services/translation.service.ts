import { Injectable, signal, computed } from '@angular/core';

export type Language = 'pt' | 'en';

const DICTIONARY = {
  pt: {
    login: 'Entrar', email: 'Email', password: 'Senha', dashboard: 'Meus Quadros',
    create_board: 'Criar Quadro', save: 'Salvar', cancel: 'Cancelar',
    error_generic: 'Ocorreu um erro.', auth_invalid: 'Email ou senha inválidos',
    theme_dark: 'Tema Escuro', theme_light: 'Tema Claro',
    lang_pt: 'Português', lang_en: 'Inglês'
  },
  en: {
    login: 'Login', email: 'Email', password: 'Password', dashboard: 'My Boards',
    create_board: 'Create Board', save: 'Save', cancel: 'Cancel',
    error_generic: 'An error occurred.', auth_invalid: 'Invalid email or password',
    theme_dark: 'Dark Theme', theme_light: 'Light Theme',
    lang_pt: 'Portuguese', lang_en: 'English'
  }
};

@Injectable({ providedIn: 'root' })
export class TranslationService {
  private lang = signal<Language>((localStorage.getItem('lang') as Language) || 'pt');
  private theme = signal<'light' | 'dark'>((localStorage.getItem('theme') as 'light' | 'dark') || 'light');

  translations = computed(() => DICTIONARY[this.lang()]);
  currentTheme = this.theme.asReadonly();

  setLanguage(lang: Language) {
    this.lang.set(lang);
    localStorage.setItem('lang', lang);
  }

  setTheme(theme: 'light' | 'dark') {
    this.theme.set(theme);
    localStorage.setItem('theme', theme);
    document.documentElement.setAttribute('data-theme', theme);
  }
}