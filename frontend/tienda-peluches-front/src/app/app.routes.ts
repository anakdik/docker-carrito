import { Routes } from '@angular/router';
import { authGuard } from './guards/auth.guard';
import { productosResolver } from './resolvers/productos.resolver';
import { carritoResolver } from './resolvers/carrito.resolver';
import { perfilResolver } from './resolvers/perfil.resolver';

export const routes: Routes = [

  {
    path: '',
    loadComponent: () =>
      import('./pages/home/home.component').then(m => m.HomeComponent)
  },

  {
    path: 'productos',
    resolve: { productos: productosResolver },
    loadComponent: () =>
      import('./pages/productos/productos.component').then(m => m.ProductosComponent)
  },

  {
    path: 'carrito',
    canActivate: [authGuard],
    resolve: { carrito: carritoResolver },
    loadComponent: () =>
      import('./pages/carrito/carrito.component').then(m => m.CarritoComponent)
  },

  {
    path: 'perfil',
    canActivate: [authGuard],
    resolve: { perfil: perfilResolver },
    loadComponent: () =>
      import('./pages/perfil/perfil.component').then(m => m.PerfilComponent)
  },

  {
    path: 'login',
    loadComponent: () =>
      import('./pages/login/login.component').then(m => m.LoginComponent)
  },

  {
    path: 'registro',
    loadComponent: () =>
      import('./pages/registro/registro.component').then(m => m.RegisterComponent)
  },

  {
    path: 'pedido',
    canActivate: [authGuard],
    loadComponent: () =>
      import('./pages/pedido/pedido.component').then(m => m.PedidoComponent)
  },

  { path: '**', redirectTo: '' }
];
