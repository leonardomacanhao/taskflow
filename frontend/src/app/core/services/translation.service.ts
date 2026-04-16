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
  private langSignal = signal<Language>((localStorage.getItem('lang') as Language) || 'pt');
  
  // ✅ Sempre começa light. Só muda se localStorage tiver 'dark'
  private themeSignal = signal<'light' | 'dark'>('light');

  constructor() {
    const savedTheme = localStorage.getItem('theme') as 'light' | 'dark' | null;
    const initialTheme = savedTheme === 'dark' ? 'dark' : 'light';
    this.themeSignal.set(initialTheme);
    document.documentElement.setAttribute('data-theme', initialTheme);
  }

  translations = computed(() => DICTIONARY[this.langSignal()]);
  currentTheme = this.themeSignal.asReadonly();

  setLanguage(lang: Language) {
    this.langSignal.set(lang);
    localStorage.setItem('lang', lang);
  }

  setTheme(theme: 'light' | 'dark') {
    this.themeSignal.set(theme);
    localStorage.setItem('theme', theme);
    document.documentElement.setAttribute('data-theme', theme);
  }
}