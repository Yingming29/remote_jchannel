// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: jchannel.proto

package io.grpc.jchannelRpc;

/**
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
    jchannelAddress_ = com.google.protobuf.ByteString.EMPTY;
    state_ = com.google.protobuf.ByteString.EMPTY;
    target_ = com.google.protobuf.ByteString.EMPTY;
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

            jchannelAddress_ = input.readBytes();
            break;
          }
          case 18: {

            state_ = input.readBytes();
            break;
          }
          case 26: {

            target_ = input.readBytes();
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
    return io.grpc.jchannelRpc.JChannelRpc.internal_static_cn_yingming_grpc1_StateRep_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.grpc.jchannelRpc.JChannelRpc.internal_static_cn_yingming_grpc1_StateRep_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.grpc.jchannelRpc.StateRep.class, io.grpc.jchannelRpc.StateRep.Builder.class);
  }

  public static final int JCHANNEL_ADDRESS_FIELD_NUMBER = 1;
  private com.google.protobuf.ByteString jchannelAddress_;
  /**
   * <code>bytes jchannel_address = 1;</code>
   * @return The jchannelAddress.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString getJchannelAddress() {
    return jchannelAddress_;
  }

  public static final int STATE_FIELD_NUMBER = 2;
  private com.google.protobuf.ByteString state_;
  /**
   * <code>bytes state = 2;</code>
   * @return The state.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString getState() {
    return state_;
  }

  public static final int TARGET_FIELD_NUMBER = 3;
  private com.google.protobuf.ByteString target_;
  /**
   * <code>bytes target = 3;</code>
   * @return The target.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString getTarget() {
    return target_;
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
    if (!jchannelAddress_.isEmpty()) {
      output.writeBytes(1, jchannelAddress_);
    }
    if (!state_.isEmpty()) {
      output.writeBytes(2, state_);
    }
    if (!target_.isEmpty()) {
      output.writeBytes(3, target_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!jchannelAddress_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(1, jchannelAddress_);
    }
    if (!state_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(2, state_);
    }
    if (!target_.isEmpty()) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(3, target_);
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

    if (!getJchannelAddress()
        .equals(other.getJchannelAddress())) return false;
    if (!getState()
        .equals(other.getState())) return false;
    if (!getTarget()
        .equals(other.getTarget())) return false;
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
    hash = (37 * hash) + STATE_FIELD_NUMBER;
    hash = (53 * hash) + getState().hashCode();
    hash = (37 * hash) + TARGET_FIELD_NUMBER;
    hash = (53 * hash) + getTarget().hashCode();
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
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      jchannelAddress_ = com.google.protobuf.ByteString.EMPTY;

      state_ = com.google.protobuf.ByteString.EMPTY;

      target_ = com.google.protobuf.ByteString.EMPTY;

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
      result.jchannelAddress_ = jchannelAddress_;
      result.state_ = state_;
      result.target_ = target_;
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
      if (other.getJchannelAddress() != com.google.protobuf.ByteString.EMPTY) {
        setJchannelAddress(other.getJchannelAddress());
      }
      if (other.getState() != com.google.protobuf.ByteString.EMPTY) {
        setState(other.getState());
      }
      if (other.getTarget() != com.google.protobuf.ByteString.EMPTY) {
        setTarget(other.getTarget());
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

    private com.google.protobuf.ByteString jchannelAddress_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes jchannel_address = 1;</code>
     * @return The jchannelAddress.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getJchannelAddress() {
      return jchannelAddress_;
    }
    /**
     * <code>bytes jchannel_address = 1;</code>
     * @param value The jchannelAddress to set.
     * @return This builder for chaining.
     */
    public Builder setJchannelAddress(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      jchannelAddress_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bytes jchannel_address = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearJchannelAddress() {
      
      jchannelAddress_ = getDefaultInstance().getJchannelAddress();
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString state_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes state = 2;</code>
     * @return The state.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getState() {
      return state_;
    }
    /**
     * <code>bytes state = 2;</code>
     * @param value The state to set.
     * @return This builder for chaining.
     */
    public Builder setState(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      state_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bytes state = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearState() {
      
      state_ = getDefaultInstance().getState();
      onChanged();
      return this;
    }

    private com.google.protobuf.ByteString target_ = com.google.protobuf.ByteString.EMPTY;
    /**
     * <code>bytes target = 3;</code>
     * @return The target.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString getTarget() {
      return target_;
    }
    /**
     * <code>bytes target = 3;</code>
     * @param value The target to set.
     * @return This builder for chaining.
     */
    public Builder setTarget(com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      target_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bytes target = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearTarget() {
      
      target_ = getDefaultInstance().getTarget();
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

