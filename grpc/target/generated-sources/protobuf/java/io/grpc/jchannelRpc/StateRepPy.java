// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: jchannel.proto

package io.grpc.jchannelRpc;

/**
 * Protobuf type {@code cn.yingming.grpc1.StateRepPy}
 */
public final class StateRepPy extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:cn.yingming.grpc1.StateRepPy)
    StateRepPyOrBuilder {
private static final long serialVersionUID = 0L;
  // Use StateRepPy.newBuilder() to construct.
  private StateRepPy(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private StateRepPy() {
    line_ = com.google.protobuf.LazyStringArrayList.EMPTY;
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new StateRepPy();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private StateRepPy(
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
          case 8: {

            size_ = input.readInt32();
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              line_ = new com.google.protobuf.LazyStringArrayList();
              mutable_bitField0_ |= 0x00000001;
            }
            line_.add(s);
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
        line_ = line_.getUnmodifiableView();
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return io.grpc.jchannelRpc.JChannelRpc.internal_static_cn_yingming_grpc1_StateRepPy_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.grpc.jchannelRpc.JChannelRpc.internal_static_cn_yingming_grpc1_StateRepPy_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.grpc.jchannelRpc.StateRepPy.class, io.grpc.jchannelRpc.StateRepPy.Builder.class);
  }

  public static final int SIZE_FIELD_NUMBER = 1;
  private int size_;
  /**
   * <code>int32 size = 1;</code>
   * @return The size.
   */
  @java.lang.Override
  public int getSize() {
    return size_;
  }

  public static final int LINE_FIELD_NUMBER = 2;
  private com.google.protobuf.LazyStringList line_;
  /**
   * <code>repeated string line = 2;</code>
   * @return A list containing the line.
   */
  public com.google.protobuf.ProtocolStringList
      getLineList() {
    return line_;
  }
  /**
   * <code>repeated string line = 2;</code>
   * @return The count of line.
   */
  public int getLineCount() {
    return line_.size();
  }
  /**
   * <code>repeated string line = 2;</code>
   * @param index The index of the element to return.
   * @return The line at the given index.
   */
  public java.lang.String getLine(int index) {
    return line_.get(index);
  }
  /**
   * <code>repeated string line = 2;</code>
   * @param index The index of the value to return.
   * @return The bytes of the line at the given index.
   */
  public com.google.protobuf.ByteString
      getLineBytes(int index) {
    return line_.getByteString(index);
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
    if (size_ != 0) {
      output.writeInt32(1, size_);
    }
    for (int i = 0; i < line_.size(); i++) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, line_.getRaw(i));
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (size_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, size_);
    }
    {
      int dataSize = 0;
      for (int i = 0; i < line_.size(); i++) {
        dataSize += computeStringSizeNoTag(line_.getRaw(i));
      }
      size += dataSize;
      size += 1 * getLineList().size();
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
    if (!(obj instanceof io.grpc.jchannelRpc.StateRepPy)) {
      return super.equals(obj);
    }
    io.grpc.jchannelRpc.StateRepPy other = (io.grpc.jchannelRpc.StateRepPy) obj;

    if (getSize()
        != other.getSize()) return false;
    if (!getLineList()
        .equals(other.getLineList())) return false;
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
    hash = (37 * hash) + SIZE_FIELD_NUMBER;
    hash = (53 * hash) + getSize();
    if (getLineCount() > 0) {
      hash = (37 * hash) + LINE_FIELD_NUMBER;
      hash = (53 * hash) + getLineList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static io.grpc.jchannelRpc.StateRepPy parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.grpc.jchannelRpc.StateRepPy parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.grpc.jchannelRpc.StateRepPy parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.grpc.jchannelRpc.StateRepPy parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.grpc.jchannelRpc.StateRepPy parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.grpc.jchannelRpc.StateRepPy parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.grpc.jchannelRpc.StateRepPy parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.grpc.jchannelRpc.StateRepPy parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.grpc.jchannelRpc.StateRepPy parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static io.grpc.jchannelRpc.StateRepPy parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.grpc.jchannelRpc.StateRepPy parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.grpc.jchannelRpc.StateRepPy parseFrom(
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
  public static Builder newBuilder(io.grpc.jchannelRpc.StateRepPy prototype) {
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
   * Protobuf type {@code cn.yingming.grpc1.StateRepPy}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:cn.yingming.grpc1.StateRepPy)
      io.grpc.jchannelRpc.StateRepPyOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.grpc.jchannelRpc.JChannelRpc.internal_static_cn_yingming_grpc1_StateRepPy_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.grpc.jchannelRpc.JChannelRpc.internal_static_cn_yingming_grpc1_StateRepPy_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.grpc.jchannelRpc.StateRepPy.class, io.grpc.jchannelRpc.StateRepPy.Builder.class);
    }

    // Construct using io.grpc.jchannelRpc.StateRepPy.newBuilder()
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
      size_ = 0;

      line_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return io.grpc.jchannelRpc.JChannelRpc.internal_static_cn_yingming_grpc1_StateRepPy_descriptor;
    }

    @java.lang.Override
    public io.grpc.jchannelRpc.StateRepPy getDefaultInstanceForType() {
      return io.grpc.jchannelRpc.StateRepPy.getDefaultInstance();
    }

    @java.lang.Override
    public io.grpc.jchannelRpc.StateRepPy build() {
      io.grpc.jchannelRpc.StateRepPy result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public io.grpc.jchannelRpc.StateRepPy buildPartial() {
      io.grpc.jchannelRpc.StateRepPy result = new io.grpc.jchannelRpc.StateRepPy(this);
      int from_bitField0_ = bitField0_;
      result.size_ = size_;
      if (((bitField0_ & 0x00000001) != 0)) {
        line_ = line_.getUnmodifiableView();
        bitField0_ = (bitField0_ & ~0x00000001);
      }
      result.line_ = line_;
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
      if (other instanceof io.grpc.jchannelRpc.StateRepPy) {
        return mergeFrom((io.grpc.jchannelRpc.StateRepPy)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(io.grpc.jchannelRpc.StateRepPy other) {
      if (other == io.grpc.jchannelRpc.StateRepPy.getDefaultInstance()) return this;
      if (other.getSize() != 0) {
        setSize(other.getSize());
      }
      if (!other.line_.isEmpty()) {
        if (line_.isEmpty()) {
          line_ = other.line_;
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          ensureLineIsMutable();
          line_.addAll(other.line_);
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
      io.grpc.jchannelRpc.StateRepPy parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (io.grpc.jchannelRpc.StateRepPy) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int size_ ;
    /**
     * <code>int32 size = 1;</code>
     * @return The size.
     */
    @java.lang.Override
    public int getSize() {
      return size_;
    }
    /**
     * <code>int32 size = 1;</code>
     * @param value The size to set.
     * @return This builder for chaining.
     */
    public Builder setSize(int value) {
      
      size_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 size = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearSize() {
      
      size_ = 0;
      onChanged();
      return this;
    }

    private com.google.protobuf.LazyStringList line_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    private void ensureLineIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        line_ = new com.google.protobuf.LazyStringArrayList(line_);
        bitField0_ |= 0x00000001;
       }
    }
    /**
     * <code>repeated string line = 2;</code>
     * @return A list containing the line.
     */
    public com.google.protobuf.ProtocolStringList
        getLineList() {
      return line_.getUnmodifiableView();
    }
    /**
     * <code>repeated string line = 2;</code>
     * @return The count of line.
     */
    public int getLineCount() {
      return line_.size();
    }
    /**
     * <code>repeated string line = 2;</code>
     * @param index The index of the element to return.
     * @return The line at the given index.
     */
    public java.lang.String getLine(int index) {
      return line_.get(index);
    }
    /**
     * <code>repeated string line = 2;</code>
     * @param index The index of the value to return.
     * @return The bytes of the line at the given index.
     */
    public com.google.protobuf.ByteString
        getLineBytes(int index) {
      return line_.getByteString(index);
    }
    /**
     * <code>repeated string line = 2;</code>
     * @param index The index to set the value at.
     * @param value The line to set.
     * @return This builder for chaining.
     */
    public Builder setLine(
        int index, java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureLineIsMutable();
      line_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string line = 2;</code>
     * @param value The line to add.
     * @return This builder for chaining.
     */
    public Builder addLine(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureLineIsMutable();
      line_.add(value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string line = 2;</code>
     * @param values The line to add.
     * @return This builder for chaining.
     */
    public Builder addAllLine(
        java.lang.Iterable<java.lang.String> values) {
      ensureLineIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, line_);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string line = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearLine() {
      line_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string line = 2;</code>
     * @param value The bytes of the line to add.
     * @return This builder for chaining.
     */
    public Builder addLineBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      ensureLineIsMutable();
      line_.add(value);
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


    // @@protoc_insertion_point(builder_scope:cn.yingming.grpc1.StateRepPy)
  }

  // @@protoc_insertion_point(class_scope:cn.yingming.grpc1.StateRepPy)
  private static final io.grpc.jchannelRpc.StateRepPy DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new io.grpc.jchannelRpc.StateRepPy();
  }

  public static io.grpc.jchannelRpc.StateRepPy getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<StateRepPy>
      PARSER = new com.google.protobuf.AbstractParser<StateRepPy>() {
    @java.lang.Override
    public StateRepPy parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new StateRepPy(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<StateRepPy> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<StateRepPy> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public io.grpc.jchannelRpc.StateRepPy getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

