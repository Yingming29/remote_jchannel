// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: jchannel.proto

package io.grpc.jchannelRpc;

public interface DisconnectReqOrBuilder extends
    // @@protoc_insertion_point(interface_extends:cn.yingming.grpc1.DisconnectReq)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string jchannel_address = 1;</code>
   * @return The jchannelAddress.
   */
  java.lang.String getJchannelAddress();
  /**
   * <code>string jchannel_address = 1;</code>
   * @return The bytes for jchannelAddress.
   */
  com.google.protobuf.ByteString
      getJchannelAddressBytes();

  /**
   * <code>string cluster = 2;</code>
   * @return The cluster.
   */
  java.lang.String getCluster();
  /**
   * <code>string cluster = 2;</code>
   * @return The bytes for cluster.
   */
  com.google.protobuf.ByteString
      getClusterBytes();

  /**
   * <code>string timestamp = 3;</code>
   * @return The timestamp.
   */
  java.lang.String getTimestamp();
  /**
   * <code>string timestamp = 3;</code>
   * @return The bytes for timestamp.
   */
  com.google.protobuf.ByteString
      getTimestampBytes();
}
