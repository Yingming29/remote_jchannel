// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: jchannel.proto

package io.grpc.jchannelRpc;

public interface StateRepOrBuilder extends
    // @@protoc_insertion_point(interface_extends:cn.yingming.grpc1.StateRep)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 size = 1;</code>
   * @return The size.
   */
  int getSize();

  /**
   * <code>repeated .cn.yingming.grpc1.MessageReqRep oneOfHistory = 2;</code>
   */
  java.util.List<io.grpc.jchannelRpc.MessageReqRep> 
      getOneOfHistoryList();
  /**
   * <code>repeated .cn.yingming.grpc1.MessageReqRep oneOfHistory = 2;</code>
   */
  io.grpc.jchannelRpc.MessageReqRep getOneOfHistory(int index);
  /**
   * <code>repeated .cn.yingming.grpc1.MessageReqRep oneOfHistory = 2;</code>
   */
  int getOneOfHistoryCount();
  /**
   * <code>repeated .cn.yingming.grpc1.MessageReqRep oneOfHistory = 2;</code>
   */
  java.util.List<? extends io.grpc.jchannelRpc.MessageReqRepOrBuilder> 
      getOneOfHistoryOrBuilderList();
  /**
   * <code>repeated .cn.yingming.grpc1.MessageReqRep oneOfHistory = 2;</code>
   */
  io.grpc.jchannelRpc.MessageReqRepOrBuilder getOneOfHistoryOrBuilder(
      int index);
}
