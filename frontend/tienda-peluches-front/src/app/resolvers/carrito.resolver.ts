import { ResolveFn } from '@angular/router';
import { inject } from '@angular/core';
import { ApiService } from '../services/api.service';

export const carritoResolver: ResolveFn<any[]> = () => {
  const api = inject(ApiService);
  return api.getCarrito();
};
