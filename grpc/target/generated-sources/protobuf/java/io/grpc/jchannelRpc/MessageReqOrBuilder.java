// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: jchannel.proto

package io.grpc.jchannelRpc;

public interface MessageReqOrBuilder extends
    // @@protoc_insertion_point(interface_extends:cn.yingming.grpc1.MessageReq)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * uuid
   * </pre>
   *
   * <code>string source = 1;</code>
   * @return The source.
   */
  java.lang.String getSource();
  /**
   * <pre>
   * uuid
   * </pre>
   *
   * <code>string source = 1;</code>
   * @return The bytes for source.
   */
  com.google.protobuf.ByteString
      getSourceBytes();

  /**
   * <pre>
   * the fake address of client jchannel, sender
   * </pre>
   *
   * <code>string jchannel_address = 2;</code>
   * @return The jchannelAddress.
   */
  java.lang.String getJchannelAddress();
  /**
   * <pre>
   * the fake address of client jchannel, sender
   * </pre>
   *
   * <code>string jchannel_address = 2;</code>
   * @return The bytes for jchannelAddress.
   */
  com.google.protobuf.ByteString
      getJchannelAddressBytes();

  /**
   * <pre>
   * the cluster of jchannel-client
   * </pre>
   *
   * <code>string cluster = 3;</code>
   * @return The cluster.
   */
  java.lang.String getCluster();
  /**
   * <pre>
   * the cluster of jchannel-client
   * </pre>
   *
   * <code>string cluster = 3;</code>
   * @return The bytes for cluster.
   */
  com.google.protobuf.ByteString
      getClusterBytes();

  /**
   * <pre>
   * content of message
   * </pre>
   *
   * <code>string content = 4;</code>
   * @return The content.
   */
  java.lang.String getContent();
  /**
   * <pre>
   * content of message
   * </pre>
   *
   * <code>string content = 4;</code>
   * @return The bytes for content.
   */
  com.google.protobuf.ByteString
      getContentBytes();

  /**
   * <pre>
   * time
   * </pre>
   *
   * <code>string timestamp = 5;</code>
   * @return The timestamp.
   */
  java.lang.String getTimestamp();
  /**
   * <pre>
   * time
   * </pre>
   *
   * <code>string timestamp = 5;</code>
   * @return The bytes for timestamp.
   */
  com.google.protobuf.ByteString
      getTimestampBytes();

  /**
   * <pre>
   * "" means the broadcast in this cluster, receivers
   * </pre>
   *
   * <code>string destination = 6;</code>
   * @return The destination.
   */
  java.lang.String getDestination();
  /**
   * <pre>
   * "" means the broadcast in this cluster, receivers
   * </pre>
   *
   * <code>string destination = 6;</code>
   * @return The bytes for destination.
   */
  com.google.protobuf.ByteString
      getDestinationBytes();
}
