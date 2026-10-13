
# VisitaJá Backend

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

## Run

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
