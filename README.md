# Consumer Platform Profile API (Monorepo)

A production-style scaffold implementing a **Consumer 360 Profile & Personalization API** with a Kotlin/Micronaut backend, Kafka, PostgreSQL, Prometheus/Grafana, and a Next.js internal console.

## Quickstart

```bash
# 1) Build images and start infra + apps
docker compose up -d --build

# 2) Seed demo data (produces events via API -> Kafka and writes to Postgres)
docker compose exec api ./gradlew :services:profile:testData

# 3) Open console
open http://localhost:3000
```

### Services & Ports
- API (Micronaut): http://localhost:8080
- Console (Next.js): http://localhost:3000
- Postgres: localhost:5432 (user/pass: app/app, db: appdb)
- Kafka (KRaft): localhost:9092
- Prometheus: http://localhost:9090
- Grafana: http://localhost:3001 (admin/admin)

## SLOs
- Freshness SLO: ≤ 60s from event produce ➜ profile visible
- p95 profile read latency < 200ms (local demo load)

## Structure
```
consumer-platform-profile-api/
  apps/console/                 # Next.js internal console
  services/profile/             # Kotlin Micronaut service
  infra/                        # Docker Compose, Prometheus, Grafana
  tools/seed/                   # Seed tasks/scripts
  lib/openapi-client/           # Typed TS client (minimal)
  .github/workflows/ci.yml
  README.md
```
