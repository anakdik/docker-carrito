// src/environments/environment.docker.ts
export const environment = {
  production: true,
  apiUrl: '/api',      // Mantén esta para otros servicios si la usas
  backendUrl: '/api'   // <--- AGREGA ESTA LÍNEA EXACTAMENTE ASÍ
};