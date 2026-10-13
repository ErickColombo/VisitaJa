
# VisitaJá Backend

## PostgreSQL with Docker

Start a temporary PostgreSQL database for local testing:

```bash
docker run --rm --name visitaja-postgres \
  -e POSTGRES_DB=visitaja_db \
  -e POSTGRES_USER=postgres \
  -e POSTGRES_PASSWORD=postgres \
  -p 5432:5432 \
  -d postgres:17
```

The `DB_USER` and `DB_PASS` values in `.env` must match the Docker values:

```properties
DB_USER=postgres
DB_PASS=postgres
```

Because this command uses `--rm`, stopping the container removes its data. Stop it with:

```bash
docker stop visitaja-postgres
```

## Configuration

Copy `.env.example` to `.env` and fill in the PostgreSQL and administrator values:

```properties
DB_USER=postgres
DB_PASS=your-database-password
ADMIN_USERNAME=admin
ADMIN_PASSWORD=your-admin-password
JWT_SECRET=your-long-random-secret
JWT_EXPIRATION_SECONDS=86400
```

The application creates the configured administrator on startup when the username does not already exist. The password is stored with BCrypt.

The administrator is created only once for each username:

1. The application searches for `ADMIN_USERNAME` in the database.
2. If it does not exist, it creates the user with the `ADMIN` role.
3. If it already exists, startup skips creation and does not change its password.

Changing `ADMIN_PASSWORD` in `.env` does not change an existing user's password. To test new credentials with the temporary database, stop the container and start it again before restarting the backend.

## Run

From the project root, start both backend and frontend with:

```bash
./start.sh
```

To start only the backend:

```bash
bash mvnw spring-boot:run
```

## Authentication

Login:

```http
POST /auth/login
Content-Type: application/json

{
  "username": "admin",
  "password": "your-admin-password"
}
```

Use the returned token on administrative requests:

```http
Authorization: Bearer <token>
```

Public visitor routes remain available without a token:

- `GET /api/leiloes/ativos`
- `GET /api/patios/leilao/{leilaoId}`
- `GET /api/horarios/patio/{patioId}/disponiveis`
- `POST /api/agendamentos/publico/agendar`

Other `/api/**` routes require the `ADMIN` role.
