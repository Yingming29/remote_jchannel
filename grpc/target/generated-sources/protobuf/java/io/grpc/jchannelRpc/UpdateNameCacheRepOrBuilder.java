// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: jchannel.proto

package io.grpc.jchannelRpc;

public interface UpdateNameCacheRepOrBuilder extends
    // @@protoc_insertion_point(interface_extends:cn.yingming.grpc1.UpdateNameCacheRep)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated bytes address = 1;</code>
   * @return A list containing the address.
   */
  java.util.List<com.google.protobuf.ByteString> getAddressList();
  /**
   * <code>repeated bytes address = 1;</code>
   * @return The count of address.
   */
  int getAddressCount();
  /**
   * <code>repeated bytes address = 1;</code>
   * @param index The index of the element to return.
   * @return The address at the given index.
   */
  com.google.protobuf.ByteString getAddress(int index);

  /**
   * <code>repeated string logical_name = 2;</code>
   * @return A list containing the logicalName.
   */
  java.util.List<java.lang.String>
      getLogicalNameList();
  /**
   * <code>repeated string logical_name = 2;</code>
   * @return The count of logicalName.
   */
  int getLogicalNameCount();
  /**
   * <code>repeated string logical_name = 2;</code>
   * @param index The index of the element to return.
   * @return The logicalName at the given index.
   */
  java.lang.String getLogicalName(int index);
  /**
   * <code>repeated string logical_name = 2;</code>
   * @param index The index of the value to return.
   * @return The bytes of the logicalName at the given index.
   */
  com.google.protobuf.ByteString
      getLogicalNameBytes(int index);
}