package com.example.gRPC;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class servidor {

    public static void main(String[] args) throws Exception {

        // Inicializar JPA con la unidad de persistencia del servidor (bd_servidor)
        JpaUtil.init("servidorPU");

        Server server = ServerBuilder.forPort(50051)
                .addService(new ProfesorService())
                .build();

        server.start();
        System.out.println("[Servidor] gRPC corriendo en puerto 50051");

        // Cierre limpio al recibir Ctrl+C
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("[Servidor] Apagando...");
            server.shutdown();
            JpaUtil.shutdown();
        }));

        server.awaitTermination();
    }
}
