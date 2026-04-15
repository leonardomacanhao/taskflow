import { Injectable, inject, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { firstValueFrom } from 'rxjs';
import { environment } from '../../../environments/environment';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private http = inject(HttpClient);
  private router = inject(Router);
  isAuthenticated = signal(!!localStorage.getItem('token'));

  async login(email: string, password: string) {
    const res = await firstValueFrom(this.http.post<{token: string, userId: number}>(`${environment.apiUrl}/auth/login`, { email, password }));
    localStorage.setItem('token', res.token);
    localStorage.setItem('userId', String(res.userId));
    this.isAuthenticated.set(true);
    this.router.navigate(['/dashboard']);
  }

  logout() {
    localStorage.removeItem('token');
    localStorage.removeItem('userId');
    this.isAuthenticated.set(false);
    this.router.navigate(['/login']);
  }
}