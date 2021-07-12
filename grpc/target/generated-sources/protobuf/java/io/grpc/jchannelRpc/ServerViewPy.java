// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: jchannel.proto

package io.grpc.jchannelRpc;

/**
 * Protobuf type {@code cn.yingming.grpc1.ServerViewPy}
 */
public final class ServerViewPy extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:cn.yingming.grpc1.ServerViewPy)
    ServerViewPyOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ServerViewPy.newBuilder() to construct.
  private ServerViewPy(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ServerViewPy() {
    members_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    coordinator_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ServerViewPy();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ServerViewPy(
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

            num_ = input.readInt32();
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              members_ = new com.google.protobuf.LazyStringArrayList();
              mutable_bitField0_ |= 0x00000001;
            }
            members_.add(s);
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            coordinator_ = s;
            break;
          }
          case 32: {

            size_ = input.readInt32();
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
        members_ = members_.getUnmodifiableView();
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return io.grpc.jchannelRpc.JChannelRpc.internal_static_cn_yingming_grpc1_ServerViewPy_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.grpc.jchannelRpc.JChannelRpc.internal_static_cn_yingming_grpc1_ServerViewPy_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.grpc.jchannelRpc.ServerViewPy.class, io.grpc.jchannelRpc.ServerViewPy.Builder.class);
  }

  public static final int NUM_FIELD_NUMBER = 1;
  private int num_;
  /**
   * <code>int32 num = 1;</code>
   * @return The num.
   */
  @java.lang.Override
  public int getNum() {
    return num_;
  }

  public static final int MEMBERS_FIELD_NUMBER = 2;
  private com.google.protobuf.LazyStringList members_;
  /**
   * <code>repeated string members = 2;</code>
   * @return A list containing the members.
   */
  public com.google.protobuf.ProtocolStringList
      getMembersList() {
    return members_;
  }
  /**
   * <code>repeated string members = 2;</code>
   * @return The count of members.
   */
  public int getMembersCount() {
    return members_.size();
  }
  /**
   * <code>repeated string members = 2;</code>
   * @param index The index of the element to return.
   * @return The members at the given index.
   */
  public java.lang.String getMembers(int index) {
    return members_.get(index);
  }
  /**
   * <code>repeated string members = 2;</code>
   * @param index The index of the value to return.
   * @return The bytes of the members at the given index.
   */
  public com.google.protobuf.ByteString
      getMembersBytes(int index) {
    return members_.getByteString(index);
  }

  public static final int COORDINATOR_FIELD_NUMBER = 3;
  private volatile java.lang.Object coordinator_;
  /**
   * <code>string coordinator = 3;</code>
   * @return The coordinator.
   */
  @java.lang.Override
  public java.lang.String getCoordinator() {
    java.lang.Object ref = coordinator_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      coordinator_ = s;
      return s;
    }
  }
  /**
   * <code>string coordinator = 3;</code>
   * @return The bytes for coordinator.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getCoordinatorBytes() {
    java.lang.Object ref = coordinator_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      coordinator_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int SIZE_FIELD_NUMBER = 4;
  private int size_;
  /**
   * <code>int32 size = 4;</code>
   * @return The size.
   */
  @java.lang.Override
  public int getSize() {
    return size_;
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
    if (num_ != 0) {
      output.writeInt32(1, num_);
    }
    for (int i = 0; i < members_.size(); i++) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, members_.getRaw(i));
    }
    if (!getCoordinatorBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, coordinator_);
    }
    if (size_ != 0) {
      output.writeInt32(4, size_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (num_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, num_);
    }
    {
      int dataSize = 0;
      for (int i = 0; i < members_.size(); i++) {
        dataSize += computeStringSizeNoTag(members_.getRaw(i));
      }
      size += dataSize;
      size += 1 * getMembersList().size();
    }
    if (!getCoordinatorBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, coordinator_);
    }
    if (size_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(4, size_);
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
    if (!(obj instanceof io.grpc.jchannelRpc.ServerViewPy)) {
      return super.equals(obj);
    }
    io.grpc.jchannelRpc.ServerViewPy other = (io.grpc.jchannelRpc.ServerViewPy) obj;

    if (getNum()
        != other.getNum()) return false;
    if (!getMembersList()
        .equals(other.getMembersList())) return false;
    if (!getCoordinator()
        .equals(other.getCoordinator())) return false;
    if (getSize()
        != other.getSize()) return false;
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
    hash = (37 * hash) + NUM_FIELD_NUMBER;
    hash = (53 * hash) + getNum();
    if (getMembersCount() > 0) {
      hash = (37 * hash) + MEMBERS_FIELD_NUMBER;
      hash = (53 * hash) + getMembersList().hashCode();
    }
    hash = (37 * hash) + COORDINATOR_FIELD_NUMBER;
    hash = (53 * hash) + getCoordinator().hashCode();
    hash = (37 * hash) + SIZE_FIELD_NUMBER;
    hash = (53 * hash) + getSize();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static io.grpc.jchannelRpc.ServerViewPy parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.grpc.jchannelRpc.ServerViewPy parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.grpc.jchannelRpc.ServerViewPy parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.grpc.jchannelRpc.ServerViewPy parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.grpc.jchannelRpc.ServerViewPy parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.grpc.jchannelRpc.ServerViewPy parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.grpc.jchannelRpc.ServerViewPy parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.grpc.jchannelRpc.ServerViewPy parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.grpc.jchannelRpc.ServerViewPy parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static io.grpc.jchannelRpc.ServerViewPy parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.grpc.jchannelRpc.ServerViewPy parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.grpc.jchannelRpc.ServerViewPy parseFrom(
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
  public static Builder newBuilder(io.grpc.jchannelRpc.ServerViewPy prototype) {
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
   * Protobuf type {@code cn.yingming.grpc1.ServerViewPy}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:cn.yingming.grpc1.ServerViewPy)
      io.grpc.jchannelRpc.ServerViewPyOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.grpc.jchannelRpc.JChannelRpc.internal_static_cn_yingming_grpc1_ServerViewPy_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.grpc.jchannelRpc.JChannelRpc.internal_static_cn_yingming_grpc1_ServerViewPy_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.grpc.jchannelRpc.ServerViewPy.class, io.grpc.jchannelRpc.ServerViewPy.Builder.class);
    }

    // Construct using io.grpc.jchannelRpc.ServerViewPy.newBuilder()
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
      num_ = 0;

      members_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000001);
      coordinator_ = "";

      size_ = 0;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return io.grpc.jchannelRpc.JChannelRpc.internal_static_cn_yingming_grpc1_ServerViewPy_descriptor;
    }

    @java.lang.Override
    public io.grpc.jchannelRpc.ServerViewPy getDefaultInstanceForType() {
      return io.grpc.jchannelRpc.ServerViewPy.getDefaultInstance();
    }

    @java.lang.Override
    public io.grpc.jchannelRpc.ServerViewPy build() {
      io.grpc.jchannelRpc.ServerViewPy result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public io.grpc.jchannelRpc.ServerViewPy buildPartial() {
      io.grpc.jchannelRpc.ServerViewPy result = new io.grpc.jchannelRpc.ServerViewPy(this);
      int from_bitField0_ = bitField0_;
      result.num_ = num_;
      if (((bitField0_ & 0x00000001) != 0)) {
        members_ = members_.getUnmodifiableView();
        bitField0_ = (bitField0_ & ~0x00000001);
      }
      result.members_ = members_;
      result.coordinator_ = coordinator_;
      result.size_ = size_;
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
      if (other instanceof io.grpc.jchannelRpc.ServerViewPy) {
        return mergeFrom((io.grpc.jchannelRpc.ServerViewPy)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(io.grpc.jchannelRpc.ServerViewPy other) {
      if (other == io.grpc.jchannelRpc.ServerViewPy.getDefaultInstance()) return this;
      if (other.getNum() != 0) {
        setNum(other.getNum());
      }
      if (!other.members_.isEmpty()) {
        if (members_.isEmpty()) {
          members_ = other.members_;
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          ensureMembersIsMutable();
          members_.addAll(other.members_);
        }
        onChanged();
      }
      if (!other.getCoordinator().isEmpty()) {
        coordinator_ = other.coordinator_;
        onChanged();
      }
      if (other.getSize() != 0) {
        setSize(other.getSize());
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
      io.grpc.jchannelRpc.ServerViewPy parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (io.grpc.jchannelRpc.ServerViewPy) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int num_ ;
    /**
     * <code>int32 num = 1;</code>
     * @return The num.
     */
    @java.lang.Override
    public int getNum() {
      return num_;
    }
    /**
     * <code>int32 num = 1;</code>
     * @param value The num to set.
     * @return This builder for chaining.
     */
    public Builder setNum(int value) {
      
      num_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 num = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearNum() {
      
      num_ = 0;
      onChanged();
      return this;
    }

    private com.google.protobuf.LazyStringList members_ = com.google.protobuf.LazyStringArrayList.EMPTY;
    private void ensureMembersIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        members_ = new com.google.protobuf.LazyStringArrayList(members_);
        bitField0_ |= 0x00000001;
       }
    }
    /**
     * <code>repeated string members = 2;</code>
     * @return A list containing the members.
     */
    public com.google.protobuf.ProtocolStringList
        getMembersList() {
      return members_.getUnmodifiableView();
    }
    /**
     * <code>repeated string members = 2;</code>
     * @return The count of members.
     */
    public int getMembersCount() {
      return members_.size();
    }
    /**
     * <code>repeated string members = 2;</code>
     * @param index The index of the element to return.
     * @return The members at the given index.
     */
    public java.lang.String getMembers(int index) {
      return members_.get(index);
    }
    /**
     * <code>repeated string members = 2;</code>
     * @param index The index of the value to return.
     * @return The bytes of the members at the given index.
     */
    public com.google.protobuf.ByteString
        getMembersBytes(int index) {
      return members_.getByteString(index);
    }
    /**
     * <code>repeated string members = 2;</code>
     * @param index The index to set the value at.
     * @param value The members to set.
     * @return This builder for chaining.
     */
    public Builder setMembers(
        int index, java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureMembersIsMutable();
      members_.set(index, value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string members = 2;</code>
     * @param value The members to add.
     * @return This builder for chaining.
     */
    public Builder addMembers(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  ensureMembersIsMutable();
      members_.add(value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string members = 2;</code>
     * @param values The members to add.
     * @return This builder for chaining.
     */
    public Builder addAllMembers(
        java.lang.Iterable<java.lang.String> values) {
      ensureMembersIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, members_);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string members = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearMembers() {
      members_ = com.google.protobuf.LazyStringArrayList.EMPTY;
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    /**
     * <code>repeated string members = 2;</code>
     * @param value The bytes of the members to add.
     * @return This builder for chaining.
     */
    public Builder addMembersBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      ensureMembersIsMutable();
      members_.add(value);
      onChanged();
      return this;
    }

    private java.lang.Object coordinator_ = "";
    /**
     * <code>string coordinator = 3;</code>
     * @return The coordinator.
     */
    public java.lang.String getCoordinator() {
      java.lang.Object ref = coordinator_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        coordinator_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string coordinator = 3;</code>
     * @return The bytes for coordinator.
     */
    public com.google.protobuf.ByteString
        getCoordinatorBytes() {
      java.lang.Object ref = coordinator_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        coordinator_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string coordinator = 3;</code>
     * @param value The coordinator to set.
     * @return This builder for chaining.
     */
    public Builder setCoordinator(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      coordinator_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string coordinator = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearCoordinator() {
      
      coordinator_ = getDefaultInstance().getCoordinator();
      onChanged();
      return this;
    }
    /**
     * <code>string coordinator = 3;</code>
     * @param value The bytes for coordinator to set.
     * @return This builder for chaining.
     */
    public Builder setCoordinatorBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      coordinator_ = value;
      onChanged();
      return this;
    }

    private int size_ ;
    /**
     * <code>int32 size = 4;</code>
     * @return The size.
     */
    @java.lang.Override
    public int getSize() {
      return size_;
    }
    /**
     * <code>int32 size = 4;</code>
     * @param value The size to set.
     * @return This builder for chaining.
     */
    public Builder setSize(int value) {
      
      size_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 size = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearSize() {
      
      size_ = 0;
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


    // @@protoc_insertion_point(builder_scope:cn.yingming.grpc1.ServerViewPy)
  }

  // @@protoc_insertion_point(class_scope:cn.yingming.grpc1.ServerViewPy)
  private static final io.grpc.jchannelRpc.ServerViewPy DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new io.grpc.jchannelRpc.ServerViewPy();
  }

  public static io.grpc.jchannelRpc.ServerViewPy getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ServerViewPy>
      PARSER = new com.google.protobuf.AbstractParser<ServerViewPy>() {
    @java.lang.Override
    public ServerViewPy parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ServerViewPy(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ServerViewPy> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ServerViewPy> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public io.grpc.jchannelRpc.ServerViewPy getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

