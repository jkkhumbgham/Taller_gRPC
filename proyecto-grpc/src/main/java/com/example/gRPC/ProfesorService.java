package com.example.gRPC;

import com.example.gRPC.entity.Profesor;
import com.example.gRPC.repository.ProfesorRepository;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public class ProfesorService extends ProfesorServiceGrpc.ProfesorServiceImplBase {

    @Override
    public void obtenerProfesor(ProfesorRequest request,
                                StreamObserver<ProfesorResponse> responseObserver) {

        int profesorId = request.getId();
        EntityManager em = null;

        try {
            em = JpaUtil.getEntityManager();
            ProfesorRepository repo = new ProfesorRepository(em);

            Optional<Profesor> resultado = repo.findById(profesorId);

            if (resultado.isPresent()) {
                Profesor p = resultado.get();
                ProfesorResponse response = ProfesorResponse.newBuilder()
                        .setId(p.getId())
                        .setNombre(p.getNombre())
                        .setEspecialidad(p.getEspecialidad())
                        .build();

                responseObserver.onNext(response);
                responseObserver.onCompleted();
                System.out.println("[Servidor] Enviado: " + p);

            } else {
                responseObserver.onError(
                    Status.NOT_FOUND
                        .withDescription("No se encontro un profesor con id=" + profesorId)
                        .asRuntimeException()
                );
                System.err.println("[Servidor] Profesor id=" + profesorId + " no encontrado.");
            }

        } catch (Exception e) {
            System.err.println("[Servidor] Error JPA: " + e.getMessage());
            responseObserver.onError(
                Status.INTERNAL
                    .withDescription("Error interno: " + e.getMessage())
                    .asRuntimeException()
            );
        } finally {
            JpaUtil.closeQuietly(em);
        }
    }
}
