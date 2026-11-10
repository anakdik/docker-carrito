import { Component, inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Router, ActivatedRoute } from '@angular/router';
import { ApiService } from '../../services/api.service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-carrito',
  standalone: true,
  imports: [CommonModule, RouterModule],
  templateUrl: './carrito.component.html',
  styleUrls: ['./carrito.component.css']
})
export class CarritoComponent implements OnInit {

  private route = inject(ActivatedRoute);
  private api = inject(ApiService);
  private router = inject(Router);
  private auth = inject(AuthService);

  items: any[] = [];
  total = 0;

  ngOnInit() {
    if (!this.auth.isLogged()) {
      this.router.navigate(['/login']);
      return;
    }

    this.items = this.route.snapshot.data['carrito'];
    this.calcularTotal();
  }

  calcularTotal() {
    this.total = this.items.reduce(
      (s, i) => s + i.precioUnitario * i.cantidad, 0
    );
  }

  sumar(item: any) {
    item.cantidad++;
    this.calcularTotal();

    this.api.agregarAlCarrito(item.producto.id, 1).subscribe({
      error: () => {
        item.cantidad--;
        this.calcularTotal();
      }
    });
  }

  restar(item: any) {
    if (item.cantidad <= 1) return;

    item.cantidad--;
    this.calcularTotal();

    this.api.agregarAlCarrito(item.producto.id, -1).subscribe({
      error: () => {
        item.cantidad++;
        this.calcularTotal();
      }
    });
  }

  eliminar(detalleId: number) {
    this.api.eliminarDetalle(detalleId).subscribe(() => {
      this.items = this.items.filter(i => i.id !== detalleId);
      this.calcularTotal();
    });
  }

  vaciar() {
    this.items.forEach(item =>
      this.api.eliminarDetalle(item.id).subscribe()
    );
    this.items = [];
    this.total = 0;
  }

  hacerPedido() {
    if (this.items.length === 0) {
      alert('El carrito está vacío');
      return;
    }

    this.api.crearPedido().subscribe({
      next: pedido =>
        this.router.navigate(['/pedido'], { state: { pedido } }),
      error: () => alert('Error al crear el pedido')
    });
  }

  getImagenProducto(nombre: string): string {
    const n = nombre.toLowerCase();
    if (n.includes('oso')) return 'imagenes/oso.jpg';
    if (n.includes('panda')) return 'imagenes/panda.jpg';
    if (n.includes('tiburon') || n.includes('tiburón')) return 'imagenes/tiburon.png';
    if (n.includes('conejo')) return 'imagenes/conejo.jpg';
    if (n.includes('pulpo')) return 'imagenes/pulpo.jpg';
    return 'assets/imagenes/default.jpg';
  }
}
