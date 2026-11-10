import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-pedido',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './pedido.component.html',
  styleUrls: ['./pedido.component.css']
})
export class PedidoComponent implements OnInit {

  pedidoConfirmado = false;

  constructor(private router: Router) {}

  ngOnInit() {
    const state = history.state;
    if (state && state.pedido) {
      this.pedidoConfirmado = true;
    }
  }

  volver() {
    this.router.navigate(['/productos']);
  }
}
