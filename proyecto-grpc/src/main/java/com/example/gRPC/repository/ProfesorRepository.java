package com.example.gRPC.repository;

import com.example.gRPC.entity.Profesor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.Optional;

/**
 * Repositorio JPA para la entidad Profesor.
 * Opera sobre la unidad de persistencia "servidorPU" (bd_servidor).
 */
public class ProfesorRepository {

    private final EntityManager em;

    public ProfesorRepository(EntityManager em) {
        this.em = em;
    }

    /**
     * Busca un profesor por su ID.
     * @param id identificador del profesor
     * @return Optional con el profesor si existe, o empty si no
     */
    public Optional<Profesor> findById(int id) {
        Profesor profesor = em.find(Profesor.class, id);
        return Optional.ofNullable(profesor);
    }

    /**
     * Busca un profesor por nombre (busqueda exacta).
     */
    public Optional<Profesor> findByNombre(String nombre) {
        try {
            TypedQuery<Profesor> query = em.createQuery(
                "SELECT p FROM Profesor p WHERE p.nombre = :nombre", Profesor.class);
            query.setParameter("nombre", nombre);
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
    /**
     * Retorna todos los profesores de la BD.
     */
    public java.util.List<Profesor> findAll() {
        TypedQuery<Profesor> query = em.createQuery(
            "SELECT p FROM Profesor p", Profesor.class);
        return query.getResultList();
    }

    /**
     * Persiste un nuevo profesor en la BD.
     */
    public void save(Profesor profesor) {
        em.getTransaction().begin();
        em.persist(profesor);
        em.getTransaction().commit();
    }
}
