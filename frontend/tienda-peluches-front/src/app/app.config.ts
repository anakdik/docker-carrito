import { ApplicationConfig } from '@angular/core';
import { provideRouter } from '@angular/router';
import {
  provideHttpClient,
  withInterceptorsFromDi,
  HTTP_INTERCEPTORS
} from '@angular/common/http';

import { routes } from './app.routes';
import { AuthInterceptor } from './interceptors/auth.interceptor';

export const appConfig: ApplicationConfig = {
  providers: [
    provideRouter(routes),

    // 🔑 habilita interceptores de CLASE
    provideHttpClient(withInterceptorsFromDi()),

    // 🔑 registra el interceptor
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ]
};
