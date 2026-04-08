package com.example.gRPC;

import com.example.gRPC.entity.ConsultaLog;
import com.example.gRPC.repository.ConsultaLogRepository;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import jakarta.persistence.EntityManager;

/**
 * Cliente gRPC que consulta profesores al servidor
 * y registra cada consulta en su propia BD (bd_cliente) usando JPA.
 */
public class cliente {

    public static void main(String[] args) {

        // Inicializar JPA con la unidad de persistencia del cliente (bd_cliente)
        JpaUtil.init("clientePU");

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        ProfesorServiceGrpc.ProfesorServiceBlockingStub stub =
                ProfesorServiceGrpc.newBlockingStub(channel);

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

        } finally {
            channel.shutdown();
        }

        // Registrar la consulta en bd_cliente usando JPA
        registrarConsulta(
            idAConsultar,
            response != null ? response.getNombre()       : null,
            response != null ? response.getEspecialidad() : null,
            exitoso,
            mensajeError
        );

        JpaUtil.shutdown();
    }

    private static void registrarConsulta(int profesorId, String nombre,
                                          String especialidad, boolean exitoso,
                                          String mensajeError) {
        EntityManager em = null;
        try {
            em = JpaUtil.getEntityManager();
            ConsultaLogRepository repo = new ConsultaLogRepository(em);
            repo.save(new ConsultaLog(profesorId, nombre, especialidad, exitoso, mensajeError));
        } catch (Exception e) {
            System.err.println("[Cliente] Error al registrar log en BD: " + e.getMessage());
        } finally {
            JpaUtil.closeQuietly(em);
        }
    }
}
