package com.example.gRPC;

import io.grpc.stub.StreamObserver;

public class ProfesorService extends ProfesorServiceGrpc.ProfesorServiceImplBase {

    @Override
    public void obtenerProfesor(ProfesorRequest request,
                                StreamObserver<ProfesorResponse> responseObserver) {

        ProfesorResponse response = ProfesorResponse.newBuilder()
                .setId(request.getId())
                .setNombre("Juan Perez")
                .setEspecialidad("Matemáticas")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
