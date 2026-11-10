import { ResolveFn } from '@angular/router';
import { inject } from '@angular/core';
import { ApiService } from '../services/api.service';

export const perfilResolver: ResolveFn<any> = () => {
  const api = inject(ApiService);
  return api.getPerfil();
};
