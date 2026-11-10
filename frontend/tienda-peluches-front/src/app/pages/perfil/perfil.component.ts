import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { ApiService } from '../../services/api.service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-perfil',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './perfil.component.html',
  styleUrls: ['./perfil.component.css']
})
export class PerfilComponent implements OnInit {

  private route = inject(ActivatedRoute);
  private router = inject(Router);
  private auth = inject(AuthService);
  private api = inject(ApiService);

  usuario: any;

  mostrarCambio = false;
  passwordActual = '';
  passwordNueva = '';
  passwordConfirmar = '';
  mostrarActual = false;
  mostrarNueva = false;
  mostrarConfirmar = false;
  mensaje = '';
  error = '';
  cargando = false; // evita doble click

  ngOnInit() {
    this.usuario = this.route.snapshot.data['perfil'];
  }

  irCarrito() {
    this.router.navigate(['/carrito']);
  }

  logout() {
    this.auth.logout();
    this.router.navigate(['/login']);
  }

  abrirCambioPassword() {
    this.mostrarCambio = true;
    this.mensaje = '';
    this.error = '';
  }

  cancelarCambio(limpiarMensaje: boolean = false) {
    this.mostrarCambio = false;
    this.passwordActual = '';
    this.passwordNueva = '';
    this.passwordConfirmar = '';
    this.mostrarActual = false;
    this.mostrarNueva = false;
    this.mostrarConfirmar = false;

    if (limpiarMensaje) {
      this.mensaje = '';
    }
  }
  toggleMostrar(field: 'actual' | 'nueva' | 'confirmar') {
    if (field === 'actual') this.mostrarActual = !this.mostrarActual;
    if (field === 'nueva') this.mostrarNueva = !this.mostrarNueva;
    if (field === 'confirmar') this.mostrarConfirmar = !this.mostrarConfirmar;
  }

  cambiarPassword() {
    if (this.cargando) return;

    const actualTrim = this.passwordActual.trim();
    const nuevaTrim = this.passwordNueva.trim();
    const confirmarTrim = this.passwordConfirmar.trim();

    if (!actualTrim || !nuevaTrim || !confirmarTrim) {
      alert('Todos los campos son obligatorios');
      return;
    }

    if (nuevaTrim !== confirmarTrim) {
      alert('Las contraseñas no coinciden');
      return;
    }

    const dto = { actual: actualTrim, nueva: nuevaTrim };
    this.cargando = true;

    this.api.cambiarPassword(dto).subscribe({
      next: (res: any) => {
        alert(res.mensaje || '✅ Contraseña actualizada correctamente');
        this.passwordActual = '';
        this.passwordNueva = '';
        this.passwordConfirmar = '';
        this.mostrarCambio = false;
        this.cargando = false;
      },
      error: (err) => {
        alert(err.error?.mensaje || 'Contraseña actual incorrecta');
        this.cargando = false;
      }
    });
  }

}