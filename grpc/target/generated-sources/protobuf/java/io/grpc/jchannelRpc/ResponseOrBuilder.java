// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: jchannel.proto

package io.grpc.jchannelRpc;

public interface ResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:cn.yingming.grpc1.Response)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.cn.yingming.grpc1.MessageRep messageResponse = 1;</code>
   * @return Whether the messageResponse field is set.
   */
  boolean hasMessageResponse();
  /**
   * <code>.cn.yingming.grpc1.MessageRep messageResponse = 1;</code>
   * @return The messageResponse.
   */
  io.grpc.jchannelRpc.MessageRep getMessageResponse();
  /**
   * <code>.cn.yingming.grpc1.MessageRep messageResponse = 1;</code>
   */
  io.grpc.jchannelRpc.MessageRepOrBuilder getMessageResponseOrBuilder();

  /**
   * <code>.cn.yingming.grpc1.ConnectRep connectResponse = 2;</code>
   * @return Whether the connectResponse field is set.
   */
  boolean hasConnectResponse();
  /**
   * <code>.cn.yingming.grpc1.ConnectRep connectResponse = 2;</code>
   * @return The connectResponse.
   */
  io.grpc.jchannelRpc.ConnectRep getConnectResponse();
  /**
   * <code>.cn.yingming.grpc1.ConnectRep connectResponse = 2;</code>
   */
  io.grpc.jchannelRpc.ConnectRepOrBuilder getConnectResponseOrBuilder();

  /**
   * <code>.cn.yingming.grpc1.DisconnectRep disconnectResponse = 3;</code>
   * @return Whether the disconnectResponse field is set.
   */
  boolean hasDisconnectResponse();
  /**
   * <code>.cn.yingming.grpc1.DisconnectRep disconnectResponse = 3;</code>
   * @return The disconnectResponse.
   */
  io.grpc.jchannelRpc.DisconnectRep getDisconnectResponse();
  /**
   * <code>.cn.yingming.grpc1.DisconnectRep disconnectResponse = 3;</code>
   */
  io.grpc.jchannelRpc.DisconnectRepOrBuilder getDisconnectResponseOrBuilder();

  /**
   * <code>.cn.yingming.grpc1.UpdateRep updateResponse = 4;</code>
   * @return Whether the updateResponse field is set.
   */
  boolean hasUpdateResponse();
  /**
   * <code>.cn.yingming.grpc1.UpdateRep updateResponse = 4;</code>
   * @return The updateResponse.
   */
  io.grpc.jchannelRpc.UpdateRep getUpdateResponse();
  /**
   * <code>.cn.yingming.grpc1.UpdateRep updateResponse = 4;</code>
   */
  io.grpc.jchannelRpc.UpdateRepOrBuilder getUpdateResponseOrBuilder();

  /**
   * <pre>
   *  client view
   * </pre>
   *
   * <code>.cn.yingming.grpc1.ViewRep viewResponse = 5;</code>
   * @return Whether the viewResponse field is set.
   */
  boolean hasViewResponse();
  /**
   * <pre>
   *  client view
   * </pre>
   *
   * <code>.cn.yingming.grpc1.ViewRep viewResponse = 5;</code>
   * @return The viewResponse.
   */
  io.grpc.jchannelRpc.ViewRep getViewResponse();
  /**
   * <pre>
   *  client view
   * </pre>
   *
   * <code>.cn.yingming.grpc1.ViewRep viewResponse = 5;</code>
   */
  io.grpc.jchannelRpc.ViewRepOrBuilder getViewResponseOrBuilder();

  /**
   * <code>.cn.yingming.grpc1.StateRep stateRep = 6;</code>
   * @return Whether the stateRep field is set.
   */
  boolean hasStateRep();
  /**
   * <code>.cn.yingming.grpc1.StateRep stateRep = 6;</code>
   * @return The stateRep.
   */
  io.grpc.jchannelRpc.StateRep getStateRep();
  /**
   * <code>.cn.yingming.grpc1.StateRep stateRep = 6;</code>
   */
  io.grpc.jchannelRpc.StateRepOrBuilder getStateRepOrBuilder();

  /**
   * <code>.cn.yingming.grpc1.StateMsg_withTarget_1 stateMsg1 = 7;</code>
   * @return Whether the stateMsg1 field is set.
   */
  boolean hasStateMsg1();
  /**
   * <code>.cn.yingming.grpc1.StateMsg_withTarget_1 stateMsg1 = 7;</code>
   * @return The stateMsg1.
   */
  io.grpc.jchannelRpc.StateMsg_withTarget_1 getStateMsg1();
  /**
   * <code>.cn.yingming.grpc1.StateMsg_withTarget_1 stateMsg1 = 7;</code>
   */
  io.grpc.jchannelRpc.StateMsg_withTarget_1OrBuilder getStateMsg1OrBuilder();

  /**
   * <code>.cn.yingming.grpc1.StateMsg_withTarget_2 stateMsg2 = 8;</code>
   * @return Whether the stateMsg2 field is set.
   */
  boolean hasStateMsg2();
  /**
   * <code>.cn.yingming.grpc1.StateMsg_withTarget_2 stateMsg2 = 8;</code>
   * @return The stateMsg2.
   */
  io.grpc.jchannelRpc.StateMsg_withTarget_2 getStateMsg2();
  /**
   * <code>.cn.yingming.grpc1.StateMsg_withTarget_2 stateMsg2 = 8;</code>
   */
  io.grpc.jchannelRpc.StateMsg_withTarget_2OrBuilder getStateMsg2OrBuilder();

  /**
   * <code>.cn.yingming.grpc1.GetAddressRep getAddressRep = 9;</code>
   * @return Whether the getAddressRep field is set.
   */
  boolean hasGetAddressRep();
  /**
   * <code>.cn.yingming.grpc1.GetAddressRep getAddressRep = 9;</code>
   * @return The getAddressRep.
   */
  io.grpc.jchannelRpc.GetAddressRep getGetAddressRep();
  /**
   * <code>.cn.yingming.grpc1.GetAddressRep getAddressRep = 9;</code>
   */
  io.grpc.jchannelRpc.GetAddressRepOrBuilder getGetAddressRepOrBuilder();

  /**
   * <code>.cn.yingming.grpc1.GetNameRep getNameRep = 10;</code>
   * @return Whether the getNameRep field is set.
   */
  boolean hasGetNameRep();
  /**
   * <code>.cn.yingming.grpc1.GetNameRep getNameRep = 10;</code>
   * @return The getNameRep.
   */
  io.grpc.jchannelRpc.GetNameRep getGetNameRep();
  /**
   * <code>.cn.yingming.grpc1.GetNameRep getNameRep = 10;</code>
   */
  io.grpc.jchannelRpc.GetNameRepOrBuilder getGetNameRepOrBuilder();

  /**
   * <code>.cn.yingming.grpc1.GetClusterNameRep getClusterNameRep = 11;</code>
   * @return Whether the getClusterNameRep field is set.
   */
  boolean hasGetClusterNameRep();
  /**
   * <code>.cn.yingming.grpc1.GetClusterNameRep getClusterNameRep = 11;</code>
   * @return The getClusterNameRep.
   */
  io.grpc.jchannelRpc.GetClusterNameRep getGetClusterNameRep();
  /**
   * <code>.cn.yingming.grpc1.GetClusterNameRep getClusterNameRep = 11;</code>
   */
  io.grpc.jchannelRpc.GetClusterNameRepOrBuilder getGetClusterNameRepOrBuilder();

  /**
   * <code>.cn.yingming.grpc1.PrintProtocolSpecRep printProtoRep = 12;</code>
   * @return Whether the printProtoRep field is set.
   */
  boolean hasPrintProtoRep();
  /**
   * <code>.cn.yingming.grpc1.PrintProtocolSpecRep printProtoRep = 12;</code>
   * @return The printProtoRep.
   */
  io.grpc.jchannelRpc.PrintProtocolSpecRep getPrintProtoRep();
  /**
   * <code>.cn.yingming.grpc1.PrintProtocolSpecRep printProtoRep = 12;</code>
   */
  io.grpc.jchannelRpc.PrintProtocolSpecRepOrBuilder getPrintProtoRepOrBuilder();

  /**
   * <pre>
   * server view
   * </pre>
   *
   * <code>.cn.yingming.grpc1.ViewRep_server viewRep_server = 13;</code>
   * @return Whether the viewRepServer field is set.
   */
  boolean hasViewRepServer();
  /**
   * <pre>
   * server view
   * </pre>
   *
   * <code>.cn.yingming.grpc1.ViewRep_server viewRep_server = 13;</code>
   * @return The viewRepServer.
   */
  io.grpc.jchannelRpc.ViewRep_server getViewRepServer();
  /**
   * <pre>
   * server view
   * </pre>
   *
   * <code>.cn.yingming.grpc1.ViewRep_server viewRep_server = 13;</code>
   */
  io.grpc.jchannelRpc.ViewRep_serverOrBuilder getViewRepServerOrBuilder();

  public io.grpc.jchannelRpc.Response.OneTypeCase getOneTypeCase();
}
