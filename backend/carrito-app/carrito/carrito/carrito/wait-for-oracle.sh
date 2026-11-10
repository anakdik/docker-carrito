#!/usr/bin/env sh

HOST=$1
PORT=$2
shift 2

echo "⏳ Esperando a Oracle Listener en $HOST:$PORT..."

until nc -z "$HOST" "$PORT"; do
  echo "❌ Listener no disponible, esperando 5s..."
  sleep 5
done

echo "✅ Listener disponible"
echo "⏳ Esperando 30s extra para que XEPDB1 termine de abrir..."
sleep 30

echo "🚀 Oracle listo. Iniciando backend..."
exec "$@"
