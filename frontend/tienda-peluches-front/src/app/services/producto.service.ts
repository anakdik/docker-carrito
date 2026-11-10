import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment.docker';

@Injectable({
  providedIn: 'root'
})
export class ProductoService {

  private http = inject(HttpClient);
  private apiUrl = `${environment.backendUrl}/productos`;

  listar(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

  obtener(id: number): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }
}
