// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: jchannel.proto

package io.grpc.jchannelRpc;

public interface RequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:cn.yingming.grpc1.Request)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.cn.yingming.grpc1.MessageReq messageRequest = 1;</code>
   * @return Whether the messageRequest field is set.
   */
  boolean hasMessageRequest();
  /**
   * <code>.cn.yingming.grpc1.MessageReq messageRequest = 1;</code>
   * @return The messageRequest.
   */
  io.grpc.jchannelRpc.MessageReq getMessageRequest();
  /**
   * <code>.cn.yingming.grpc1.MessageReq messageRequest = 1;</code>
   */
  io.grpc.jchannelRpc.MessageReqOrBuilder getMessageRequestOrBuilder();

  /**
   * <code>.cn.yingming.grpc1.ConnectReq connectRequest = 2;</code>
   * @return Whether the connectRequest field is set.
   */
  boolean hasConnectRequest();
  /**
   * <code>.cn.yingming.grpc1.ConnectReq connectRequest = 2;</code>
   * @return The connectRequest.
   */
  io.grpc.jchannelRpc.ConnectReq getConnectRequest();
  /**
   * <code>.cn.yingming.grpc1.ConnectReq connectRequest = 2;</code>
   */
  io.grpc.jchannelRpc.ConnectReqOrBuilder getConnectRequestOrBuilder();

  /**
   * <code>.cn.yingming.grpc1.DisconnectReq disconnectRequest = 3;</code>
   * @return Whether the disconnectRequest field is set.
   */
  boolean hasDisconnectRequest();
  /**
   * <code>.cn.yingming.grpc1.DisconnectReq disconnectRequest = 3;</code>
   * @return The disconnectRequest.
   */
  io.grpc.jchannelRpc.DisconnectReq getDisconnectRequest();
  /**
   * <code>.cn.yingming.grpc1.DisconnectReq disconnectRequest = 3;</code>
   */
  io.grpc.jchannelRpc.DisconnectReqOrBuilder getDisconnectRequestOrBuilder();

  /**
   * <code>.cn.yingming.grpc1.StateReq stateReq = 4;</code>
   * @return Whether the stateReq field is set.
   */
  boolean hasStateReq();
  /**
   * <code>.cn.yingming.grpc1.StateReq stateReq = 4;</code>
   * @return The stateReq.
   */
  io.grpc.jchannelRpc.StateReq getStateReq();
  /**
   * <code>.cn.yingming.grpc1.StateReq stateReq = 4;</code>
   */
  io.grpc.jchannelRpc.StateReqOrBuilder getStateReqOrBuilder();

  public io.grpc.jchannelRpc.Request.OneTypeCase getOneTypeCase();
}
