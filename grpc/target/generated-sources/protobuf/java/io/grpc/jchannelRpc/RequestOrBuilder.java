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

  /**
   * <code>.cn.yingming.grpc1.StateMsg_withTarget_1 stateMsg1 = 5;</code>
   * @return Whether the stateMsg1 field is set.
   */
  boolean hasStateMsg1();
  /**
   * <code>.cn.yingming.grpc1.StateMsg_withTarget_1 stateMsg1 = 5;</code>
   * @return The stateMsg1.
   */
  io.grpc.jchannelRpc.StateMsg_withTarget_1 getStateMsg1();
  /**
   * <code>.cn.yingming.grpc1.StateMsg_withTarget_1 stateMsg1 = 5;</code>
   */
  io.grpc.jchannelRpc.StateMsg_withTarget_1OrBuilder getStateMsg1OrBuilder();

  /**
   * <code>.cn.yingming.grpc1.StateMsg_withTarget_2 stateMsg2 = 6;</code>
   * @return Whether the stateMsg2 field is set.
   */
  boolean hasStateMsg2();
  /**
   * <code>.cn.yingming.grpc1.StateMsg_withTarget_2 stateMsg2 = 6;</code>
   * @return The stateMsg2.
   */
  io.grpc.jchannelRpc.StateMsg_withTarget_2 getStateMsg2();
  /**
   * <code>.cn.yingming.grpc1.StateMsg_withTarget_2 stateMsg2 = 6;</code>
   */
  io.grpc.jchannelRpc.StateMsg_withTarget_2OrBuilder getStateMsg2OrBuilder();

  /**
   * <code>.cn.yingming.grpc1.GetAddressReq getAddressReq = 7;</code>
   * @return Whether the getAddressReq field is set.
   */
  boolean hasGetAddressReq();
  /**
   * <code>.cn.yingming.grpc1.GetAddressReq getAddressReq = 7;</code>
   * @return The getAddressReq.
   */
  io.grpc.jchannelRpc.GetAddressReq getGetAddressReq();
  /**
   * <code>.cn.yingming.grpc1.GetAddressReq getAddressReq = 7;</code>
   */
  io.grpc.jchannelRpc.GetAddressReqOrBuilder getGetAddressReqOrBuilder();

  /**
   * <code>.cn.yingming.grpc1.GetNameReq getNameReq = 8;</code>
   * @return Whether the getNameReq field is set.
   */
  boolean hasGetNameReq();
  /**
   * <code>.cn.yingming.grpc1.GetNameReq getNameReq = 8;</code>
   * @return The getNameReq.
   */
  io.grpc.jchannelRpc.GetNameReq getGetNameReq();
  /**
   * <code>.cn.yingming.grpc1.GetNameReq getNameReq = 8;</code>
   */
  io.grpc.jchannelRpc.GetNameReqOrBuilder getGetNameReqOrBuilder();

  public io.grpc.jchannelRpc.Request.OneTypeCase getOneTypeCase();
}
