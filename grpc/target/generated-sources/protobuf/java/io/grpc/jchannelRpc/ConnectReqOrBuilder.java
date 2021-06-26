// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: jchannel.proto

package io.grpc.jchannelRpc;

public interface ConnectReqOrBuilder extends
    // @@protoc_insertion_point(interface_extends:cn.yingming.grpc1.ConnectReq)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * cluster
   * </pre>
   *
   * <code>string cluster = 1;</code>
   * @return The cluster.
   */
  java.lang.String getCluster();
  /**
   * <pre>
   * cluster
   * </pre>
   *
   * <code>string cluster = 1;</code>
   * @return The bytes for cluster.
   */
  com.google.protobuf.ByteString
      getClusterBytes();

  /**
   * <code>string timestamp = 2;</code>
   * @return The timestamp.
   */
  java.lang.String getTimestamp();
  /**
   * <code>string timestamp = 2;</code>
   * @return The bytes for timestamp.
   */
  com.google.protobuf.ByteString
      getTimestampBytes();

  /**
   * <pre>
   * reconnect and jchannel_address will be used in the reconnection part.
   * </pre>
   *
   * <code>bool reconnect = 3;</code>
   * @return The reconnect.
   */
  boolean getReconnect();

  /**
   * <code>string jchannel_address = 4;</code>
   * @return The jchannelAddress.
   */
  java.lang.String getJchannelAddress();
  /**
   * <code>string jchannel_address = 4;</code>
   * @return The bytes for jchannelAddress.
   */
  com.google.protobuf.ByteString
      getJchannelAddressBytes();

  /**
   * <code>string logical_name = 5;</code>
   * @return The logicalName.
   */
  java.lang.String getLogicalName();
  /**
   * <code>string logical_name = 5;</code>
   * @return The bytes for logicalName.
   */
  com.google.protobuf.ByteString
      getLogicalNameBytes();
}
