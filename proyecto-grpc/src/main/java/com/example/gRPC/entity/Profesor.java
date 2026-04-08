package com.example.gRPC.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entidad JPA que representa un profesor en la BD del servidor (bd_servidor).
 *
 * Hibernate crea/actualiza la tabla automaticamente con hbm2ddl.auto=update.
 * Esquema equivalente:
 *   CREATE TABLE profesores (
 *       id           SERIAL PRIMARY KEY,
 *       nombre       VARCHAR(100) NOT NULL,
 *       especialidad VARCHAR(100) NOT NULL,
 *       created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP
 *   );
 */
@Entity
@Table(name = "profesores")
public class Profesor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "especialidad", nullable = false, length = 100)
    private String especialidad;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // ---- Constructores ----

    public Profesor() {}

    public Profesor(String nombre, String especialidad) {
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    // ---- Getters y Setters ----

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    @Override
    public String toString() {
        return "Profesor{id=" + id + ", nombre='" + nombre + "', especialidad='" + especialidad + "'}";
    }
}
