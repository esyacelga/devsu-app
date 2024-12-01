-- Crear la base de datos 'devsu'
CREATE DATABASE devsu;

-- Cambiar la contraseña del usuario por defecto 'postgres'
ALTER USER postgres WITH PASSWORD 'seya1922';

-- (Opcional) Conectar a la base de datos 'devsu' y realizar configuraciones adicionales
\connect devsu;

-- Crear una tabla ejemplo (opcional)
CREATE TABLE ejemplo (
                         id SERIAL PRIMARY KEY,
                         nombre VARCHAR(255) NOT NULL
);

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- Tabla Persona
CREATE TABLE IF NOT EXISTS public.persona (
                                              id UUID NOT NULL,                          -- ID generado desde la aplicación
                                              nombre VARCHAR(255) NOT NULL,
    genero VARCHAR(50) NOT NULL,
    edad VARCHAR(255) NOT NULL,
    identificacion VARCHAR(50) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    CONSTRAINT persona_pkey PRIMARY KEY (id),
    CONSTRAINT persona_identificacion_key UNIQUE (identificacion)
    );

ALTER TABLE IF EXISTS public.persona
    OWNER TO postgres;

-- Tabla Cliente
CREATE TABLE IF NOT EXISTS public.cliente (
                                              clienteid UUID NOT NULL,                   -- ID generado desde la aplicación
                                              persona_id UUID NOT NULL,
                                              estado BOOLEAN NOT NULL DEFAULT true,
                                              contrasenia VARCHAR(255) NOT NULL,
    CONSTRAINT cliente_pkey PRIMARY KEY (clienteid),
    CONSTRAINT cliente_persona_id_key UNIQUE (persona_id),
    CONSTRAINT fk_cliente_persona FOREIGN KEY (persona_id)
    REFERENCES public.persona (id)
    ON UPDATE NO ACTION
    ON DELETE CASCADE
    );

ALTER TABLE IF EXISTS public.cliente
    OWNER TO postgres;

-- Tabla Cuenta
CREATE TABLE IF NOT EXISTS public.cuenta (
                                             id UUID NOT NULL,                          -- ID generado desde la aplicación
                                             id_persona UUID NOT NULL,                  -- Relación con Persona
                                             numero_cuenta VARCHAR(20) NOT NULL UNIQUE,
    tipo_cuenta VARCHAR(50) NOT NULL,
    saldo_inicial_estado NUMERIC(15, 2) DEFAULT 0,
    CONSTRAINT cuenta_pkey PRIMARY KEY (id),
    CONSTRAINT fk_cuenta_persona FOREIGN KEY (id_persona) -- Relación con Persona
    REFERENCES public.persona (id)
    ON UPDATE NO ACTION
    ON DELETE CASCADE
    );

ALTER TABLE IF EXISTS public.cuenta
    OWNER TO postgres;

-- Tabla Movimientos
CREATE TABLE IF NOT EXISTS public.movimientos (
                                                  id UUID NOT NULL,                          -- ID generado desde la aplicación
                                                  id_cuenta UUID NOT NULL REFERENCES cuenta(id),
    fecha_movimiento TIMESTAMP NOT NULL DEFAULT NOW(),
    tipo_movimiento VARCHAR(50) NOT NULL,
    valor NUMERIC(15, 2) NOT NULL,
    saldo NUMERIC(15, 2) NOT NULL,
    CONSTRAINT movimientos_pkey PRIMARY KEY (id),
    CHECK (valor <> 0),
    CHECK (tipo_movimiento IN ('Débito', 'Crédito'))
    );

-- Tabla Auditoría
CREATE TABLE IF NOT EXISTS public.auditoria_transacciones (
                                                              id UUID NOT NULL DEFAULT uuid_generate_v4(),
    id_movimiento UUID NOT NULL,
    accion VARCHAR(50) NOT NULL,
    usuario_auditoria VARCHAR(50),
    fecha_auditoria TIMESTAMP DEFAULT NOW(),
    detalles JSONB,
    CONSTRAINT auditoria_transacciones_pkey PRIMARY KEY (id)
    );

-- Función para Registrar Auditoría
CREATE OR REPLACE FUNCTION registrar_auditoria()
RETURNS TRIGGER AS $$
BEGIN
    IF (TG_OP = 'DELETE') THEN
        INSERT INTO auditoria_transacciones (id_movimiento, accion, usuario_auditoria, detalles)
        VALUES (
            OLD.id,
            TG_OP,
            current_user,
            row_to_json(OLD)
        );
ELSE
        INSERT INTO auditoria_transacciones (id_movimiento, accion, usuario_auditoria, detalles)
        VALUES (
            NEW.id,
            TG_OP,
            current_user,
            row_to_json(NEW)
        );
END IF;
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Asociar Trigger a Movimientos
CREATE TRIGGER trigger_auditoria_movimientos
    AFTER INSERT OR UPDATE OR DELETE ON movimientos
    FOR EACH ROW
    EXECUTE FUNCTION registrar_auditoria();
