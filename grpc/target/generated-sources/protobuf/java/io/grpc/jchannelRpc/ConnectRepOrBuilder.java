// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: jchannel.proto

package io.grpc.jchannelRpc;

public interface ConnectRepOrBuilder extends
    // @@protoc_insertion_point(interface_extends:cn.yingming.grpc1.ConnectRep)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>bool result = 1;</code>
   * @return The result.
   */
  boolean getResult();

  /**
   * <pre>
   * generated Address object's byte
   * </pre>
   *
   * <code>bytes address = 2;</code>
   * @return The address.
   */
  com.google.protobuf.ByteString getAddress();

  /**
   * <pre>
   * generated Address 'logical name
   * </pre>
   *
   * <code>string logical_name = 3;</code>
   * @return The logicalName.
   */
  java.lang.String getLogicalName();
  /**
   * <pre>
   * generated Address 'logical name
   * </pre>
   *
   * <code>string logical_name = 3;</code>
   * @return The bytes for logicalName.
   */
  com.google.protobuf.ByteString
      getLogicalNameBytes();
}
