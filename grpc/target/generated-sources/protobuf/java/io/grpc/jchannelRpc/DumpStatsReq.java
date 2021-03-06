// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: jchannel.proto

package io.grpc.jchannelRpc;

/**
 * Protobuf type {@code cn.yingming.grpc1.DumpStatsReq}
 */
public final class DumpStatsReq extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:cn.yingming.grpc1.DumpStatsReq)
    DumpStatsReqOrBuilder {
private static final long serialVersionUID = 0L;
  // Use DumpStatsReq.newBuilder() to construct.
  private DumpStatsReq(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private DumpStatsReq() {
    jchannelAddress_ = "";
    protocolName_ = "";
    attrs_ = com.google.protobuf.LazyStringArrayList.EMPTY;
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new DumpStatsReq();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private DumpStatsReq(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
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
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            jchannelAddress_ = s;
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            protocolName_ = s;
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              attrs_ = new com.google.protobuf.LazyStringArrayList();
              mutable_bitField0_ |= 0x00000001;
            }
            attrs_.add(s);
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
      if (((mutable_bitField0_ & 0x00000001) != 0)) {
        attrs_ = attrs_.getUnmodifiableView();
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return io.grpc.jchannelRpc.JChannelRpc.internal_static_cn_yingming_grpc1_DumpStatsReq_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.grpc.jchannelRpc.JChannelRpc.internal_static_cn_yingming_grpc1_DumpStatsReq_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.grpc.jchannelRpc.DumpStatsReq.class, io.grpc.jchannelRpc.DumpStatsReq.Builder.class);
  }

  public static final int JCHANNEL_ADDRESS_FIELD_NUMBER = 1;
  private volatile java.lang.Object jchannelAddress_;
  /**
   * <code>string jchannel_address = 1;</code>
   * @return The jchannelAddress.
   */
  @java.lang.Override
  public java.lang.String getJchannelAddress() {
    java.lang.Object ref = jchannelAddress_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      jchannelAddress_ = s;
      return s;
    }
  }
  /**
   * <code>string jchannel_address = 1;</code>
   * @return The bytes for jchannelAddress.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getJchannelAddressBytes() {
    java.lang.Object ref = jchannelAddress_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      jchannelAddress_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int PROTOCOL_NAME_FIELD_NUMBER = 2;
  private volatile java.lang.Object protocolName_;
  /**
   * <code>string protocol_name = 2;</code>
   * @return The protocolName.
   */
  @java.lang.Override
  public java.lang.String getProtocolName() {
    java.lang.Object ref = protocolName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      protocolName_ = s;
      return s;
    }
  }
  /**
   * <code>string protocol_name = 2;</code>
   * @return The bytes for protocolName.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getProtocolNameBytes() {
    java.lang.Object ref = protocolName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      protocolName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int ATTRS_FIELD_NUMBER = 3;
  private com.google.protobuf.LazyStringList attrs_;
  /**
   * <code>repeated string attrs = 3;</code>
   * @return A list containing the attrs.
   */
  public com.google.protobuf.ProtocolStringList
      getAttrsList() {
    return attrs_;
  }
  /**
   * <code>repeated string attrs = 3;</code>
   * @return The count of attrs.
   */
  public int getAttrsCount() {
    return attrs_.size();
  }
  /**
   * <code>repeated string attrs = 3;</code>
   * @param index The index of the element to return.
   * @return The attrs at the given index.
   */
  public java.lang.String getAttrs(int index) {
    return attrs_.get(index);
  }
  /**
   * <code>repeated string attrs = 3;</code>
   * @param index The index of the value to return.
   * @return The bytes of the attrs at the given index.
   */
  public com.google.protobuf.ByteString
      getAttrsBytes(int index) {
    return attrs_.getByteString(index);
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
    if (!getJchannelAddressBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, jchannelAddress_);
    }
    if (!getProtocolNameBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, protocolName_);
    }
    for (int i = 0; i < attrs_.size(); i++) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, attrs_.getRaw(i));
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getJchannelAddressBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, jchannelAddress_);
    }
    if (!getProtocolNameBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, protocolName_);
    }
    {
      int dataSize = 0;
      for (int i = 0; i < attrs_.size(); i++) {
        dataSize += computeStringSizeNoTag(attrs_.getRaw(i));
      }
      size += dataSize;
      size += 1 * getAttrsList().size();
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
    if (!(obj instanceof io.grpc.jchannelRpc.DumpStatsReq)) {
      return super.equals(obj);
    }
    io.grpc.jchannelRpc.DumpStatsReq other = (io.grpc.jchannelRpc.DumpStatsReq) obj;

    if (!getJchannelAddress()
        .equals(other.getJchannelAddress())) return false;
    if (!getProtocolName()
        .equals(other.getProtocolName())) return false;
    if (!getAttrsList()
        .equals(other.getAttrsList())) return false;
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
    hash = (37 * hash) + JCHANNEL_ADDRESS_FIELD_NUMBER;
    hash = (53 * hash) + getJchannelAddress().hashCode();
    hash = (37 * hash) + PROTOCOL_NAME_FIELD_NUMBER;
    hash = (53 * hash) + getProtocolName().hashCode();
    if (getAttrsCount() > 0) {
      hash = (37 * hash) + ATTRS_FIELD_NUMBER;
      hash = (53 * hash) + getAttrsList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static io.grpc.jchannelRpc.DumpStatsReq parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.grpc.jchannelRpc.DumpStatsReq parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.grpc.jchannelRpc.DumpStatsReq parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.grpc.jchannelRpc.DumpStatsReq parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.grpc.jchannelRpc.DumpStatsReq parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.grpc.jchannelRpc.DumpStatsReq parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.grpc.jchannelRpc.DumpStatsReq parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.grpc.jchannelRpc.DumpStatsReq parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.grpc.jchannelRpc.DumpStatsReq parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static io.grpc.jchannelRpc.DumpStatsReq parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.grpc.jchannelRpc.DumpStatsReq parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.grpc.jchannelRpc.DumpStatsReq parseFrom(
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
  public static Builder newBuilder(io.grpc.jchannelRpc.DumpStatsReq prototype) {
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
   * Protobuf type {@code cn.yingming.grpc1.DumpStatsReq}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:cn.yingming.grpc1.DumpStatsReq)
      io.grpc.jchannelRpc.DumpStatsReqOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.grpc.jchannelRpc.JChannelRpc.internal_static_cn_yingming_grpc1_DumpStatsReq_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.grpc.jchannelRpc.JChannelRpc.internal_static_cn_yingming_grpc1_DumpStatsReq_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.grpc.jchannelRpc.DumpStatsReq.class, io.grpc.jchannelRpc.DumpStatsReq.Builder.class);
    }

    // Construct using io.grpc.jchannelRpc.DumpStatsReq.newBuilder()
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
      jchannelAddress_ = "";

      protocolName_ = "";

      attrs_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return io.grpc.jchannelRpc.JChannelRpc.internal_static_cn_yingming_grpc1_DumpStatsReq_descriptor;
    }

    @java.lang.Override
    public io.grpc.jchannelRpc.DumpStatsReq getDefaultInstanceForType() {
      return io.grpc.jchannelRpc.DumpStatsReq.getDefaultInstance();
    }

    @java.lang.Override
    public io.grpc.jchannelRpc.DumpStatsReq build() {
      io.grpc.jchannelRpc.DumpStatsReq result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public io.grpc.jchannelRpc.DumpStatsReq buildPartial() {
      io.grpc.jchannelRpc.DumpStatsReq result = new io.grpc.jchannelRpc.DumpStatsReq(this);
      int from_bitField0_ = bitField0_;
      result.jchannelAddress_ = jchannelAddress_;
      result.protocolName_ = protocolName_;
      if (((bitField0_ & 0x00000001) != 0)) {
        attrs_ = attrs_.getUnmodifiableView();
        bitField0_ = (bitField0_ & ~0x00000001);
      }
      result.attrs_ = attrs_;
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
      if (other instanceof io.grpc.jchannelRpc.DumpStatsReq) {
        return mergeFrom((io.grpc.jchannelRpc.DumpStatsReq)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(io.grpc.jchannelRpc.DumpStatsReq other) {
      if (other == io.grpc.jchannelRpc.DumpStatsReq.getDefaultInstance()) return this;
      if (!other.getJchannelAddress().isEmpty()) {
        jchannelAddress_ = other.jchannelAddress_;
        onChanged();
      }
      if (!other.getProtocolName().isEmpty()) {
        protocolName_ = other.protocolName_;
        onChanged();
      }
      if (!other.attrs_.isEmpty()) {
        if (attrs_.isEmpty()) {
          attrs_ = other.attrs_;
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          ensureAttrsIsMutable();
          attrs_.addAll(other.attrs_);
        }
        onChanged();
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
      io.grpc.jchannelRpc.DumpStatsReq parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (io.grpc.jchannelRpc.DumpStatsReq) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private java.lang.Object jchannelAddress_ = "";
    /**
     * <code>string jchannel_address = 1;</code>
     * @return The jchannelAddress.
     */
    public java.lang.String getJchannelAddress() {
      java.lang.Object ref = jchannelAddress_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        jchannelAddress_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string jchannel_address = 1;</code>
     * @return The bytes for jchannelAddress.
     */
    public com.google.protobuf.ByteString
        getJchannelAddressBytes() {
      java.lang.Object ref = jchannelAddress_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        jchannelAddress_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string jchannel_address = 1;</code>
     * @param value The jchannelAddress to set.
     * @return This builder for chaining.
     */
    public Builder setJchannelAddress(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      jchannelAddress_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string jchannel_address = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearJchannelAddress() {
      
      jchannelAddress_ = getDefaultInstance().getJchannelAddress();
      onChanged();
      return this;
    }
    /**
     * <code>string jchannel_address = 1;</code>
     * @param value The bytes for jchannelAddress to set.
     * @return This builder for chaining.
     */
    public Builder setJchannelAddressBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      jchannelAddress_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object protocolName_ = "";
    /**
     * <code>string protocol_name = 2;</code>
     * @return The protocolName.
     */
    public java.lang.String getProtocolName() {
      java.lang.Object ref = protocolName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        protocolName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string protocol_name = 2;</code>
     * @return The bytes for protocolName.
     */
    public com.google.protobuf.ByteString
        getProtocolNameBytes() {
      java.lang.Object ref = protocolName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        protocolName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string protocol_name = 2;</code>
     * @param value The protocolName to set.
     * @return This builder for chaining.
     */
    public Builder setProtocolName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      protocolName_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string protocol_name = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearProtocolName() {
      
      protocolName_ = getDefaultInstance().getProtocolName();
      onChanged();
      return this;
    }
    /**
     * <code>string protocol_name = 2;</code>
     * @param value The bytes for protocolName to set.
     * @return This builder for chaining.
     */
    public Builder setProtocolNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      protocolName_ = value;
      onChanged();
      return this;
    }

    private com.google.protobuf.LazyStringList attrs_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    private void ensureAttrsIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        attrs_ = new com.google.protobuf.LazyStringArrayList(attrs_);
        bitField0_ |= 0x00000001;
       }
    }
    /**
     * <code>repeated string attrs = 3;</code>
     * @return A list containing the attrs.
     */
    public com.google.protobuf.ProtocolStringList
        getAttrsList() {
      return attrs_.getUnmodifiableView();
    }
    /**
     * <code>repeated string attrs = 3;</code>
     * @return The count of attrs.
     */
    public int getAttrsCount() {
      return attrs_.size();
    }
    /**
     * <code>repeated string attrs = 3;</code>
     * @param index The index of the element to return.
     * @return The attrs at the given index.
     */
    public java.lang.String getAttrs(int index) {
      return attrs_.get(index);
    }
    /**
     * <code>repeated string attrs = 3;</code>
     * @param index The index of the value to return.
     * @return The bytes of the attrs at the given index.
     */
    public com.google.protobuf.ByteString
        getAttrsBytes(int index) {
      return attrs_.getByteString(index);
    }
    /**
     * <code>repeated string attrs = 3;</code>
     * @param index The index to set the value at.
     * @param value The attrs to set.
     * @return This builder for chaining.
     */
    public Builder setAttrs(
        int index, java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureAttrsIsMutable();
      attrs_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string attrs = 3;</code>
     * @param value The attrs to add.
     * @return This builder for chaining.
     */
    public Builder addAttrs(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureAttrsIsMutable();
      attrs_.add(value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string attrs = 3;</code>
     * @param values The attrs to add.
     * @return This builder for chaining.
     */
    public Builder addAllAttrs(
        java.lang.Iterable<java.lang.String> values) {
      ensureAttrsIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, attrs_);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string attrs = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearAttrs() {
      attrs_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string attrs = 3;</code>
     * @param value The bytes of the attrs to add.
     * @return This builder for chaining.
     */
    public Builder addAttrsBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      ensureAttrsIsMutable();
      attrs_.add(value);
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


    // @@protoc_insertion_point(builder_scope:cn.yingming.grpc1.DumpStatsReq)
  }

  // @@protoc_insertion_point(class_scope:cn.yingming.grpc1.DumpStatsReq)
  private static final io.grpc.jchannelRpc.DumpStatsReq DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new io.grpc.jchannelRpc.DumpStatsReq();
  }

  public static io.grpc.jchannelRpc.DumpStatsReq getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<DumpStatsReq>
      PARSER = new com.google.protobuf.AbstractParser<DumpStatsReq>() {
    @java.lang.Override
    public DumpStatsReq parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new DumpStatsReq(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<DumpStatsReq> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<DumpStatsReq> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public io.grpc.jchannelRpc.DumpStatsReq getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

