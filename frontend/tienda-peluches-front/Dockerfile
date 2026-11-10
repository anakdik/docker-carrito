# Stage 1: Build
FROM node:22-alpine AS build
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
# Desactivamos SSR/Prerender para asegurar que genere el index.html en la raíz de dist
RUN npx ng build --configuration=docker --ssr false --prerender false

# Stage 2: Serve
FROM nginx:alpine

# Ajuste según tu estructura real detectada:
COPY --from=build /app/dist/tienda-peluches-front/browser/browser /usr/share/nginx/html

COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]