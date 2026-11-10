import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css']
})
export class RegisterComponent {

  nombre = '';
  apellido = '';
  correo = '';
  telefono = '';
  contrasena = '';

  mensaje = '';
  error = '';

  constructor(
    private auth: AuthService,
    private api: ApiService,
    private router: Router
  ) {}

  // ================= REGISTRO =================
  registrar() {
    this.error = '';
    this.mensaje = '';

    if (!this.nombre || !this.correo || !this.contrasena) {
      this.error = 'Todos los campos marcados son obligatorios';
      return;
    }

    if (!this.contrasenaValida()) {
      this.error = 'La contraseña no cumple con los requisitos';
      return;
    }

    const data = {
      nombre: this.nombre,
      apellido: this.apellido,
      correo: this.correo,
      telefono: this.telefono,
      contrasena: this.contrasena
    };

    this.api.register(data).subscribe({
      next: () => {
        this.mensaje = 'Registro exitoso 🎉 Redirigiendo...';
        setTimeout(() => {
          this.router.navigate(['/login']);
        }, 1500);
      },
      error: (err) => {
        if (err.error?.error) {
          this.error = err.error.error;
        } else {
          this.error = 'No se pudo registrar. Revisa los datos.';
        }
      }
    });
  }

  // ================= VALIDACIONES PASSWORD =================
  tieneLongitud(): boolean {
    return this.contrasena.length >= 8;
  }

  tieneMayuscula(): boolean {
    return /[A-Z]/.test(this.contrasena);
  }

  tieneMinuscula(): boolean {
    return /[a-z]/.test(this.contrasena);
  }

  tieneNumero(): boolean {
    return /\d/.test(this.contrasena);
  }

  tieneEspecial(): boolean {
    return /[^A-Za-z0-9]/.test(this.contrasena);
  }

  contrasenaValida(): boolean {
    return (
      this.tieneLongitud() &&
      this.tieneMayuscula() &&
      this.tieneMinuscula() &&
      this.tieneNumero() &&
      this.tieneEspecial()
    );
  }
}
