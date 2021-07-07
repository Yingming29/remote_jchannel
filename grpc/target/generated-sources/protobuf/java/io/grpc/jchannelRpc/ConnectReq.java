// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: jchannel.proto

package io.grpc.jchannelRpc;

/**
 * <pre>
 * connect() of JChannel, create a bidirectional streaming of grpc
 * </pre>
 *
 * Protobuf type {@code cn.yingming.grpc1.ConnectReq}
 */
public final class ConnectReq extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:cn.yingming.grpc1.ConnectReq)
    ConnectReqOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ConnectReq.newBuilder() to construct.
  private ConnectReq(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ConnectReq() {
    logicalName_ = "";
    jchannAddressByte_ = com.google.protobuf.ByteString.EMPTY;
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ConnectReq();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ConnectReq(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 8: {

            reconnect_ = input.readBool();
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            logicalName_ = s;
            break;
          }
          case 26: {

            jchannAddressByte_ = input.readBytes();
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return io.grpc.jchannelRpc.JChannelRpc.internal_static_cn_yingming_grpc1_ConnectReq_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.grpc.jchannelRpc.JChannelRpc.internal_static_cn_yingming_grpc1_ConnectReq_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.grpc.jchannelRpc.ConnectReq.class, io.grpc.jchannelRpc.ConnectReq.Builder.class);
  }

  public static final int RECONNECT_FIELD_NUMBER = 1;
  private boolean reconnect_;
  /**
   * <pre>
   * reconnect and jchannel_address will be used in the reconnection part.
   * </pre>
   *
   * <code>bool reconnect = 1;</code>
   * @return The reconnect.
   */
  @java.lang.Override
  public boolean getReconnect() {
    return reconnect_;
  }

  public static final int LOGICAL_NAME_FIELD_NUMBER = 2;
  private volatile java.lang.Object logicalName_;
  /**
   * <code>string logical_name = 2;</code>
   * @return The logicalName.
   */
  @java.lang.Override
  public java.lang.String getLogicalName() {
    java.lang.Object ref = logicalName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      logicalName_ = s;
      return s;
    }
  }
  /**
   * <code>string logical_name = 2;</code>
   * @return The bytes for logicalName.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getLogicalNameBytes() {
    java.lang.Object ref = logicalName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      logicalName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int JCHANN_ADDRESS_BYTE_FIELD_NUMBER = 3;
  private com.google.protobuf.ByteString jchannAddressByte_;
  /**
   * <code>bytes jchann_address_byte = 3;</code>
   * @return The jchannAddressByte.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString getJchannAddressByte() {
    return jchannAddressByte_;
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (reconnect_ != false) {
      output.writeBool(1, reconnect_);
    }
    if (!getLogicalNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, logicalName_);
    }
    if (!jchannAddressByte_.isEmpty()) {
      output.writeBytes(3, jchannAddressByte_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (reconnect_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(1, reconnect_);
    }
    if (!getLogicalNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, logicalName_);
    }
    if (!jchannAddressByte_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(3, jchannAddressByte_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof io.grpc.jchannelRpc.ConnectReq)) {
      return super.equals(obj);
    }
    io.grpc.jchannelRpc.ConnectReq other = (io.grpc.jchannelRpc.ConnectReq) obj;

    if (getReconnect()
        != other.getReconnect()) return false;
    if (!getLogicalName()
        .equals(other.getLogicalName())) return false;
    if (!getJchannAddressByte()
        .equals(other.getJchannAddressByte())) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + RECONNECT_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getReconnect());
    hash = (37 * hash) + LOGICAL_NAME_FIELD_NUMBER;
    hash = (53 * hash) + getLogicalName().hashCode();
    hash = (37 * hash) + JCHANN_ADDRESS_BYTE_FIELD_NUMBER;
    hash = (53 * hash) + getJchannAddressByte().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static io.grpc.jchannelRpc.ConnectReq parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.grpc.jchannelRpc.ConnectReq parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.grpc.jchannelRpc.ConnectReq parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.grpc.jchannelRpc.ConnectReq parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.grpc.jchannelRpc.ConnectReq parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.grpc.jchannelRpc.ConnectReq parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.grpc.jchannelRpc.ConnectReq parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.grpc.jchannelRpc.ConnectReq parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.grpc.jchannelRpc.ConnectReq parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static io.grpc.jchannelRpc.ConnectReq parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.grpc.jchannelRpc.ConnectReq parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.grpc.jchannelRpc.ConnectReq parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(io.grpc.jchannelRpc.ConnectReq prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   * connect() of JChannel, create a bidirectional streaming of grpc
   * </pre>
   *
   * Protobuf type {@code cn.yingming.grpc1.ConnectReq}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:cn.yingming.grpc1.ConnectReq)
      io.grpc.jchannelRpc.ConnectReqOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.grpc.jchannelRpc.JChannelRpc.internal_static_cn_yingming_grpc1_ConnectReq_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.grpc.jchannelRpc.JChannelRpc.internal_static_cn_yingming_grpc1_ConnectReq_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.grpc.jchannelRpc.ConnectReq.class, io.grpc.jchannelRpc.ConnectReq.Builder.class);
    }

    // Construct using io.grpc.jchannelRpc.ConnectReq.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      reconnect_ = false;

      logicalName_ = "";

      jchannAddressByte_ = com.google.protobuf.ByteString.EMPTY;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return io.grpc.jchannelRpc.JChannelRpc.internal_static_cn_yingming_grpc1_ConnectReq_descriptor;
    }

    @java.lang.Override
    public io.grpc.jchannelRpc.ConnectReq getDefaultInstanceForType() {
      return io.grpc.jchannelRpc.ConnectReq.getDefaultInstance();
    }

    @java.lang.Override
    public io.grpc.jchannelRpc.ConnectReq build() {
      io.grpc.jchannelRpc.ConnectReq result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public io.grpc.jchannelRpc.ConnectReq buildPartial() {
      io.grpc.jchannelRpc.ConnectReq result = new io.grpc.jchannelRpc.ConnectReq(this);
      result.reconnect_ = reconnect_;
      result.logicalName_ = logicalName_;
      result.jchannAddressByte_ = jchannAddressByte_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof io.grpc.jchannelRpc.ConnectReq) {
        return mergeFrom((io.grpc.jchannelRpc.ConnectReq)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(io.grpc.jchannelRpc.ConnectReq other) {
      if (other == io.grpc.jchannelRpc.ConnectReq.getDefaultInstance()) return this;
      if (other.getReconnect() != false) {
        setReconnect(other.getReconnect());
      }
      if (!other.getLogicalName().isEmpty()) {
        logicalName_ = other.logicalName_;
        onChanged();
      }
      if (other.getJchannAddressByte() != com.google.protobuf.ByteString.EMPTY) {
        setJchannAddressByte(other.getJchannAddressByte());
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      io.grpc.jchannelRpc.ConnectReq parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (io.grpc.jchannelRpc.ConnectReq) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private boolean reconnect_ ;
    /**
     * <pre>
     * reconnect and jchannel_address will be used in the reconnection part.
     * </pre>
     *
     * <code>bool reconnect = 1;</code>
     * @return The reconnect.
     */
    @java.lang.Override
    public boolean getReconnect() {
      return reconnect_;
    }
    /**
     * <pre>
     * reconnect and jchannel_address will be used in the reconnection part.
     * </pre>
     *
     * <code>bool reconnect = 1;</code>
     * @param value The reconnect to set.
     * @return This builder for chaining.
     */
    public Builder setReconnect(boolean value) {
      
      reconnect_ = value;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * reconnect and jchannel_address will be used in the reconnection part.
     * </pre>
     *
     * <code>bool reconnect = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearReconnect() {
      
      reconnect_ = false;
      onChanged();
      return this;
    }

    private java.lang.Object logicalName_ = "";
    /**
     * <code>string logical_name = 2;</code>
     * @return The logicalName.
     */
    public java.lang.String getLogicalName() {
      java.lang.Object ref = logicalName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        logicalName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string logical_name = 2;</code>
     * @return The bytes for logicalName.
     */
    public com.google.protobuf.ByteString
        getLogicalNameBytes() {
      java.lang.Object ref = logicalName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        logicalName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string logical_name = 2;</code>
     * @param value The logicalName to set.
     * @return This builder for chaining.
     */
    public Builder setLogicalName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      logicalName_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string logical_name = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearLogicalName() {
      
      logicalName_ = getDefaultInstance().getLogicalName();
      onChanged();
      return this;
    }
    /**
     * <code>string logical_name = 2;</code>
     * @param value The bytes for logicalName to set.
     * @return This builder for chaining.
     */
    public Builder setLogicalNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      logicalName_ = value;
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString jchannAddressByte_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes jchann_address_byte = 3;</code>
     * @return The jchannAddressByte.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getJchannAddressByte() {
      return jchannAddressByte_;
    }
    /**
     * <code>bytes jchann_address_byte = 3;</code>
     * @param value The jchannAddressByte to set.
     * @return This builder for chaining.
     */
    public Builder setJchannAddressByte(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      jchannAddressByte_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bytes jchann_address_byte = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearJchannAddressByte() {
      
      jchannAddressByte_ = getDefaultInstance().getJchannAddressByte();
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:cn.yingming.grpc1.ConnectReq)
  }

  // @@protoc_insertion_point(class_scope:cn.yingming.grpc1.ConnectReq)
  private static final io.grpc.jchannelRpc.ConnectReq DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new io.grpc.jchannelRpc.ConnectReq();
  }

  public static io.grpc.jchannelRpc.ConnectReq getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ConnectReq>
      PARSER = new com.google.protobuf.AbstractParser<ConnectReq>() {
    @java.lang.Override
    public ConnectReq parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ConnectReq(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ConnectReq> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ConnectReq> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public io.grpc.jchannelRpc.ConnectReq getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

