import { Component, inject, OnInit, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-productos',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './productos.component.html',
  styleUrls: ['./productos.component.css']
})
export class ProductosComponent implements OnInit {

  private route = inject(ActivatedRoute);
  private api = inject(ApiService);
  private cd = inject(ChangeDetectorRef);

  productos: any[] = [];
  cargando = true;

  mostrarToast = false;
  mensajeToast = '';

  ngOnInit() {
    this.productos = this.route.snapshot.data['productos'] || [];
    this.cargando = false;
  }

  agregarAlCarrito(producto: any) {
    this.api.agregarAlCarrito(producto.id, 1).subscribe({
      next: () => {
        this.mensajeToast = '🛒 Producto agregado al carrito';
        this.mostrarToast = true;

        this.cd.detectChanges();

        setTimeout(() => {
          this.mostrarToast = false;
          this.cd.detectChanges();
        }, 2000);
      },
      error: () => {
        this.mensajeToast = '❌ Error al agregar al carrito';
        this.mostrarToast = true;

        this.cd.detectChanges();

        setTimeout(() => {
          this.mostrarToast = false;
          this.cd.detectChanges();
        }, 2000);
      }
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
