import { Component } from '@angular/core';
import { ApiService } from '../services/api.service';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [FormsModule, CommonModule],
  template: `
    <div class="registro-container">
      <h2>Registro</h2>

      <input [(ngModel)]="nombre" placeholder="Nombre" />
      <input [(ngModel)]="apellido" placeholder="Apellido" />
      <input [(ngModel)]="correo" placeholder="Correo" type="email" />
      <input [(ngModel)]="telefono" placeholder="Teléfono" />
      <input [(ngModel)]="password" placeholder="Contraseña" type="password" />

      <button (click)="registrar()">Crear Cuenta</button>

      <p *ngIf="error" class="error">{{ error }}</p>
      <p *ngIf="mensaje" class="ok">{{ mensaje }}</p>
    </div>
  `,
  styles: [``]
})
export class RegisterComponent {

  nombre = '';
  apellido = '';
  correo = '';
  telefono = '';
  password = '';
  error = '';
  mensaje = '';

  constructor(
    private api: ApiService,
    private router: Router
  ) {}

  registrar() {
    this.error = '';
    this.mensaje = '';

    const data = {
      nombre: this.nombre,
      apellido: this.apellido,
      correo: this.correo,
      telefono: this.telefono,
      contrasena: this.password
    };

    this.api.register(data).subscribe({
      next: () => {
        this.mensaje = 'Usuario registrado con éxito';
        setTimeout(() => this.router.navigate(['/login']), 1000);
      },
      error: () => {
        this.error = 'Error al registrar usuario';
      }
    });
  }
}
