// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: jchannel.proto

package io.grpc.jchannelRpc;

public interface RepMsgForPyClientOrBuilder extends
    // @@protoc_insertion_point(interface_extends:cn.yingming.grpc1.RepMsgForPyClient)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.cn.yingming.grpc1.ConnectRepPy conRepPy = 1;</code>
   * @return Whether the conRepPy field is set.
   */
  boolean hasConRepPy();
  /**
   * <code>.cn.yingming.grpc1.ConnectRepPy conRepPy = 1;</code>
   * @return The conRepPy.
   */
  io.grpc.jchannelRpc.ConnectRepPy getConRepPy();
  /**
   * <code>.cn.yingming.grpc1.ConnectRepPy conRepPy = 1;</code>
   */
  io.grpc.jchannelRpc.ConnectRepPyOrBuilder getConRepPyOrBuilder();

  /**
   * <code>.cn.yingming.grpc1.DisconnectRepPy disconRepPy = 2;</code>
   * @return Whether the disconRepPy field is set.
   */
  boolean hasDisconRepPy();
  /**
   * <code>.cn.yingming.grpc1.DisconnectRepPy disconRepPy = 2;</code>
   * @return The disconRepPy.
   */
  io.grpc.jchannelRpc.DisconnectRepPy getDisconRepPy();
  /**
   * <code>.cn.yingming.grpc1.DisconnectRepPy disconRepPy = 2;</code>
   */
  io.grpc.jchannelRpc.DisconnectRepPyOrBuilder getDisconRepPyOrBuilder();

  /**
   * <code>.cn.yingming.grpc1.MessageRepPy msgRepPy = 3;</code>
   * @return Whether the msgRepPy field is set.
   */
  boolean hasMsgRepPy();
  /**
   * <code>.cn.yingming.grpc1.MessageRepPy msgRepPy = 3;</code>
   * @return The msgRepPy.
   */
  io.grpc.jchannelRpc.MessageRepPy getMsgRepPy();
  /**
   * <code>.cn.yingming.grpc1.MessageRepPy msgRepPy = 3;</code>
   */
  io.grpc.jchannelRpc.MessageRepPyOrBuilder getMsgRepPyOrBuilder();

  /**
   * <code>.cn.yingming.grpc1.StateRepPy stateRepPy = 4;</code>
   * @return Whether the stateRepPy field is set.
   */
  boolean hasStateRepPy();
  /**
   * <code>.cn.yingming.grpc1.StateRepPy stateRepPy = 4;</code>
   * @return The stateRepPy.
   */
  io.grpc.jchannelRpc.StateRepPy getStateRepPy();
  /**
   * <code>.cn.yingming.grpc1.StateRepPy stateRepPy = 4;</code>
   */
  io.grpc.jchannelRpc.StateRepPyOrBuilder getStateRepPyOrBuilder();

  /**
   * <code>.cn.yingming.grpc1.ClientViewPy clientViewPy = 5;</code>
   * @return Whether the clientViewPy field is set.
   */
  boolean hasClientViewPy();
  /**
   * <code>.cn.yingming.grpc1.ClientViewPy clientViewPy = 5;</code>
   * @return The clientViewPy.
   */
  io.grpc.jchannelRpc.ClientViewPy getClientViewPy();
  /**
   * <code>.cn.yingming.grpc1.ClientViewPy clientViewPy = 5;</code>
   */
  io.grpc.jchannelRpc.ClientViewPyOrBuilder getClientViewPyOrBuilder();

  /**
   * <code>.cn.yingming.grpc1.ServerViewPy serverViewPy = 6;</code>
   * @return Whether the serverViewPy field is set.
   */
  boolean hasServerViewPy();
  /**
   * <code>.cn.yingming.grpc1.ServerViewPy serverViewPy = 6;</code>
   * @return The serverViewPy.
   */
  io.grpc.jchannelRpc.ServerViewPy getServerViewPy();
  /**
   * <code>.cn.yingming.grpc1.ServerViewPy serverViewPy = 6;</code>
   */
  io.grpc.jchannelRpc.ServerViewPyOrBuilder getServerViewPyOrBuilder();

  /**
   * <code>.cn.yingming.grpc1.UpdateAddPy updateAddPy = 7;</code>
   * @return Whether the updateAddPy field is set.
   */
  boolean hasUpdateAddPy();
  /**
   * <code>.cn.yingming.grpc1.UpdateAddPy updateAddPy = 7;</code>
   * @return The updateAddPy.
   */
  io.grpc.jchannelRpc.UpdateAddPy getUpdateAddPy();
  /**
   * <code>.cn.yingming.grpc1.UpdateAddPy updateAddPy = 7;</code>
   */
  io.grpc.jchannelRpc.UpdateAddPyOrBuilder getUpdateAddPyOrBuilder();

  public io.grpc.jchannelRpc.RepMsgForPyClient.OneTypeCase getOneTypeCase();
}