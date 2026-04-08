package com.example.gRPC.repository;

import com.example.gRPC.entity.ConsultaLog;
import jakarta.persistence.EntityManager;

/**
 * Repositorio JPA para la entidad ConsultaLog.
 * Opera sobre la unidad de persistencia "clientePU" (bd_cliente).
 */
public class ConsultaLogRepository {

    private final EntityManager em;

    public ConsultaLogRepository(EntityManager em) {
        this.em = em;
    }

    /**
     * Persiste un registro de consulta gRPC en la BD del cliente.
     */
    public void save(ConsultaLog log) {
        em.getTransaction().begin();
        em.persist(log);
        em.getTransaction().commit();
        System.out.println("[Cliente] Consulta registrada en bd_cliente: " + log);
    }
}
