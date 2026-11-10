import { Component } from '@angular/core';
import { ApiService } from '../../services/api.service';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  correo = '';
  password = '';
  error = '';

  constructor(
    private api: ApiService,
    private auth: AuthService,
    private router: Router
  ) {}

  iniciar() {
  this.error = '';

 this.api.login({ correo: this.correo, password: this.password })
  .subscribe({
    next: (resp: any) => {
      
      this.auth.setSesion(resp.usuario, resp.token);  
      this.router.navigate(['/productos']);
    },
    error: err => {
      if (err.status === 401) {
        this.error = 'Credenciales inválidas';
      } else {
        this.error = 'Error de conexión con el servidor';
      }
    }
  });
  }}