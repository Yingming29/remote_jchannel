// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: jchannel.proto

package io.grpc.jchannelRpc;

public interface ResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:cn.yingming.grpc1.Response)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.cn.yingming.grpc1.MessageReqRep messageReqRep = 1;</code>
   * @return Whether the messageReqRep field is set.
   */
  boolean hasMessageReqRep();
  /**
   * <code>.cn.yingming.grpc1.MessageReqRep messageReqRep = 1;</code>
   * @return The messageReqRep.
   */
  io.grpc.jchannelRpc.MessageReqRep getMessageReqRep();
  /**
   * <code>.cn.yingming.grpc1.MessageReqRep messageReqRep = 1;</code>
   */
  io.grpc.jchannelRpc.MessageReqRepOrBuilder getMessageReqRepOrBuilder();

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
   * <code>.cn.yingming.grpc1.ViewRep viewResponse = 5;</code>
   * @return Whether the viewResponse field is set.
   */
  boolean hasViewResponse();
  /**
   * <code>.cn.yingming.grpc1.ViewRep viewResponse = 5;</code>
   * @return The viewResponse.
   */
  io.grpc.jchannelRpc.ViewRep getViewResponse();
  /**
   * <code>.cn.yingming.grpc1.ViewRep viewResponse = 5;</code>
   */
  io.grpc.jchannelRpc.ViewRepOrBuilder getViewResponseOrBuilder();

  /**
   * <code>.cn.yingming.grpc1.ViewRep_server viewRep_server = 6;</code>
   * @return Whether the viewRepServer field is set.
   */
  boolean hasViewRepServer();
  /**
   * <code>.cn.yingming.grpc1.ViewRep_server viewRep_server = 6;</code>
   * @return The viewRepServer.
   */
  io.grpc.jchannelRpc.ViewRep_server getViewRepServer();
  /**
   * <code>.cn.yingming.grpc1.ViewRep_server viewRep_server = 6;</code>
   */
  io.grpc.jchannelRpc.ViewRep_serverOrBuilder getViewRepServerOrBuilder();

  /**
   * <code>.cn.yingming.grpc1.UpdateNameCacheRep updateNameCache = 7;</code>
   * @return Whether the updateNameCache field is set.
   */
  boolean hasUpdateNameCache();
  /**
   * <code>.cn.yingming.grpc1.UpdateNameCacheRep updateNameCache = 7;</code>
   * @return The updateNameCache.
   */
  io.grpc.jchannelRpc.UpdateNameCacheRep getUpdateNameCache();
  /**
   * <code>.cn.yingming.grpc1.UpdateNameCacheRep updateNameCache = 7;</code>
   */
  io.grpc.jchannelRpc.UpdateNameCacheRepOrBuilder getUpdateNameCacheOrBuilder();

  /**
   * <code>.cn.yingming.grpc1.StateRep stateRep = 8;</code>
   * @return Whether the stateRep field is set.
   */
  boolean hasStateRep();
  /**
   * <code>.cn.yingming.grpc1.StateRep stateRep = 8;</code>
   * @return The stateRep.
   */
  io.grpc.jchannelRpc.StateRep getStateRep();
  /**
   * <code>.cn.yingming.grpc1.StateRep stateRep = 8;</code>
   */
  io.grpc.jchannelRpc.StateRepOrBuilder getStateRepOrBuilder();

  /**
   * <code>.cn.yingming.grpc1.GetAddressRep getAddressRep = 11;</code>
   * @return Whether the getAddressRep field is set.
   */
  boolean hasGetAddressRep();
  /**
   * <code>.cn.yingming.grpc1.GetAddressRep getAddressRep = 11;</code>
   * @return The getAddressRep.
   */
  io.grpc.jchannelRpc.GetAddressRep getGetAddressRep();
  /**
   * <code>.cn.yingming.grpc1.GetAddressRep getAddressRep = 11;</code>
   */
  io.grpc.jchannelRpc.GetAddressRepOrBuilder getGetAddressRepOrBuilder();

  /**
   * <code>.cn.yingming.grpc1.GetNameRep getNameRep = 12;</code>
   * @return Whether the getNameRep field is set.
   */
  boolean hasGetNameRep();
  /**
   * <code>.cn.yingming.grpc1.GetNameRep getNameRep = 12;</code>
   * @return The getNameRep.
   */
  io.grpc.jchannelRpc.GetNameRep getGetNameRep();
  /**
   * <code>.cn.yingming.grpc1.GetNameRep getNameRep = 12;</code>
   */
  io.grpc.jchannelRpc.GetNameRepOrBuilder getGetNameRepOrBuilder();

  /**
   * <code>.cn.yingming.grpc1.GetClusterNameRep getClusterNameRep = 13;</code>
   * @return Whether the getClusterNameRep field is set.
   */
  boolean hasGetClusterNameRep();
  /**
   * <code>.cn.yingming.grpc1.GetClusterNameRep getClusterNameRep = 13;</code>
   * @return The getClusterNameRep.
   */
  io.grpc.jchannelRpc.GetClusterNameRep getGetClusterNameRep();
  /**
   * <code>.cn.yingming.grpc1.GetClusterNameRep getClusterNameRep = 13;</code>
   */
  io.grpc.jchannelRpc.GetClusterNameRepOrBuilder getGetClusterNameRepOrBuilder();

  /**
   * <code>.cn.yingming.grpc1.PrintProtocolSpecRep printProtoRep = 14;</code>
   * @return Whether the printProtoRep field is set.
   */
  boolean hasPrintProtoRep();
  /**
   * <code>.cn.yingming.grpc1.PrintProtocolSpecRep printProtoRep = 14;</code>
   * @return The printProtoRep.
   */
  io.grpc.jchannelRpc.PrintProtocolSpecRep getPrintProtoRep();
  /**
   * <code>.cn.yingming.grpc1.PrintProtocolSpecRep printProtoRep = 14;</code>
   */
  io.grpc.jchannelRpc.PrintProtocolSpecRepOrBuilder getPrintProtoRepOrBuilder();

  /**
   * <code>.cn.yingming.grpc1.GetPropertyRep getPropertyRep = 15;</code>
   * @return Whether the getPropertyRep field is set.
   */
  boolean hasGetPropertyRep();
  /**
   * <code>.cn.yingming.grpc1.GetPropertyRep getPropertyRep = 15;</code>
   * @return The getPropertyRep.
   */
  io.grpc.jchannelRpc.GetPropertyRep getGetPropertyRep();
  /**
   * <code>.cn.yingming.grpc1.GetPropertyRep getPropertyRep = 15;</code>
   */
  io.grpc.jchannelRpc.GetPropertyRepOrBuilder getGetPropertyRepOrBuilder();

  /**
   * <code>.cn.yingming.grpc1.SetStatsRep setStatsRep = 16;</code>
   * @return Whether the setStatsRep field is set.
   */
  boolean hasSetStatsRep();
  /**
   * <code>.cn.yingming.grpc1.SetStatsRep setStatsRep = 16;</code>
   * @return The setStatsRep.
   */
  io.grpc.jchannelRpc.SetStatsRep getSetStatsRep();
  /**
   * <code>.cn.yingming.grpc1.SetStatsRep setStatsRep = 16;</code>
   */
  io.grpc.jchannelRpc.SetStatsRepOrBuilder getSetStatsRepOrBuilder();

  /**
   * <code>.cn.yingming.grpc1.GetStatsRep getStatsRep = 17;</code>
   * @return Whether the getStatsRep field is set.
   */
  boolean hasGetStatsRep();
  /**
   * <code>.cn.yingming.grpc1.GetStatsRep getStatsRep = 17;</code>
   * @return The getStatsRep.
   */
  io.grpc.jchannelRpc.GetStatsRep getGetStatsRep();
  /**
   * <code>.cn.yingming.grpc1.GetStatsRep getStatsRep = 17;</code>
   */
  io.grpc.jchannelRpc.GetStatsRepOrBuilder getGetStatsRepOrBuilder();

  /**
   * <code>.cn.yingming.grpc1.DumpStatsRep dumpStatsRep = 18;</code>
   * @return Whether the dumpStatsRep field is set.
   */
  boolean hasDumpStatsRep();
  /**
   * <code>.cn.yingming.grpc1.DumpStatsRep dumpStatsRep = 18;</code>
   * @return The dumpStatsRep.
   */
  io.grpc.jchannelRpc.DumpStatsRep getDumpStatsRep();
  /**
   * <code>.cn.yingming.grpc1.DumpStatsRep dumpStatsRep = 18;</code>
   */
  io.grpc.jchannelRpc.DumpStatsRepOrBuilder getDumpStatsRepOrBuilder();

  /**
   * <code>.cn.yingming.grpc1.SetDiscardOwnMsgRep setDiscardOwnRep = 19;</code>
   * @return Whether the setDiscardOwnRep field is set.
   */
  boolean hasSetDiscardOwnRep();
  /**
   * <code>.cn.yingming.grpc1.SetDiscardOwnMsgRep setDiscardOwnRep = 19;</code>
   * @return The setDiscardOwnRep.
   */
  io.grpc.jchannelRpc.SetDiscardOwnMsgRep getSetDiscardOwnRep();
  /**
   * <code>.cn.yingming.grpc1.SetDiscardOwnMsgRep setDiscardOwnRep = 19;</code>
   */
  io.grpc.jchannelRpc.SetDiscardOwnMsgRepOrBuilder getSetDiscardOwnRepOrBuilder();

  /**
   * <code>.cn.yingming.grpc1.GetDiscardOwnMsgRep getDiscardOwnRep = 20;</code>
   * @return Whether the getDiscardOwnRep field is set.
   */
  boolean hasGetDiscardOwnRep();
  /**
   * <code>.cn.yingming.grpc1.GetDiscardOwnMsgRep getDiscardOwnRep = 20;</code>
   * @return The getDiscardOwnRep.
   */
  io.grpc.jchannelRpc.GetDiscardOwnMsgRep getGetDiscardOwnRep();
  /**
   * <code>.cn.yingming.grpc1.GetDiscardOwnMsgRep getDiscardOwnRep = 20;</code>
   */
  io.grpc.jchannelRpc.GetDiscardOwnMsgRepOrBuilder getGetDiscardOwnRepOrBuilder();

  /**
   * <code>.cn.yingming.grpc1.GetStateRep getStateRep = 21;</code>
   * @return Whether the getStateRep field is set.
   */
  boolean hasGetStateRep();
  /**
   * <code>.cn.yingming.grpc1.GetStateRep getStateRep = 21;</code>
   * @return The getStateRep.
   */
  io.grpc.jchannelRpc.GetStateRep getGetStateRep();
  /**
   * <code>.cn.yingming.grpc1.GetStateRep getStateRep = 21;</code>
   */
  io.grpc.jchannelRpc.GetStateRepOrBuilder getGetStateRepOrBuilder();

  /**
   * <code>.cn.yingming.grpc1.IsStateRep isStateRep = 22;</code>
   * @return Whether the isStateRep field is set.
   */
  boolean hasIsStateRep();
  /**
   * <code>.cn.yingming.grpc1.IsStateRep isStateRep = 22;</code>
   * @return The isStateRep.
   */
  io.grpc.jchannelRpc.IsStateRep getIsStateRep();
  /**
   * <code>.cn.yingming.grpc1.IsStateRep isStateRep = 22;</code>
   */
  io.grpc.jchannelRpc.IsStateRepOrBuilder getIsStateRepOrBuilder();

  /**
   * <code>.cn.yingming.grpc1.RepMsgForPyClient pyRepMsg = 23;</code>
   * @return Whether the pyRepMsg field is set.
   */
  boolean hasPyRepMsg();
  /**
   * <code>.cn.yingming.grpc1.RepMsgForPyClient pyRepMsg = 23;</code>
   * @return The pyRepMsg.
   */
  io.grpc.jchannelRpc.RepMsgForPyClient getPyRepMsg();
  /**
   * <code>.cn.yingming.grpc1.RepMsgForPyClient pyRepMsg = 23;</code>
   */
  io.grpc.jchannelRpc.RepMsgForPyClientOrBuilder getPyRepMsgOrBuilder();

  public io.grpc.jchannelRpc.Response.OneTypeCase getOneTypeCase();
}
