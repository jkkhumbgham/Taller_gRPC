package com.example.gRPC;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Utilitaria JPA: gestiona los EntityManagerFactory para cada
 * unidad de persistencia definida en persistence.xml.
 *
 * Uso en el SERVIDOR:
 *   JpaUtil.init("servidorPU");
 *   EntityManager em = JpaUtil.getEntityManager();
 *
 * Uso en el CLIENTE:
 *   JpaUtil.init("clientePU");
 *   EntityManager em = JpaUtil.getEntityManager();
 */
public class JpaUtil {

    private static EntityManagerFactory emf;

    /**
     * Inicializa el EntityManagerFactory con la unidad de persistencia indicada.
     * Debe llamarse una sola vez al arrancar la aplicacion.
     *
     * @param persistenceUnit nombre del persistence-unit en persistence.xml
     *                        ("servidorPU" o "clientePU")
     */
    public static void init(String persistenceUnit) {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
        emf = Persistence.createEntityManagerFactory(persistenceUnit);
        System.out.println("[JPA] EntityManagerFactory creado para: " + persistenceUnit);
    }

    /**
     * Devuelve un nuevo EntityManager. El llamador es responsable de cerrarlo.
     */
    public static EntityManager getEntityManager() {
        if (emf == null || !emf.isOpen()) {
            throw new IllegalStateException(
                "JpaUtil no ha sido inicializado. Llama a JpaUtil.init(persistenceUnit) primero.");
        }
        return emf.createEntityManager();
    }

    /**
     * Cierra el EntityManagerFactory al apagar la aplicacion.
     */
    public static void shutdown() {
        if (emf != null && emf.isOpen()) {
            emf.close();
            System.out.println("[JPA] EntityManagerFactory cerrado.");
        }
    }

    /**
     * Cierra un EntityManager de forma segura (null-safe).
     */
    public static void closeQuietly(EntityManager em) {
        if (em != null && em.isOpen()) {
            try {
                em.close();
            } catch (Exception e) {
                System.err.println("[JPA] Error cerrando EntityManager: " + e.getMessage());
            }
        }
    }
}
