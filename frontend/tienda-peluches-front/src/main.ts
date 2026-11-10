import { bootstrapApplication } from '@angular/platform-browser';
import { provideRouter } from '@angular/router';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { routes } from './app/app.routes';
import { App } from './app/app.component';
import { AuthInterceptor } from './app/interceptors/auth.interceptor';
import { HTTP_INTERCEPTORS } from '@angular/common/http';


bootstrapApplication(App, {
  providers: [
    provideRouter(routes),

    provideHttpClient(withInterceptorsFromDi()),

    // 👇 Registrar el interceptor de clase
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    }
  ]
})
.catch(err => console.error(err));
