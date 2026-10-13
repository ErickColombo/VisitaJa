#!/usr/bin/env bash

set -Eeuo pipefail

ROOT_DIR="$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")" && pwd)"
BACKEND_DIR="$ROOT_DIR/backend"
FRONTEND_DIR="$ROOT_DIR/frontend"

if [[ ! -f "$BACKEND_DIR/.env" ]]; then
    echo "Missing backend/.env. Copy backend/.env.example and configure it first."
    exit 1
fi

if ! command -v python3 >/dev/null 2>&1; then
    echo "python3 is required to serve the frontend."
    exit 1
fi

cleanup() {
    trap - EXIT INT TERM

    if [[ -n "${BACKEND_PID:-}" ]] && kill -0 "$BACKEND_PID" 2>/dev/null; then
        kill "$BACKEND_PID" 2>/dev/null || true
    fi

    if [[ -n "${FRONTEND_PID:-}" ]] && kill -0 "$FRONTEND_PID" 2>/dev/null; then
        kill "$FRONTEND_PID" 2>/dev/null || true
    fi

    wait "${BACKEND_PID:-}" "${FRONTEND_PID:-}" 2>/dev/null || true
}

trap cleanup EXIT INT TERM

(
    cd "$BACKEND_DIR"
    exec bash mvnw spring-boot:run
) &
BACKEND_PID=$!

(
    cd "$FRONTEND_DIR"
    exec python3 -m http.server 5500
) &
FRONTEND_PID=$!

echo "Backend: http://localhost:8080"
echo "Frontend: http://localhost:5500/index.html"
echo "Login: http://localhost:5500/login.html"
echo "Press Ctrl+C to stop both servers."

set +e
wait -n "$BACKEND_PID" "$FRONTEND_PID"
status=$?
set -e

exit "$status"
