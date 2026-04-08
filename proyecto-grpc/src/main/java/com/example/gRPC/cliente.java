package com.example.gRPC;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class cliente {

    public static void main(String[] args) {

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        ProfesorServiceGrpc.ProfesorServiceBlockingStub stub =
                ProfesorServiceGrpc.newBlockingStub(channel);

        ProfesorRequest request = ProfesorRequest.newBuilder()
                .setId(1)
                .build();

        ProfesorResponse response = stub.obtenerProfesor(request);

        System.out.println("Profesor: " + response.getNombre());

        channel.shutdown();
    }
}
