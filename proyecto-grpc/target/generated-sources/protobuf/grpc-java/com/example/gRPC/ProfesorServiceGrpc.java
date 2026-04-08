package com.example.gRPC;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.60.0)",
    comments = "Source: profesor.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ProfesorServiceGrpc {

  private ProfesorServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "ProfesorService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.example.gRPC.ProfesorRequest,
      com.example.gRPC.ProfesorResponse> getObtenerProfesorMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ObtenerProfesor",
      requestType = com.example.gRPC.ProfesorRequest.class,
      responseType = com.example.gRPC.ProfesorResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.gRPC.ProfesorRequest,
      com.example.gRPC.ProfesorResponse> getObtenerProfesorMethod() {
    io.grpc.MethodDescriptor<com.example.gRPC.ProfesorRequest, com.example.gRPC.ProfesorResponse> getObtenerProfesorMethod;
    if ((getObtenerProfesorMethod = ProfesorServiceGrpc.getObtenerProfesorMethod) == null) {
      synchronized (ProfesorServiceGrpc.class) {
        if ((getObtenerProfesorMethod = ProfesorServiceGrpc.getObtenerProfesorMethod) == null) {
          ProfesorServiceGrpc.getObtenerProfesorMethod = getObtenerProfesorMethod =
              io.grpc.MethodDescriptor.<com.example.gRPC.ProfesorRequest, com.example.gRPC.ProfesorResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ObtenerProfesor"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.gRPC.ProfesorRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.gRPC.ProfesorResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ProfesorServiceMethodDescriptorSupplier("ObtenerProfesor"))
              .build();
        }
      }
    }
    return getObtenerProfesorMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ProfesorServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProfesorServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProfesorServiceStub>() {
        @java.lang.Override
        public ProfesorServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProfesorServiceStub(channel, callOptions);
        }
      };
    return ProfesorServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ProfesorServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProfesorServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProfesorServiceBlockingStub>() {
        @java.lang.Override
        public ProfesorServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProfesorServiceBlockingStub(channel, callOptions);
        }
      };
    return ProfesorServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ProfesorServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProfesorServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProfesorServiceFutureStub>() {
        @java.lang.Override
        public ProfesorServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProfesorServiceFutureStub(channel, callOptions);
        }
      };
    return ProfesorServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void obtenerProfesor(com.example.gRPC.ProfesorRequest request,
        io.grpc.stub.StreamObserver<com.example.gRPC.ProfesorResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getObtenerProfesorMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ProfesorService.
   */
  public static abstract class ProfesorServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ProfesorServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ProfesorService.
   */
  public static final class ProfesorServiceStub
      extends io.grpc.stub.AbstractAsyncStub<ProfesorServiceStub> {
    private ProfesorServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProfesorServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProfesorServiceStub(channel, callOptions);
    }

    /**
     */
    public void obtenerProfesor(com.example.gRPC.ProfesorRequest request,
        io.grpc.stub.StreamObserver<com.example.gRPC.ProfesorResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getObtenerProfesorMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ProfesorService.
   */
  public static final class ProfesorServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ProfesorServiceBlockingStub> {
    private ProfesorServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProfesorServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProfesorServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.gRPC.ProfesorResponse obtenerProfesor(com.example.gRPC.ProfesorRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getObtenerProfesorMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ProfesorService.
   */
  public static final class ProfesorServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<ProfesorServiceFutureStub> {
    private ProfesorServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProfesorServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProfesorServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.gRPC.ProfesorResponse> obtenerProfesor(
        com.example.gRPC.ProfesorRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getObtenerProfesorMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_OBTENER_PROFESOR = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_OBTENER_PROFESOR:
          serviceImpl.obtenerProfesor((com.example.gRPC.ProfesorRequest) request,
              (io.grpc.stub.StreamObserver<com.example.gRPC.ProfesorResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getObtenerProfesorMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.gRPC.ProfesorRequest,
              com.example.gRPC.ProfesorResponse>(
                service, METHODID_OBTENER_PROFESOR)))
        .build();
  }

  private static abstract class ProfesorServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ProfesorServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.gRPC.Profesor.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ProfesorService");
    }
  }

  private static final class ProfesorServiceFileDescriptorSupplier
      extends ProfesorServiceBaseDescriptorSupplier {
    ProfesorServiceFileDescriptorSupplier() {}
  }

  private static final class ProfesorServiceMethodDescriptorSupplier
      extends ProfesorServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    ProfesorServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ProfesorServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ProfesorServiceFileDescriptorSupplier())
              .addMethod(getObtenerProfesorMethod())
              .build();
        }
      }
    }
    return result;
  }
}
