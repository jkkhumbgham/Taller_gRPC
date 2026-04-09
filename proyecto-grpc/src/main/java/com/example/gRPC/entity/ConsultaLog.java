package com.example.gRPC.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "consultas_log")
public class ConsultaLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "profesor_id", nullable = false)
    private Integer profesorId;

    @Column(name = "nombre_obtenido", length = 100)
    private String nombreObtenido;

    @Column(name = "especialidad", length = 100)
    private String especialidad;

    @Column(name = "exitoso", nullable = false)
    private Boolean exitoso;

    @Column(name = "mensaje_error", columnDefinition = "TEXT")
    private String mensajeError;

    @Column(name = "fecha_consulta", updatable = false)
    private LocalDateTime fechaConsulta;

    @PrePersist
    protected void onCreate() {
        this.fechaConsulta = LocalDateTime.now();
    }

    // ---- Constructor util ----

    public ConsultaLog() {}

    public ConsultaLog(Integer profesorId, String nombreObtenido,
                       String especialidad, Boolean exitoso, String mensajeError) {
        this.profesorId     = profesorId;
        this.nombreObtenido = nombreObtenido;
        this.especialidad   = especialidad;
        this.exitoso        = exitoso;
        this.mensajeError   = mensajeError;
    }

    // ---- Getters y Setters ----

    public Integer getId() { return id; }

    public Integer getProfesorId() { return profesorId; }
    public void setProfesorId(Integer profesorId) { this.profesorId = profesorId; }

    public String getNombreObtenido() { return nombreObtenido; }
    public void setNombreObtenido(String nombreObtenido) { this.nombreObtenido = nombreObtenido; }

    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }

    public Boolean getExitoso() { return exitoso; }
    public void setExitoso(Boolean exitoso) { this.exitoso = exitoso; }

    public String getMensajeError() { return mensajeError; }
    public void setMensajeError(String mensajeError) { this.mensajeError = mensajeError; }

    public LocalDateTime getFechaConsulta() { return fechaConsulta; }

    @Override
    public String toString() {
        return "ConsultaLog{id=" + id + ", profesorId=" + profesorId
                + ", exitoso=" + exitoso + ", fecha=" + fechaConsulta + "}";
    }
}
