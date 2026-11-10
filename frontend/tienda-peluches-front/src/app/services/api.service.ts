import { Injectable, inject } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';
import { environment } from '../../environments/environment.docker';

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private http = inject(HttpClient);
  private auth = inject(AuthService);
  private baseUrl = environment.backendUrl;

  // ------------------- USUARIOS -------------------

  login(credentials: { correo: string; password: string }): Observable<any> {
    return this.http.post(`${this.baseUrl}/usuarios/login`, credentials);
  }

  register(data: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/usuarios`, data);
  }

  getPerfil(): Observable<any> {
    const token = this.auth.getToken();
    if (!token) throw new Error('Usuario no autenticado');
    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
    return this.http.get(`${this.baseUrl}/usuarios/perfil`, { headers });
  }

  cambiarPassword(data: { actual: string; nueva: string }): Observable<any> {
    const token = this.auth.getToken();
    if (!token) throw new Error('Usuario no autenticado');
    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
    return this.http.put(`${this.baseUrl}/usuarios/cambiar-password`, data, { headers });
  }

  // ------------------- PRODUCTOS -------------------

  listarProductos(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/productos`);
  }

  obtenerProducto(id: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/productos/${id}`);
  }

  // ------------------- CARRITO -------------------

  getCarrito(): Observable<any[]> {
    const token = this.auth.getToken();
    if (!token) throw new Error('Usuario no autenticado');
    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
    return this.http.get<any[]>(`${this.baseUrl}/carrito`, { headers });
  }

  agregarAlCarrito(idProducto: number, cantidad: number): Observable<any> {
    const token = this.auth.getToken();
    if (!token) throw new Error('Usuario no autenticado');
    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
    return this.http.post(`${this.baseUrl}/carrito/agregar?idProducto=${idProducto}&cantidad=${cantidad}`, {}, { headers });
  }

  eliminarDetalle(idDetalle: number): Observable<any> {
    const token = this.auth.getToken();
    if (!token) throw new Error('Usuario no autenticado');
    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
    return this.http.delete(`${this.baseUrl}/carrito/detalle/${idDetalle}`, { headers });
  }

  // ------------------- PEDIDOS -------------------

  crearPedido(): Observable<any> {
    const token = this.auth.getToken();
    if (!token) throw new Error('Usuario no autenticado');
    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
    return this.http.post(`${this.baseUrl}/pedidos`, {}, { headers });
  }

  listarPedidos(): Observable<any[]> {
    const token = this.auth.getToken();
    if (!token) throw new Error('Usuario no autenticado');
    const headers = new HttpHeaders({ Authorization: `Bearer ${token}` });
    return this.http.get<any[]>(`${this.baseUrl}/pedidos`, { headers });
  }

  // ------------------- HOME -------------------

  getHome(): Observable<any> {
    return this.http.get(`${this.baseUrl}/home`);
  }
}
