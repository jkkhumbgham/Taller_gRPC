CREATE TABLE IF NOT EXISTS profesores (
    id          SERIAL PRIMARY KEY,
    nombre      VARCHAR(100) NOT NULL,
    especialidad VARCHAR(100) NOT NULL,
    created_at  TIMESTAMP
);

INSERT INTO profesores (nombre, especialidad, created_at) VALUES
    ('Ana García',     'Matemáticas',  NOW()),
    ('Carlos López',   'Física',       NOW()),
    ('María Martínez', 'Química',      NOW()),
    ('Juan Rodríguez', 'Historia',     NOW()),
    ('Laura Sánchez',  'Inglés',       NOW());