// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: jchannel.proto

package io.grpc.jchannelRpc;

public interface PrintProtocolSpecReqOrBuilder extends
    // @@protoc_insertion_point(interface_extends:cn.yingming.grpc1.PrintProtocolSpecReq)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string source = 1;</code>
   * @return The source.
   */
  java.lang.String getSource();
  /**
   * <code>string source = 1;</code>
   * @return The bytes for source.
   */
  com.google.protobuf.ByteString
      getSourceBytes();

  /**
   * <code>string jchannel_address = 2;</code>
   * @return The jchannelAddress.
   */
  java.lang.String getJchannelAddress();
  /**
   * <code>string jchannel_address = 2;</code>
   * @return The bytes for jchannelAddress.
   */
  com.google.protobuf.ByteString
      getJchannelAddressBytes();

  /**
   * <code>bool include_props = 3;</code>
   * @return The includeProps.
   */
  boolean getIncludeProps();
}