// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: jchannel.proto

package io.grpc.jchannelRpc;

/**
 * Protobuf type {@code cn.yingming.grpc1.GetDiscardOwnMsgRep}
 */
public final class GetDiscardOwnMsgRep extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:cn.yingming.grpc1.GetDiscardOwnMsgRep)
    GetDiscardOwnMsgRepOrBuilder {
private static final long serialVersionUID = 0L;
  // Use GetDiscardOwnMsgRep.newBuilder() to construct.
  private GetDiscardOwnMsgRep(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private GetDiscardOwnMsgRep() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new GetDiscardOwnMsgRep();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private GetDiscardOwnMsgRep(
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

            discard_ = input.readBool();
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
    return io.grpc.jchannelRpc.JChannelRpc.internal_static_cn_yingming_grpc1_GetDiscardOwnMsgRep_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.grpc.jchannelRpc.JChannelRpc.internal_static_cn_yingming_grpc1_GetDiscardOwnMsgRep_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.grpc.jchannelRpc.GetDiscardOwnMsgRep.class, io.grpc.jchannelRpc.GetDiscardOwnMsgRep.Builder.class);
  }

  public static final int DISCARD_FIELD_NUMBER = 1;
  private boolean discard_;
  /**
   * <code>bool discard = 1;</code>
   * @return The discard.
   */
  @java.lang.Override
  public boolean getDiscard() {
    return discard_;
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
    if (discard_ != false) {
      output.writeBool(1, discard_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (discard_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(1, discard_);
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
    if (!(obj instanceof io.grpc.jchannelRpc.GetDiscardOwnMsgRep)) {
      return super.equals(obj);
    }
    io.grpc.jchannelRpc.GetDiscardOwnMsgRep other = (io.grpc.jchannelRpc.GetDiscardOwnMsgRep) obj;

    if (getDiscard()
        != other.getDiscard()) return false;
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
    hash = (37 * hash) + DISCARD_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getDiscard());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static io.grpc.jchannelRpc.GetDiscardOwnMsgRep parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.grpc.jchannelRpc.GetDiscardOwnMsgRep parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.grpc.jchannelRpc.GetDiscardOwnMsgRep parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.grpc.jchannelRpc.GetDiscardOwnMsgRep parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.grpc.jchannelRpc.GetDiscardOwnMsgRep parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.grpc.jchannelRpc.GetDiscardOwnMsgRep parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.grpc.jchannelRpc.GetDiscardOwnMsgRep parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.grpc.jchannelRpc.GetDiscardOwnMsgRep parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.grpc.jchannelRpc.GetDiscardOwnMsgRep parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static io.grpc.jchannelRpc.GetDiscardOwnMsgRep parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.grpc.jchannelRpc.GetDiscardOwnMsgRep parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.grpc.jchannelRpc.GetDiscardOwnMsgRep parseFrom(
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
  public static Builder newBuilder(io.grpc.jchannelRpc.GetDiscardOwnMsgRep prototype) {
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
   * Protobuf type {@code cn.yingming.grpc1.GetDiscardOwnMsgRep}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:cn.yingming.grpc1.GetDiscardOwnMsgRep)
      io.grpc.jchannelRpc.GetDiscardOwnMsgRepOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.grpc.jchannelRpc.JChannelRpc.internal_static_cn_yingming_grpc1_GetDiscardOwnMsgRep_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.grpc.jchannelRpc.JChannelRpc.internal_static_cn_yingming_grpc1_GetDiscardOwnMsgRep_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.grpc.jchannelRpc.GetDiscardOwnMsgRep.class, io.grpc.jchannelRpc.GetDiscardOwnMsgRep.Builder.class);
    }

    // Construct using io.grpc.jchannelRpc.GetDiscardOwnMsgRep.newBuilder()
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
      discard_ = false;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return io.grpc.jchannelRpc.JChannelRpc.internal_static_cn_yingming_grpc1_GetDiscardOwnMsgRep_descriptor;
    }

    @java.lang.Override
    public io.grpc.jchannelRpc.GetDiscardOwnMsgRep getDefaultInstanceForType() {
      return io.grpc.jchannelRpc.GetDiscardOwnMsgRep.getDefaultInstance();
    }

    @java.lang.Override
    public io.grpc.jchannelRpc.GetDiscardOwnMsgRep build() {
      io.grpc.jchannelRpc.GetDiscardOwnMsgRep result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public io.grpc.jchannelRpc.GetDiscardOwnMsgRep buildPartial() {
      io.grpc.jchannelRpc.GetDiscardOwnMsgRep result = new io.grpc.jchannelRpc.GetDiscardOwnMsgRep(this);
      result.discard_ = discard_;
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
      if (other instanceof io.grpc.jchannelRpc.GetDiscardOwnMsgRep) {
        return mergeFrom((io.grpc.jchannelRpc.GetDiscardOwnMsgRep)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(io.grpc.jchannelRpc.GetDiscardOwnMsgRep other) {
      if (other == io.grpc.jchannelRpc.GetDiscardOwnMsgRep.getDefaultInstance()) return this;
      if (other.getDiscard() != false) {
        setDiscard(other.getDiscard());
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
      io.grpc.jchannelRpc.GetDiscardOwnMsgRep parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (io.grpc.jchannelRpc.GetDiscardOwnMsgRep) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private boolean discard_ ;
    /**
     * <code>bool discard = 1;</code>
     * @return The discard.
     */
    @java.lang.Override
    public boolean getDiscard() {
      return discard_;
    }
    /**
     * <code>bool discard = 1;</code>
     * @param value The discard to set.
     * @return This builder for chaining.
     */
    public Builder setDiscard(boolean value) {
      
      discard_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bool discard = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearDiscard() {
      
      discard_ = false;
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


    // @@protoc_insertion_point(builder_scope:cn.yingming.grpc1.GetDiscardOwnMsgRep)
  }

  // @@protoc_insertion_point(class_scope:cn.yingming.grpc1.GetDiscardOwnMsgRep)
  private static final io.grpc.jchannelRpc.GetDiscardOwnMsgRep DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new io.grpc.jchannelRpc.GetDiscardOwnMsgRep();
  }

  public static io.grpc.jchannelRpc.GetDiscardOwnMsgRep getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<GetDiscardOwnMsgRep>
      PARSER = new com.google.protobuf.AbstractParser<GetDiscardOwnMsgRep>() {
    @java.lang.Override
    public GetDiscardOwnMsgRep parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new GetDiscardOwnMsgRep(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<GetDiscardOwnMsgRep> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<GetDiscardOwnMsgRep> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public io.grpc.jchannelRpc.GetDiscardOwnMsgRep getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

