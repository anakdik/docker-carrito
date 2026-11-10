import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private TOKEN_KEY = 'token';
  private USUARIO_KEY = 'usuario';

  constructor() {}

  setSesion(usuario: any, token: string) {
    localStorage.setItem(this.TOKEN_KEY, token);
    localStorage.setItem(this.USUARIO_KEY, JSON.stringify(usuario));
  }

  getToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  getUsuario(): any {
    const usuario = localStorage.getItem(this.USUARIO_KEY);
    return usuario ? JSON.parse(usuario) : null;
  }

  isLogged(): boolean {
  return !!localStorage.getItem(this.TOKEN_KEY);
}

  logout() {
    localStorage.removeItem(this.TOKEN_KEY);
    localStorage.removeItem(this.USUARIO_KEY);
  }
}