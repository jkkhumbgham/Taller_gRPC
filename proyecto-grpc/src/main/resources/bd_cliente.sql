-- ============================================================
-- Script SQL - Base de datos del CLIENTE (bd_cliente)
-- Ejecutar conectado a: bd_cliente
-- ============================================================

-- Crear base de datos (ejecutar como superusuario fuera de una BD)
-- CREATE DATABASE bd_cliente;

-- Tabla de log: registra cada consulta gRPC realizada por el cliente
CREATE TABLE IF NOT EXISTS consultas_log (
    id              SERIAL PRIMARY KEY,
    profesor_id     INT NOT NULL,
    nombre_obtenido VARCHAR(100),
    especialidad    VARCHAR(100),
    exitoso         BOOLEAN NOT NULL,
    mensaje_error   TEXT,
    fecha_consulta  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
