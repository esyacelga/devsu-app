#!/bin/bash

DB_HOST="localhost"
DB_PORT="5432"
DB_USER="postgres"
DB_PASSWORD="postgres"
DB_NAME="devsu"
DEV_USER="devsu"
DEV_PASSWORD="devsu"

export PGPASSWORD=$DB_PASSWORD

until pg_isready -h $DB_HOST -p $DB_PORT -U $DB_USER; do
  echo "Esperando a que PostgreSQL esté disponible..."
  sleep 2
done

USER_EXISTS=$(psql -h $DB_HOST -p $DB_PORT -U $DB_USER -tAc "SELECT 1 FROM pg_roles WHERE rolname='$DEV_USER';")

if [ "$USER_EXISTS" != "1" ]; then
  echo "El usuario '$DEV_USER' no existe. Creándolo..."
  psql -h $DB_HOST -p $DB_PORT -U $DB_USER -c "CREATE USER $DEV_USER WITH PASSWORD '$DEV_PASSWORD';"
else
  echo "El usuario '$DEV_USER' ya existe. No se requiere acción."
fi

# Crear la base de datos si no existe
DB_EXISTS=$(psql -h $DB_HOST -p $DB_PORT -U $DB_USER -tAc "SELECT 1 FROM pg_database WHERE datname='$DB_NAME';")

if [ "$DB_EXISTS" != "1" ]; then
  echo "La base de datos '$DB_NAME' no existe. Creándola..."
  psql -h $DB_HOST -p $DB_PORT -U $DB_USER -c "CREATE DATABASE $DB_NAME OWNER $DEV_USER;"
else
  echo "La base de datos '$DB_NAME' ya existe. No se requiere acción."
fi

# Conceder todos los privilegios al usuario en la base de datos
echo "Concediendo privilegios al usuario '$DEV_USER' en la base de datos '$DB_NAME'..."
psql -h $DB_HOST -p $DB_PORT -U $DB_USER -c "GRANT ALL PRIVILEGES ON DATABASE $DB_NAME TO $DEV_USER;"
