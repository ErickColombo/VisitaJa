
# VisitaJá Frontend

The frontend uses plain HTML, CSS, and JavaScript.

- `index.html`: public visitor booking flow.
- `login.html`: administrator login.
- `admin.html`: protected administrator panel.

## Run locally

After the PostgreSQL database and backend configuration are ready, start both servers from the project root:

```bash
./start.sh
```

Alternatively, start the backend separately and serve only this directory with a local HTTP server:

```bash
cd /frontend
python3 -m http.server 5500
```

Open one of these pages in the browser:

- `http://localhost:5500/index.html`: public visitor flow.
- `http://localhost:5500/login.html`: administrator login.

Keep the terminal running while using the frontend. The administrator login stores the JWT only in the browser session and sends it as a Bearer token to the protected API routes.
