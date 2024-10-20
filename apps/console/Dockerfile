
FROM node:20-alpine AS deps
WORKDIR /app
COPY apps/console/package.json ./package.json
RUN npm i --no-audit --no-fund

FROM node:20-alpine AS builder
WORKDIR /app
COPY --from=deps /app/node_modules ./node_modules
COPY apps/console ./
RUN npm run build || true

FROM node:20-alpine
WORKDIR /app
COPY --from=builder /app .
EXPOSE 3000
CMD ["npm","run","dev"]
