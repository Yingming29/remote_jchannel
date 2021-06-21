// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: jchannel.proto

package io.grpc.jchannelRpc;

/**
 * <pre>
 * change
 * </pre>
 *
 * Protobuf type {@code cn.yingming.grpc1.StateRep}
 */
public final class StateRep extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:cn.yingming.grpc1.StateRep)
    StateRepOrBuilder {
private static final long serialVersionUID = 0L;
  // Use StateRep.newBuilder() to construct.
  private StateRep(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private StateRep() {
    oneOfHistory_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new StateRep();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private StateRep(
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
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              oneOfHistory_ = new java.util.ArrayList<io.grpc.jchannelRpc.MessageReq>();
              mutable_bitField0_ |= 0x00000001;
            }
            oneOfHistory_.add(
                input.readMessage(io.grpc.jchannelRpc.MessageReq.parser(), extensionRegistry));
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
        oneOfHistory_ = java.util.Collections.unmodifiableList(oneOfHistory_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return io.grpc.jchannelRpc.JChannelRpc.internal_static_cn_yingming_grpc1_StateRep_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.grpc.jchannelRpc.JChannelRpc.internal_static_cn_yingming_grpc1_StateRep_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.grpc.jchannelRpc.StateRep.class, io.grpc.jchannelRpc.StateRep.Builder.class);
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

  public static final int ONEOFHISTORY_FIELD_NUMBER = 2;
  private java.util.List<io.grpc.jchannelRpc.MessageReq> oneOfHistory_;
  /**
   * <code>repeated .cn.yingming.grpc1.MessageReq oneOfHistory = 2;</code>
   */
  @java.lang.Override
  public java.util.List<io.grpc.jchannelRpc.MessageReq> getOneOfHistoryList() {
    return oneOfHistory_;
  }
  /**
   * <code>repeated .cn.yingming.grpc1.MessageReq oneOfHistory = 2;</code>
   */
  @java.lang.Override
  public java.util.List<? extends io.grpc.jchannelRpc.MessageReqOrBuilder> 
      getOneOfHistoryOrBuilderList() {
    return oneOfHistory_;
  }
  /**
   * <code>repeated .cn.yingming.grpc1.MessageReq oneOfHistory = 2;</code>
   */
  @java.lang.Override
  public int getOneOfHistoryCount() {
    return oneOfHistory_.size();
  }
  /**
   * <code>repeated .cn.yingming.grpc1.MessageReq oneOfHistory = 2;</code>
   */
  @java.lang.Override
  public io.grpc.jchannelRpc.MessageReq getOneOfHistory(int index) {
    return oneOfHistory_.get(index);
  }
  /**
   * <code>repeated .cn.yingming.grpc1.MessageReq oneOfHistory = 2;</code>
   */
  @java.lang.Override
  public io.grpc.jchannelRpc.MessageReqOrBuilder getOneOfHistoryOrBuilder(
      int index) {
    return oneOfHistory_.get(index);
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
    for (int i = 0; i < oneOfHistory_.size(); i++) {
      output.writeMessage(2, oneOfHistory_.get(i));
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
    for (int i = 0; i < oneOfHistory_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, oneOfHistory_.get(i));
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
    if (!(obj instanceof io.grpc.jchannelRpc.StateRep)) {
      return super.equals(obj);
    }
    io.grpc.jchannelRpc.StateRep other = (io.grpc.jchannelRpc.StateRep) obj;

    if (getSize()
        != other.getSize()) return false;
    if (!getOneOfHistoryList()
        .equals(other.getOneOfHistoryList())) return false;
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
    if (getOneOfHistoryCount() > 0) {
      hash = (37 * hash) + ONEOFHISTORY_FIELD_NUMBER;
      hash = (53 * hash) + getOneOfHistoryList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static io.grpc.jchannelRpc.StateRep parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.grpc.jchannelRpc.StateRep parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.grpc.jchannelRpc.StateRep parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.grpc.jchannelRpc.StateRep parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.grpc.jchannelRpc.StateRep parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.grpc.jchannelRpc.StateRep parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.grpc.jchannelRpc.StateRep parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.grpc.jchannelRpc.StateRep parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.grpc.jchannelRpc.StateRep parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static io.grpc.jchannelRpc.StateRep parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.grpc.jchannelRpc.StateRep parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.grpc.jchannelRpc.StateRep parseFrom(
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
  public static Builder newBuilder(io.grpc.jchannelRpc.StateRep prototype) {
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
   * change
   * </pre>
   *
   * Protobuf type {@code cn.yingming.grpc1.StateRep}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:cn.yingming.grpc1.StateRep)
      io.grpc.jchannelRpc.StateRepOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.grpc.jchannelRpc.JChannelRpc.internal_static_cn_yingming_grpc1_StateRep_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.grpc.jchannelRpc.JChannelRpc.internal_static_cn_yingming_grpc1_StateRep_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.grpc.jchannelRpc.StateRep.class, io.grpc.jchannelRpc.StateRep.Builder.class);
    }

    // Construct using io.grpc.jchannelRpc.StateRep.newBuilder()
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
        getOneOfHistoryFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      size_ = 0;

      if (oneOfHistoryBuilder_ == null) {
        oneOfHistory_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        oneOfHistoryBuilder_.clear();
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return io.grpc.jchannelRpc.JChannelRpc.internal_static_cn_yingming_grpc1_StateRep_descriptor;
    }

    @java.lang.Override
    public io.grpc.jchannelRpc.StateRep getDefaultInstanceForType() {
      return io.grpc.jchannelRpc.StateRep.getDefaultInstance();
    }

    @java.lang.Override
    public io.grpc.jchannelRpc.StateRep build() {
      io.grpc.jchannelRpc.StateRep result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public io.grpc.jchannelRpc.StateRep buildPartial() {
      io.grpc.jchannelRpc.StateRep result = new io.grpc.jchannelRpc.StateRep(this);
      int from_bitField0_ = bitField0_;
      result.size_ = size_;
      if (oneOfHistoryBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          oneOfHistory_ = java.util.Collections.unmodifiableList(oneOfHistory_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.oneOfHistory_ = oneOfHistory_;
      } else {
        result.oneOfHistory_ = oneOfHistoryBuilder_.build();
      }
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
      if (other instanceof io.grpc.jchannelRpc.StateRep) {
        return mergeFrom((io.grpc.jchannelRpc.StateRep)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(io.grpc.jchannelRpc.StateRep other) {
      if (other == io.grpc.jchannelRpc.StateRep.getDefaultInstance()) return this;
      if (other.getSize() != 0) {
        setSize(other.getSize());
      }
      if (oneOfHistoryBuilder_ == null) {
        if (!other.oneOfHistory_.isEmpty()) {
          if (oneOfHistory_.isEmpty()) {
            oneOfHistory_ = other.oneOfHistory_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureOneOfHistoryIsMutable();
            oneOfHistory_.addAll(other.oneOfHistory_);
          }
          onChanged();
        }
      } else {
        if (!other.oneOfHistory_.isEmpty()) {
          if (oneOfHistoryBuilder_.isEmpty()) {
            oneOfHistoryBuilder_.dispose();
            oneOfHistoryBuilder_ = null;
            oneOfHistory_ = other.oneOfHistory_;
            bitField0_ = (bitField0_ & ~0x00000001);
            oneOfHistoryBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getOneOfHistoryFieldBuilder() : null;
          } else {
            oneOfHistoryBuilder_.addAllMessages(other.oneOfHistory_);
          }
        }
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
      io.grpc.jchannelRpc.StateRep parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (io.grpc.jchannelRpc.StateRep) e.getUnfinishedMessage();
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

    private java.util.List<io.grpc.jchannelRpc.MessageReq> oneOfHistory_ =
      java.util.Collections.emptyList();
    private void ensureOneOfHistoryIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        oneOfHistory_ = new java.util.ArrayList<io.grpc.jchannelRpc.MessageReq>(oneOfHistory_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        io.grpc.jchannelRpc.MessageReq, io.grpc.jchannelRpc.MessageReq.Builder, io.grpc.jchannelRpc.MessageReqOrBuilder> oneOfHistoryBuilder_;

    /**
     * <code>repeated .cn.yingming.grpc1.MessageReq oneOfHistory = 2;</code>
     */
    public java.util.List<io.grpc.jchannelRpc.MessageReq> getOneOfHistoryList() {
      if (oneOfHistoryBuilder_ == null) {
        return java.util.Collections.unmodifiableList(oneOfHistory_);
      } else {
        return oneOfHistoryBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .cn.yingming.grpc1.MessageReq oneOfHistory = 2;</code>
     */
    public int getOneOfHistoryCount() {
      if (oneOfHistoryBuilder_ == null) {
        return oneOfHistory_.size();
      } else {
        return oneOfHistoryBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .cn.yingming.grpc1.MessageReq oneOfHistory = 2;</code>
     */
    public io.grpc.jchannelRpc.MessageReq getOneOfHistory(int index) {
      if (oneOfHistoryBuilder_ == null) {
        return oneOfHistory_.get(index);
      } else {
        return oneOfHistoryBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .cn.yingming.grpc1.MessageReq oneOfHistory = 2;</code>
     */
    public Builder setOneOfHistory(
        int index, io.grpc.jchannelRpc.MessageReq value) {
      if (oneOfHistoryBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureOneOfHistoryIsMutable();
        oneOfHistory_.set(index, value);
        onChanged();
      } else {
        oneOfHistoryBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .cn.yingming.grpc1.MessageReq oneOfHistory = 2;</code>
     */
    public Builder setOneOfHistory(
        int index, io.grpc.jchannelRpc.MessageReq.Builder builderForValue) {
      if (oneOfHistoryBuilder_ == null) {
        ensureOneOfHistoryIsMutable();
        oneOfHistory_.set(index, builderForValue.build());
        onChanged();
      } else {
        oneOfHistoryBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .cn.yingming.grpc1.MessageReq oneOfHistory = 2;</code>
     */
    public Builder addOneOfHistory(io.grpc.jchannelRpc.MessageReq value) {
      if (oneOfHistoryBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureOneOfHistoryIsMutable();
        oneOfHistory_.add(value);
        onChanged();
      } else {
        oneOfHistoryBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .cn.yingming.grpc1.MessageReq oneOfHistory = 2;</code>
     */
    public Builder addOneOfHistory(
        int index, io.grpc.jchannelRpc.MessageReq value) {
      if (oneOfHistoryBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureOneOfHistoryIsMutable();
        oneOfHistory_.add(index, value);
        onChanged();
      } else {
        oneOfHistoryBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .cn.yingming.grpc1.MessageReq oneOfHistory = 2;</code>
     */
    public Builder addOneOfHistory(
        io.grpc.jchannelRpc.MessageReq.Builder builderForValue) {
      if (oneOfHistoryBuilder_ == null) {
        ensureOneOfHistoryIsMutable();
        oneOfHistory_.add(builderForValue.build());
        onChanged();
      } else {
        oneOfHistoryBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .cn.yingming.grpc1.MessageReq oneOfHistory = 2;</code>
     */
    public Builder addOneOfHistory(
        int index, io.grpc.jchannelRpc.MessageReq.Builder builderForValue) {
      if (oneOfHistoryBuilder_ == null) {
        ensureOneOfHistoryIsMutable();
        oneOfHistory_.add(index, builderForValue.build());
        onChanged();
      } else {
        oneOfHistoryBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .cn.yingming.grpc1.MessageReq oneOfHistory = 2;</code>
     */
    public Builder addAllOneOfHistory(
        java.lang.Iterable<? extends io.grpc.jchannelRpc.MessageReq> values) {
      if (oneOfHistoryBuilder_ == null) {
        ensureOneOfHistoryIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, oneOfHistory_);
        onChanged();
      } else {
        oneOfHistoryBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .cn.yingming.grpc1.MessageReq oneOfHistory = 2;</code>
     */
    public Builder clearOneOfHistory() {
      if (oneOfHistoryBuilder_ == null) {
        oneOfHistory_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        oneOfHistoryBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .cn.yingming.grpc1.MessageReq oneOfHistory = 2;</code>
     */
    public Builder removeOneOfHistory(int index) {
      if (oneOfHistoryBuilder_ == null) {
        ensureOneOfHistoryIsMutable();
        oneOfHistory_.remove(index);
        onChanged();
      } else {
        oneOfHistoryBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .cn.yingming.grpc1.MessageReq oneOfHistory = 2;</code>
     */
    public io.grpc.jchannelRpc.MessageReq.Builder getOneOfHistoryBuilder(
        int index) {
      return getOneOfHistoryFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .cn.yingming.grpc1.MessageReq oneOfHistory = 2;</code>
     */
    public io.grpc.jchannelRpc.MessageReqOrBuilder getOneOfHistoryOrBuilder(
        int index) {
      if (oneOfHistoryBuilder_ == null) {
        return oneOfHistory_.get(index);  } else {
        return oneOfHistoryBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .cn.yingming.grpc1.MessageReq oneOfHistory = 2;</code>
     */
    public java.util.List<? extends io.grpc.jchannelRpc.MessageReqOrBuilder> 
         getOneOfHistoryOrBuilderList() {
      if (oneOfHistoryBuilder_ != null) {
        return oneOfHistoryBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(oneOfHistory_);
      }
    }
    /**
     * <code>repeated .cn.yingming.grpc1.MessageReq oneOfHistory = 2;</code>
     */
    public io.grpc.jchannelRpc.MessageReq.Builder addOneOfHistoryBuilder() {
      return getOneOfHistoryFieldBuilder().addBuilder(
          io.grpc.jchannelRpc.MessageReq.getDefaultInstance());
    }
    /**
     * <code>repeated .cn.yingming.grpc1.MessageReq oneOfHistory = 2;</code>
     */
    public io.grpc.jchannelRpc.MessageReq.Builder addOneOfHistoryBuilder(
        int index) {
      return getOneOfHistoryFieldBuilder().addBuilder(
          index, io.grpc.jchannelRpc.MessageReq.getDefaultInstance());
    }
    /**
     * <code>repeated .cn.yingming.grpc1.MessageReq oneOfHistory = 2;</code>
     */
    public java.util.List<io.grpc.jchannelRpc.MessageReq.Builder> 
         getOneOfHistoryBuilderList() {
      return getOneOfHistoryFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        io.grpc.jchannelRpc.MessageReq, io.grpc.jchannelRpc.MessageReq.Builder, io.grpc.jchannelRpc.MessageReqOrBuilder> 
        getOneOfHistoryFieldBuilder() {
      if (oneOfHistoryBuilder_ == null) {
        oneOfHistoryBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            io.grpc.jchannelRpc.MessageReq, io.grpc.jchannelRpc.MessageReq.Builder, io.grpc.jchannelRpc.MessageReqOrBuilder>(
                oneOfHistory_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        oneOfHistory_ = null;
      }
      return oneOfHistoryBuilder_;
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


    // @@protoc_insertion_point(builder_scope:cn.yingming.grpc1.StateRep)
  }

  // @@protoc_insertion_point(class_scope:cn.yingming.grpc1.StateRep)
  private static final io.grpc.jchannelRpc.StateRep DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new io.grpc.jchannelRpc.StateRep();
  }

  public static io.grpc.jchannelRpc.StateRep getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<StateRep>
      PARSER = new com.google.protobuf.AbstractParser<StateRep>() {
    @java.lang.Override
    public StateRep parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new StateRep(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<StateRep> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<StateRep> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public io.grpc.jchannelRpc.StateRep getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

