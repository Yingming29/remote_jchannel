// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: jchannel.proto

package io.grpc.jchannelRpc;

public interface ServerViewPyOrBuilder extends
    // @@protoc_insertion_point(interface_extends:cn.yingming.grpc1.ServerViewPy)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 num = 1;</code>
   * @return The num.
   */
  int getNum();

  /**
   * <code>repeated string members = 2;</code>
   * @return A list containing the members.
   */
  java.util.List<java.lang.String>
      getMembersList();
  /**
   * <code>repeated string members = 2;</code>
   * @return The count of members.
   */
  int getMembersCount();
  /**
   * <code>repeated string members = 2;</code>
   * @param index The index of the element to return.
   * @return The members at the given index.
   */
  java.lang.String getMembers(int index);
  /**
   * <code>repeated string members = 2;</code>
   * @param index The index of the value to return.
   * @return The bytes of the members at the given index.
   */
  com.google.protobuf.ByteString
      getMembersBytes(int index);

  /**
   * <code>string coordinator = 3;</code>
   * @return The coordinator.
   */
  java.lang.String getCoordinator();
  /**
   * <code>string coordinator = 3;</code>
   * @return The bytes for coordinator.
   */
  com.google.protobuf.ByteString
      getCoordinatorBytes();

  /**
   * <code>int32 size = 4;</code>
   * @return The size.
   */
  int getSize();
}
