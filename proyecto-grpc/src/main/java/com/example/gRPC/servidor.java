package com.example.gRPC;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

public class servidor {

    public static void main(String[] args) throws Exception {

        Server server = ServerBuilder.forPort(50051)
                .addService(new ProfesorService())
                .build();

        server.start();
        System.out.println("Servidor gRPC corriendo en puerto 50051");

        server.awaitTermination();
    }
}