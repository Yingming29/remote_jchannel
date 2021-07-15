// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: jchannel.proto

package io.grpc.jchannelRpc;

/**
 * Protobuf type {@code cn.yingming.grpc1.MessageReqPy}
 */
public final class MessageReqPy extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:cn.yingming.grpc1.MessageReqPy)
    MessageReqPyOrBuilder {
private static final long serialVersionUID = 0L;
  // Use MessageReqPy.newBuilder() to construct.
  private MessageReqPy(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private MessageReqPy() {
    source_ = "";
    dest_ = "";
    contentStr_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new MessageReqPy();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private MessageReqPy(
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
          case 10: {
            java.lang.String s = input.readStringRequireUtf8();

            source_ = s;
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            dest_ = s;
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            contentStr_ = s;
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
    return io.grpc.jchannelRpc.JChannelRpc.internal_static_cn_yingming_grpc1_MessageReqPy_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.grpc.jchannelRpc.JChannelRpc.internal_static_cn_yingming_grpc1_MessageReqPy_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.grpc.jchannelRpc.MessageReqPy.class, io.grpc.jchannelRpc.MessageReqPy.Builder.class);
  }

  public static final int SOURCE_FIELD_NUMBER = 1;
  private volatile java.lang.Object source_;
  /**
   * <code>string source = 1;</code>
   * @return The source.
   */
  @java.lang.Override
  public java.lang.String getSource() {
    java.lang.Object ref = source_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      source_ = s;
      return s;
    }
  }
  /**
   * <code>string source = 1;</code>
   * @return The bytes for source.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getSourceBytes() {
    java.lang.Object ref = source_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      source_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int DEST_FIELD_NUMBER = 2;
  private volatile java.lang.Object dest_;
  /**
   * <code>string dest = 2;</code>
   * @return The dest.
   */
  @java.lang.Override
  public java.lang.String getDest() {
    java.lang.Object ref = dest_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      dest_ = s;
      return s;
    }
  }
  /**
   * <code>string dest = 2;</code>
   * @return The bytes for dest.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getDestBytes() {
    java.lang.Object ref = dest_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      dest_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int CONTENTSTR_FIELD_NUMBER = 3;
  private volatile java.lang.Object contentStr_;
  /**
   * <code>string contentStr = 3;</code>
   * @return The contentStr.
   */
  @java.lang.Override
  public java.lang.String getContentStr() {
    java.lang.Object ref = contentStr_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      contentStr_ = s;
      return s;
    }
  }
  /**
   * <code>string contentStr = 3;</code>
   * @return The bytes for contentStr.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getContentStrBytes() {
    java.lang.Object ref = contentStr_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      contentStr_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
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
    if (!getSourceBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, source_);
    }
    if (!getDestBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, dest_);
    }
    if (!getContentStrBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, contentStr_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!getSourceBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, source_);
    }
    if (!getDestBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, dest_);
    }
    if (!getContentStrBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, contentStr_);
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
    if (!(obj instanceof io.grpc.jchannelRpc.MessageReqPy)) {
      return super.equals(obj);
    }
    io.grpc.jchannelRpc.MessageReqPy other = (io.grpc.jchannelRpc.MessageReqPy) obj;

    if (!getSource()
        .equals(other.getSource())) return false;
    if (!getDest()
        .equals(other.getDest())) return false;
    if (!getContentStr()
        .equals(other.getContentStr())) return false;
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
    hash = (37 * hash) + SOURCE_FIELD_NUMBER;
    hash = (53 * hash) + getSource().hashCode();
    hash = (37 * hash) + DEST_FIELD_NUMBER;
    hash = (53 * hash) + getDest().hashCode();
    hash = (37 * hash) + CONTENTSTR_FIELD_NUMBER;
    hash = (53 * hash) + getContentStr().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static io.grpc.jchannelRpc.MessageReqPy parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.grpc.jchannelRpc.MessageReqPy parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.grpc.jchannelRpc.MessageReqPy parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.grpc.jchannelRpc.MessageReqPy parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.grpc.jchannelRpc.MessageReqPy parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.grpc.jchannelRpc.MessageReqPy parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.grpc.jchannelRpc.MessageReqPy parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.grpc.jchannelRpc.MessageReqPy parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.grpc.jchannelRpc.MessageReqPy parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static io.grpc.jchannelRpc.MessageReqPy parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.grpc.jchannelRpc.MessageReqPy parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.grpc.jchannelRpc.MessageReqPy parseFrom(
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
  public static Builder newBuilder(io.grpc.jchannelRpc.MessageReqPy prototype) {
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
   * Protobuf type {@code cn.yingming.grpc1.MessageReqPy}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:cn.yingming.grpc1.MessageReqPy)
      io.grpc.jchannelRpc.MessageReqPyOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.grpc.jchannelRpc.JChannelRpc.internal_static_cn_yingming_grpc1_MessageReqPy_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.grpc.jchannelRpc.JChannelRpc.internal_static_cn_yingming_grpc1_MessageReqPy_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.grpc.jchannelRpc.MessageReqPy.class, io.grpc.jchannelRpc.MessageReqPy.Builder.class);
    }

    // Construct using io.grpc.jchannelRpc.MessageReqPy.newBuilder()
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
      source_ = "";

      dest_ = "";

      contentStr_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return io.grpc.jchannelRpc.JChannelRpc.internal_static_cn_yingming_grpc1_MessageReqPy_descriptor;
    }

    @java.lang.Override
    public io.grpc.jchannelRpc.MessageReqPy getDefaultInstanceForType() {
      return io.grpc.jchannelRpc.MessageReqPy.getDefaultInstance();
    }

    @java.lang.Override
    public io.grpc.jchannelRpc.MessageReqPy build() {
      io.grpc.jchannelRpc.MessageReqPy result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public io.grpc.jchannelRpc.MessageReqPy buildPartial() {
      io.grpc.jchannelRpc.MessageReqPy result = new io.grpc.jchannelRpc.MessageReqPy(this);
      result.source_ = source_;
      result.dest_ = dest_;
      result.contentStr_ = contentStr_;
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
      if (other instanceof io.grpc.jchannelRpc.MessageReqPy) {
        return mergeFrom((io.grpc.jchannelRpc.MessageReqPy)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(io.grpc.jchannelRpc.MessageReqPy other) {
      if (other == io.grpc.jchannelRpc.MessageReqPy.getDefaultInstance()) return this;
      if (!other.getSource().isEmpty()) {
        source_ = other.source_;
        onChanged();
      }
      if (!other.getDest().isEmpty()) {
        dest_ = other.dest_;
        onChanged();
      }
      if (!other.getContentStr().isEmpty()) {
        contentStr_ = other.contentStr_;
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
      io.grpc.jchannelRpc.MessageReqPy parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (io.grpc.jchannelRpc.MessageReqPy) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private java.lang.Object source_ = "";
    /**
     * <code>string source = 1;</code>
     * @return The source.
     */
    public java.lang.String getSource() {
      java.lang.Object ref = source_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        source_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string source = 1;</code>
     * @return The bytes for source.
     */
    public com.google.protobuf.ByteString
        getSourceBytes() {
      java.lang.Object ref = source_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        source_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string source = 1;</code>
     * @param value The source to set.
     * @return This builder for chaining.
     */
    public Builder setSource(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      source_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string source = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearSource() {
      
      source_ = getDefaultInstance().getSource();
      onChanged();
      return this;
    }
    /**
     * <code>string source = 1;</code>
     * @param value The bytes for source to set.
     * @return This builder for chaining.
     */
    public Builder setSourceBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      source_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object dest_ = "";
    /**
     * <code>string dest = 2;</code>
     * @return The dest.
     */
    public java.lang.String getDest() {
      java.lang.Object ref = dest_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        dest_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string dest = 2;</code>
     * @return The bytes for dest.
     */
    public com.google.protobuf.ByteString
        getDestBytes() {
      java.lang.Object ref = dest_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        dest_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string dest = 2;</code>
     * @param value The dest to set.
     * @return This builder for chaining.
     */
    public Builder setDest(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      dest_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string dest = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearDest() {
      
      dest_ = getDefaultInstance().getDest();
      onChanged();
      return this;
    }
    /**
     * <code>string dest = 2;</code>
     * @param value The bytes for dest to set.
     * @return This builder for chaining.
     */
    public Builder setDestBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      dest_ = value;
      onChanged();
      return this;
    }

    private java.lang.Object contentStr_ = "";
    /**
     * <code>string contentStr = 3;</code>
     * @return The contentStr.
     */
    public java.lang.String getContentStr() {
      java.lang.Object ref = contentStr_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        contentStr_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string contentStr = 3;</code>
     * @return The bytes for contentStr.
     */
    public com.google.protobuf.ByteString
        getContentStrBytes() {
      java.lang.Object ref = contentStr_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        contentStr_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string contentStr = 3;</code>
     * @param value The contentStr to set.
     * @return This builder for chaining.
     */
    public Builder setContentStr(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      contentStr_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string contentStr = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearContentStr() {
      
      contentStr_ = getDefaultInstance().getContentStr();
      onChanged();
      return this;
    }
    /**
     * <code>string contentStr = 3;</code>
     * @param value The bytes for contentStr to set.
     * @return This builder for chaining.
     */
    public Builder setContentStrBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      contentStr_ = value;
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


    // @@protoc_insertion_point(builder_scope:cn.yingming.grpc1.MessageReqPy)
  }

  // @@protoc_insertion_point(class_scope:cn.yingming.grpc1.MessageReqPy)
  private static final io.grpc.jchannelRpc.MessageReqPy DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new io.grpc.jchannelRpc.MessageReqPy();
  }

  public static io.grpc.jchannelRpc.MessageReqPy getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<MessageReqPy>
      PARSER = new com.google.protobuf.AbstractParser<MessageReqPy>() {
    @java.lang.Override
    public MessageReqPy parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new MessageReqPy(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<MessageReqPy> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<MessageReqPy> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public io.grpc.jchannelRpc.MessageReqPy getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
