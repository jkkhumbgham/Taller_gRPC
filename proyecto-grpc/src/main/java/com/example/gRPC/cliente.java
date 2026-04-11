package com.example.gRPC;

import java.util.ArrayList;
import java.util.List;

import com.example.gRPC.entity.ConsultaLog;
import com.example.gRPC.entity.Profesor;
import com.example.gRPC.repository.ConsultaLogRepository;
import com.example.gRPC.repository.ProfesorRepository;

import io.grpc.ManagedChannel;
import io.grpc.StatusRuntimeException;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import jakarta.persistence.EntityManager;

public class cliente {

    public static void main(String[] args) {

        JpaUtil.init("clientePU");

        // ── Lee host y puerto del servidor desde variables de entorno ──
        String host = System.getenv().getOrDefault("SERVIDOR_HOST", "localhost");
        int port    = Integer.parseInt(System.getenv().getOrDefault("SERVIDOR_PORT", "50051"));
        System.out.println("HOST: " + host);
        System.out.println("PORT: " + port);
        ManagedChannel channel = NettyChannelBuilder
        .forAddress(new java.net.InetSocketAddress(host, port))
        .usePlaintext()
        .build();

        ProfesorServiceGrpc.ProfesorServiceBlockingStub stub =
                ProfesorServiceGrpc.newBlockingStub(channel);

        try {
            // --- Consulta de UN profesor por ID ---
            int idAConsultar = 1;
            ProfesorRequest request = ProfesorRequest.newBuilder()
                    .setId(idAConsultar)
                    .build();

            ProfesorResponse response = null;
            boolean exitoso = false;
            String mensajeError = null;

            try {
                response = stub.obtenerProfesor(request);
                exitoso = true;
                System.out.println("[Cliente] Recibido -> Nombre: " + response.getNombre()
                        + " | Especialidad: " + response.getEspecialidad());
            } catch (StatusRuntimeException e) {
                mensajeError = e.getStatus().getCode() + ": " + e.getStatus().getDescription();
                System.err.println("[Cliente] Error gRPC: " + mensajeError);
            }

            registrarConsulta(
                idAConsultar,
                response != null ? response.getNombre()       : null,
                response != null ? response.getEspecialidad() : null,
                exitoso,
                mensajeError
            );

            // --- Consulta de TODOS los profesores ---
            System.out.println("\n[Cliente] Solicitando lista completa de profesores...");
            ListaProfesoresResponse listaResponse = stub.obtenerTodosLosProfesores(
                    VacioRequest.newBuilder().build());

            registrarListaProfesores(listaResponse.getProfesoresList());

        } finally {
            channel.shutdown();
            JpaUtil.shutdown();
        }
    }

    private static void registrarConsulta(Integer profesorId, String nombre,
                                          String especialidad, boolean exitoso,
                                          String mensajeError) {
        EntityManager emLog = null;
        try {
            emLog = JpaUtil.getEntityManager();
            ConsultaLogRepository logRepo = new ConsultaLogRepository(emLog);
            logRepo.save(new ConsultaLog(profesorId, nombre, especialidad, exitoso, mensajeError));
        } catch (Exception e) {
            System.err.println("[Cliente] Error al guardar log: " + e.getMessage());
        } finally {
            JpaUtil.closeQuietly(emLog);
        }

        if (exitoso && nombre != null) {
            EntityManager emProf = null;
            try {
                emProf = JpaUtil.getEntityManager();
                ProfesorRepository profRepo = new ProfesorRepository(emProf);
                if (profRepo.findByNombre(nombre).isEmpty()) {
                    Profesor p = new Profesor();
                    p.setNombre(nombre);
                    p.setEspecialidad(especialidad);
                    profRepo.save(p);
                    System.out.println("[Cliente] Profesor guardado en bd_cliente: " + nombre);
                } else {
                    System.out.println("[Cliente] Profesor '" + nombre + "' ya existe en bd_cliente, se omite.");
                }
            } catch (Exception e) {
                System.err.println("[Cliente] Error al guardar profesor: " + e.getMessage());
            } finally {
                JpaUtil.closeQuietly(emProf);
            }
        }
    }

    private static void registrarListaProfesores(List<ProfesorResponse> lista) {
        if (lista == null || lista.isEmpty()) {
            System.out.println("[Cliente] Lista vacía recibida del servidor.");
            return;
        }

        List<ConsultaLog> logs = new ArrayList<>();

        for (ProfesorResponse r : lista) {
            EntityManager emProf = null;
            try {
                emProf = JpaUtil.getEntityManager();
                ProfesorRepository profRepo = new ProfesorRepository(emProf);
                if (profRepo.findByNombre(r.getNombre()).isEmpty()) {
                    Profesor p = new Profesor();
                    p.setNombre(r.getNombre());
                    p.setEspecialidad(r.getEspecialidad());
                    profRepo.save(p);
                    System.out.println("[Cliente] Profesor guardado: " + r.getNombre());
                } else {
                    System.out.println("[Cliente] Profesor '" + r.getNombre() + "' ya existe, se omite.");
                }
            } catch (Exception e) {
                System.err.println("[Cliente] Error al guardar profesor '" + r.getNombre() + "': " + e.getMessage());
            } finally {
                JpaUtil.closeQuietly(emProf);
            }

            logs.add(new ConsultaLog(r.getId(), r.getNombre(), r.getEspecialidad(), true, null));
        }

        EntityManager emLog = null;
        try {
            emLog = JpaUtil.getEntityManager();
            ConsultaLogRepository logRepo = new ConsultaLogRepository(emLog);
            logRepo.saveAll(logs);
            System.out.println("[Cliente] Lista procesada: " + lista.size() + " profesor(es).");
        } catch (Exception e) {
            System.err.println("[Cliente] Error al guardar logs de lista: " + e.getMessage());
        } finally {
            JpaUtil.closeQuietly(emLog);
        }
    }
}