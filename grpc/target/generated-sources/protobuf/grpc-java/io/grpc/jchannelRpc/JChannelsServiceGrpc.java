package io.grpc.jchannelRpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.37.0)",
    comments = "Source: jchannel.proto")
public final class JChannelsServiceGrpc {

  private JChannelsServiceGrpc() {}

  public static final String SERVICE_NAME = "cn.yingming.grpc1.JChannelsService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<io.grpc.jchannelRpc.Request,
      io.grpc.jchannelRpc.Response> getConnectMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "connect",
      requestType = io.grpc.jchannelRpc.Request.class,
      responseType = io.grpc.jchannelRpc.Response.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<io.grpc.jchannelRpc.Request,
      io.grpc.jchannelRpc.Response> getConnectMethod() {
    io.grpc.MethodDescriptor<io.grpc.jchannelRpc.Request, io.grpc.jchannelRpc.Response> getConnectMethod;
    if ((getConnectMethod = JChannelsServiceGrpc.getConnectMethod) == null) {
      synchronized (JChannelsServiceGrpc.class) {
        if ((getConnectMethod = JChannelsServiceGrpc.getConnectMethod) == null) {
          JChannelsServiceGrpc.getConnectMethod = getConnectMethod =
              io.grpc.MethodDescriptor.<io.grpc.jchannelRpc.Request, io.grpc.jchannelRpc.Response>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "connect"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.jchannelRpc.Request.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.jchannelRpc.Response.getDefaultInstance()))
              .setSchemaDescriptor(new JChannelsServiceMethodDescriptorSupplier("connect"))
              .build();
        }
      }
    }
    return getConnectMethod;
  }

  private static volatile io.grpc.MethodDescriptor<io.grpc.jchannelRpc.ReqAsk,
      io.grpc.jchannelRpc.RepAsk> getAskMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ask",
      requestType = io.grpc.jchannelRpc.ReqAsk.class,
      responseType = io.grpc.jchannelRpc.RepAsk.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<io.grpc.jchannelRpc.ReqAsk,
      io.grpc.jchannelRpc.RepAsk> getAskMethod() {
    io.grpc.MethodDescriptor<io.grpc.jchannelRpc.ReqAsk, io.grpc.jchannelRpc.RepAsk> getAskMethod;
    if ((getAskMethod = JChannelsServiceGrpc.getAskMethod) == null) {
      synchronized (JChannelsServiceGrpc.class) {
        if ((getAskMethod = JChannelsServiceGrpc.getAskMethod) == null) {
          JChannelsServiceGrpc.getAskMethod = getAskMethod =
              io.grpc.MethodDescriptor.<io.grpc.jchannelRpc.ReqAsk, io.grpc.jchannelRpc.RepAsk>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ask"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.jchannelRpc.ReqAsk.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.grpc.jchannelRpc.RepAsk.getDefaultInstance()))
              .setSchemaDescriptor(new JChannelsServiceMethodDescriptorSupplier("ask"))
              .build();
        }
      }
    }
    return getAskMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static JChannelsServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<JChannelsServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<JChannelsServiceStub>() {
        @java.lang.Override
        public JChannelsServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new JChannelsServiceStub(channel, callOptions);
        }
      };
    return JChannelsServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static JChannelsServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<JChannelsServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<JChannelsServiceBlockingStub>() {
        @java.lang.Override
        public JChannelsServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new JChannelsServiceBlockingStub(channel, callOptions);
        }
      };
    return JChannelsServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static JChannelsServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<JChannelsServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<JChannelsServiceFutureStub>() {
        @java.lang.Override
        public JChannelsServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new JChannelsServiceFutureStub(channel, callOptions);
        }
      };
    return JChannelsServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class JChannelsServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public io.grpc.stub.StreamObserver<io.grpc.jchannelRpc.Request> connect(
        io.grpc.stub.StreamObserver<io.grpc.jchannelRpc.Response> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getConnectMethod(), responseObserver);
    }

    /**
     */
    public void ask(io.grpc.jchannelRpc.ReqAsk request,
        io.grpc.stub.StreamObserver<io.grpc.jchannelRpc.RepAsk> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAskMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getConnectMethod(),
            io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
              new MethodHandlers<
                io.grpc.jchannelRpc.Request,
                io.grpc.jchannelRpc.Response>(
                  this, METHODID_CONNECT)))
          .addMethod(
            getAskMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                io.grpc.jchannelRpc.ReqAsk,
                io.grpc.jchannelRpc.RepAsk>(
                  this, METHODID_ASK)))
          .build();
    }
  }

  /**
   */
  public static final class JChannelsServiceStub extends io.grpc.stub.AbstractAsyncStub<JChannelsServiceStub> {
    private JChannelsServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected JChannelsServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new JChannelsServiceStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.stub.StreamObserver<io.grpc.jchannelRpc.Request> connect(
        io.grpc.stub.StreamObserver<io.grpc.jchannelRpc.Response> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getConnectMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public void ask(io.grpc.jchannelRpc.ReqAsk request,
        io.grpc.stub.StreamObserver<io.grpc.jchannelRpc.RepAsk> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAskMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class JChannelsServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<JChannelsServiceBlockingStub> {
    private JChannelsServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected JChannelsServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new JChannelsServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public io.grpc.jchannelRpc.RepAsk ask(io.grpc.jchannelRpc.ReqAsk request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAskMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class JChannelsServiceFutureStub extends io.grpc.stub.AbstractFutureStub<JChannelsServiceFutureStub> {
    private JChannelsServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected JChannelsServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new JChannelsServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<io.grpc.jchannelRpc.RepAsk> ask(
        io.grpc.jchannelRpc.ReqAsk request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAskMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ASK = 0;
  private static final int METHODID_CONNECT = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final JChannelsServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(JChannelsServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ASK:
          serviceImpl.ask((io.grpc.jchannelRpc.ReqAsk) request,
              (io.grpc.stub.StreamObserver<io.grpc.jchannelRpc.RepAsk>) responseObserver);
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
        case METHODID_CONNECT:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.connect(
              (io.grpc.stub.StreamObserver<io.grpc.jchannelRpc.Response>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class JChannelsServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    JChannelsServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return io.grpc.jchannelRpc.JChannelRpc.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("JChannelsService");
    }
  }

  private static final class JChannelsServiceFileDescriptorSupplier
      extends JChannelsServiceBaseDescriptorSupplier {
    JChannelsServiceFileDescriptorSupplier() {}
  }

  private static final class JChannelsServiceMethodDescriptorSupplier
      extends JChannelsServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    JChannelsServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (JChannelsServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new JChannelsServiceFileDescriptorSupplier())
              .addMethod(getConnectMethod())
              .addMethod(getAskMethod())
              .build();
        }
      }
    }
    return result;
  }
}
