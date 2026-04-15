import { Injectable, signal, computed } from '@angular/core';

export type Language = 'pt' | 'en';

const DICTIONARY = {
  pt: {
    login: 'Entrar',
    register: 'Cadastrar',
    email: 'Email',
    password: 'Senha',
    name: 'Nome',
    dashboard: 'Meus Quadros',
    create_board: 'Criar Quadro',
    add_list: 'Adicionar Coluna',
    add_card: 'Adicionar Tarefa',
    move_card: 'Mover Tarefa',
    delete: 'Excluir',
    save: 'Salvar',
    cancel: 'Cancelar',
    empty_state: 'Nenhum item encontrado',
    loading: 'Carregando...',
    error_generic: 'Ocorreu um erro. Tente novamente.',
    auth_invalid: 'Email ou senha inválidos',
    auth_registered: 'Cadastro realizado com sucesso',
    theme_light: 'Tema Claro',
    theme_dark: 'Tema Escuro',
    lang_pt: 'Português',
    lang_en: 'Inglês'
  },
  en: {
    login: 'Login',
    register: 'Register',
    email: 'Email',
    password: 'Password',
    name: 'Name',
    dashboard: 'My Boards',
    create_board: 'Create Board',
    add_list: 'Add Column',
    add_card: 'Add Task',
    move_card: 'Move Task',
    delete: 'Delete',
    save: 'Save',
    cancel: 'Cancel',
    empty_state: 'No items found',
    loading: 'Loading...',
    error_generic: 'An error occurred. Please try again.',
    auth_invalid: 'Invalid email or password',
    auth_registered: 'Registration successful',
    theme_light: 'Light Theme',
    theme_dark: 'Dark Theme',
    lang_pt: 'Portuguese',
    lang_en: 'English'
  }
};

@Injectable({ providedIn: 'root' })
export class TranslationService {
  private currentLang = signal<Language>((localStorage.getItem('lang') as Language) || 'pt');
  private theme = signal<'light' | 'dark'>((localStorage.getItem('theme') as 'light' | 'dark') || 'light');

  translations = computed(() => DICTIONARY[this.currentLang()]);
  currentTheme = this.theme.asReadonly();

  setLanguage(lang: Language) {
    this.currentLang.set(lang);
    localStorage.setItem('lang', lang);
  }

  setTheme(theme: 'light' | 'dark') {
    this.theme.set(theme);
    localStorage.setItem('theme', theme);
    document.documentElement.setAttribute('data-theme', theme);
  }

  translate(key: keyof typeof DICTIONARY.pt): string {
    return this.translations()[key];
  }
}