-- ============================================================
-- Script SQL - Base de datos del SERVIDOR (bd_servidor)
-- Ejecutar conectado a: bd_servidor
-- ============================================================

-- Crear base de datos (ejecutar como superusuario fuera de una BD)
-- CREATE DATABASE bd_servidor;

-- Tabla principal de profesores (consultada por el servicio gRPC)
CREATE TABLE IF NOT EXISTS profesores (
    id           SERIAL PRIMARY KEY,
    nombre       VARCHAR(100) NOT NULL,
    especialidad VARCHAR(100) NOT NULL,
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Datos de ejemplo para pruebas
INSERT INTO profesores (nombre, especialidad) VALUES
    ('Juan Perez',    'Matematicas'),
    ('Ana Gomez',     'Fisica'),
    ('Carlos Torres', 'Programacion'),
    ('Maria Lopez',   'Quimica'),
    ('Luis Herrera',  'Historia');
