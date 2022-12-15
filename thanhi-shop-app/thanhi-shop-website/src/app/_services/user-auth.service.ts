import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserAuthService {

  constructor() { }

  public setRoles(roles: []) {
    localStorage.setItem('roles', JSON.stringify(roles));
  }

  public getRoles(): String[] {
    const roles = localStorage.getItem('roles');
    return roles ? JSON.parse(roles) : [];
  }

  public setToken(jwtToken: string) {
    localStorage.setItem('jwtToken', JSON.stringify(jwtToken));
  }

  public getToken(): string {
    const token = localStorage.getItem('jwtToken');
    return token ? JSON.parse(token).toString() : null;
  }

  public clearAuth(): void {
    localStorage.removeItem('jwtToken');
    localStorage.removeItem('roles');
  }

  public isLoggedIn() {
    return this.getRoles() && this.getToken();
  }
}
