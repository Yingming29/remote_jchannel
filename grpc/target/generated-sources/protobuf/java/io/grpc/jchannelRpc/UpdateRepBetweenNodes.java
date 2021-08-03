// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: jchannel.proto

package io.grpc.jchannelRpc;

/**
 * Protobuf type {@code cn.yingming.grpc1.UpdateRepBetweenNodes}
 */
public final class UpdateRepBetweenNodes extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:cn.yingming.grpc1.UpdateRepBetweenNodes)
    UpdateRepBetweenNodesOrBuilder {
private static final long serialVersionUID = 0L;
  // Use UpdateRepBetweenNodes.newBuilder() to construct.
  private UpdateRepBetweenNodes(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private UpdateRepBetweenNodes() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new UpdateRepBetweenNodes();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private UpdateRepBetweenNodes(
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
            io.grpc.jchannelRpc.UpdateNameCacheRep.Builder subBuilder = null;
            if (nameCache_ != null) {
              subBuilder = nameCache_.toBuilder();
            }
            nameCache_ = input.readMessage(io.grpc.jchannelRpc.UpdateNameCacheRep.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(nameCache_);
              nameCache_ = subBuilder.buildPartial();
            }

            break;
          }
          case 18: {
            io.grpc.jchannelRpc.ViewRep.Builder subBuilder = null;
            if (clientView_ != null) {
              subBuilder = clientView_.toBuilder();
            }
            clientView_ = input.readMessage(io.grpc.jchannelRpc.ViewRep.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(clientView_);
              clientView_ = subBuilder.buildPartial();
            }

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
    return io.grpc.jchannelRpc.JChannelRpc.internal_static_cn_yingming_grpc1_UpdateRepBetweenNodes_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return io.grpc.jchannelRpc.JChannelRpc.internal_static_cn_yingming_grpc1_UpdateRepBetweenNodes_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            io.grpc.jchannelRpc.UpdateRepBetweenNodes.class, io.grpc.jchannelRpc.UpdateRepBetweenNodes.Builder.class);
  }

  public static final int NAMECACHE_FIELD_NUMBER = 1;
  private io.grpc.jchannelRpc.UpdateNameCacheRep nameCache_;
  /**
   * <code>.cn.yingming.grpc1.UpdateNameCacheRep nameCache = 1;</code>
   * @return Whether the nameCache field is set.
   */
  @java.lang.Override
  public boolean hasNameCache() {
    return nameCache_ != null;
  }
  /**
   * <code>.cn.yingming.grpc1.UpdateNameCacheRep nameCache = 1;</code>
   * @return The nameCache.
   */
  @java.lang.Override
  public io.grpc.jchannelRpc.UpdateNameCacheRep getNameCache() {
    return nameCache_ == null ? io.grpc.jchannelRpc.UpdateNameCacheRep.getDefaultInstance() : nameCache_;
  }
  /**
   * <code>.cn.yingming.grpc1.UpdateNameCacheRep nameCache = 1;</code>
   */
  @java.lang.Override
  public io.grpc.jchannelRpc.UpdateNameCacheRepOrBuilder getNameCacheOrBuilder() {
    return getNameCache();
  }

  public static final int CLIENTVIEW_FIELD_NUMBER = 2;
  private io.grpc.jchannelRpc.ViewRep clientView_;
  /**
   * <code>.cn.yingming.grpc1.ViewRep clientView = 2;</code>
   * @return Whether the clientView field is set.
   */
  @java.lang.Override
  public boolean hasClientView() {
    return clientView_ != null;
  }
  /**
   * <code>.cn.yingming.grpc1.ViewRep clientView = 2;</code>
   * @return The clientView.
   */
  @java.lang.Override
  public io.grpc.jchannelRpc.ViewRep getClientView() {
    return clientView_ == null ? io.grpc.jchannelRpc.ViewRep.getDefaultInstance() : clientView_;
  }
  /**
   * <code>.cn.yingming.grpc1.ViewRep clientView = 2;</code>
   */
  @java.lang.Override
  public io.grpc.jchannelRpc.ViewRepOrBuilder getClientViewOrBuilder() {
    return getClientView();
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
    if (nameCache_ != null) {
      output.writeMessage(1, getNameCache());
    }
    if (clientView_ != null) {
      output.writeMessage(2, getClientView());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (nameCache_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getNameCache());
    }
    if (clientView_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getClientView());
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
    if (!(obj instanceof io.grpc.jchannelRpc.UpdateRepBetweenNodes)) {
      return super.equals(obj);
    }
    io.grpc.jchannelRpc.UpdateRepBetweenNodes other = (io.grpc.jchannelRpc.UpdateRepBetweenNodes) obj;

    if (hasNameCache() != other.hasNameCache()) return false;
    if (hasNameCache()) {
      if (!getNameCache()
          .equals(other.getNameCache())) return false;
    }
    if (hasClientView() != other.hasClientView()) return false;
    if (hasClientView()) {
      if (!getClientView()
          .equals(other.getClientView())) return false;
    }
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
    if (hasNameCache()) {
      hash = (37 * hash) + NAMECACHE_FIELD_NUMBER;
      hash = (53 * hash) + getNameCache().hashCode();
    }
    if (hasClientView()) {
      hash = (37 * hash) + CLIENTVIEW_FIELD_NUMBER;
      hash = (53 * hash) + getClientView().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static io.grpc.jchannelRpc.UpdateRepBetweenNodes parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.grpc.jchannelRpc.UpdateRepBetweenNodes parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.grpc.jchannelRpc.UpdateRepBetweenNodes parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.grpc.jchannelRpc.UpdateRepBetweenNodes parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.grpc.jchannelRpc.UpdateRepBetweenNodes parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static io.grpc.jchannelRpc.UpdateRepBetweenNodes parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static io.grpc.jchannelRpc.UpdateRepBetweenNodes parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.grpc.jchannelRpc.UpdateRepBetweenNodes parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.grpc.jchannelRpc.UpdateRepBetweenNodes parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static io.grpc.jchannelRpc.UpdateRepBetweenNodes parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static io.grpc.jchannelRpc.UpdateRepBetweenNodes parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static io.grpc.jchannelRpc.UpdateRepBetweenNodes parseFrom(
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
  public static Builder newBuilder(io.grpc.jchannelRpc.UpdateRepBetweenNodes prototype) {
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
   * Protobuf type {@code cn.yingming.grpc1.UpdateRepBetweenNodes}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:cn.yingming.grpc1.UpdateRepBetweenNodes)
      io.grpc.jchannelRpc.UpdateRepBetweenNodesOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return io.grpc.jchannelRpc.JChannelRpc.internal_static_cn_yingming_grpc1_UpdateRepBetweenNodes_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return io.grpc.jchannelRpc.JChannelRpc.internal_static_cn_yingming_grpc1_UpdateRepBetweenNodes_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              io.grpc.jchannelRpc.UpdateRepBetweenNodes.class, io.grpc.jchannelRpc.UpdateRepBetweenNodes.Builder.class);
    }

    // Construct using io.grpc.jchannelRpc.UpdateRepBetweenNodes.newBuilder()
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
      if (nameCacheBuilder_ == null) {
        nameCache_ = null;
      } else {
        nameCache_ = null;
        nameCacheBuilder_ = null;
      }
      if (clientViewBuilder_ == null) {
        clientView_ = null;
      } else {
        clientView_ = null;
        clientViewBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return io.grpc.jchannelRpc.JChannelRpc.internal_static_cn_yingming_grpc1_UpdateRepBetweenNodes_descriptor;
    }

    @java.lang.Override
    public io.grpc.jchannelRpc.UpdateRepBetweenNodes getDefaultInstanceForType() {
      return io.grpc.jchannelRpc.UpdateRepBetweenNodes.getDefaultInstance();
    }

    @java.lang.Override
    public io.grpc.jchannelRpc.UpdateRepBetweenNodes build() {
      io.grpc.jchannelRpc.UpdateRepBetweenNodes result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public io.grpc.jchannelRpc.UpdateRepBetweenNodes buildPartial() {
      io.grpc.jchannelRpc.UpdateRepBetweenNodes result = new io.grpc.jchannelRpc.UpdateRepBetweenNodes(this);
      if (nameCacheBuilder_ == null) {
        result.nameCache_ = nameCache_;
      } else {
        result.nameCache_ = nameCacheBuilder_.build();
      }
      if (clientViewBuilder_ == null) {
        result.clientView_ = clientView_;
      } else {
        result.clientView_ = clientViewBuilder_.build();
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
      if (other instanceof io.grpc.jchannelRpc.UpdateRepBetweenNodes) {
        return mergeFrom((io.grpc.jchannelRpc.UpdateRepBetweenNodes)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(io.grpc.jchannelRpc.UpdateRepBetweenNodes other) {
      if (other == io.grpc.jchannelRpc.UpdateRepBetweenNodes.getDefaultInstance()) return this;
      if (other.hasNameCache()) {
        mergeNameCache(other.getNameCache());
      }
      if (other.hasClientView()) {
        mergeClientView(other.getClientView());
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
      io.grpc.jchannelRpc.UpdateRepBetweenNodes parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (io.grpc.jchannelRpc.UpdateRepBetweenNodes) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private io.grpc.jchannelRpc.UpdateNameCacheRep nameCache_;
    private com.google.protobuf.SingleFieldBuilderV3<
        io.grpc.jchannelRpc.UpdateNameCacheRep, io.grpc.jchannelRpc.UpdateNameCacheRep.Builder, io.grpc.jchannelRpc.UpdateNameCacheRepOrBuilder> nameCacheBuilder_;
    /**
     * <code>.cn.yingming.grpc1.UpdateNameCacheRep nameCache = 1;</code>
     * @return Whether the nameCache field is set.
     */
    public boolean hasNameCache() {
      return nameCacheBuilder_ != null || nameCache_ != null;
    }
    /**
     * <code>.cn.yingming.grpc1.UpdateNameCacheRep nameCache = 1;</code>
     * @return The nameCache.
     */
    public io.grpc.jchannelRpc.UpdateNameCacheRep getNameCache() {
      if (nameCacheBuilder_ == null) {
        return nameCache_ == null ? io.grpc.jchannelRpc.UpdateNameCacheRep.getDefaultInstance() : nameCache_;
      } else {
        return nameCacheBuilder_.getMessage();
      }
    }
    /**
     * <code>.cn.yingming.grpc1.UpdateNameCacheRep nameCache = 1;</code>
     */
    public Builder setNameCache(io.grpc.jchannelRpc.UpdateNameCacheRep value) {
      if (nameCacheBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        nameCache_ = value;
        onChanged();
      } else {
        nameCacheBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.cn.yingming.grpc1.UpdateNameCacheRep nameCache = 1;</code>
     */
    public Builder setNameCache(
        io.grpc.jchannelRpc.UpdateNameCacheRep.Builder builderForValue) {
      if (nameCacheBuilder_ == null) {
        nameCache_ = builderForValue.build();
        onChanged();
      } else {
        nameCacheBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.cn.yingming.grpc1.UpdateNameCacheRep nameCache = 1;</code>
     */
    public Builder mergeNameCache(io.grpc.jchannelRpc.UpdateNameCacheRep value) {
      if (nameCacheBuilder_ == null) {
        if (nameCache_ != null) {
          nameCache_ =
            io.grpc.jchannelRpc.UpdateNameCacheRep.newBuilder(nameCache_).mergeFrom(value).buildPartial();
        } else {
          nameCache_ = value;
        }
        onChanged();
      } else {
        nameCacheBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.cn.yingming.grpc1.UpdateNameCacheRep nameCache = 1;</code>
     */
    public Builder clearNameCache() {
      if (nameCacheBuilder_ == null) {
        nameCache_ = null;
        onChanged();
      } else {
        nameCache_ = null;
        nameCacheBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.cn.yingming.grpc1.UpdateNameCacheRep nameCache = 1;</code>
     */
    public io.grpc.jchannelRpc.UpdateNameCacheRep.Builder getNameCacheBuilder() {
      
      onChanged();
      return getNameCacheFieldBuilder().getBuilder();
    }
    /**
     * <code>.cn.yingming.grpc1.UpdateNameCacheRep nameCache = 1;</code>
     */
    public io.grpc.jchannelRpc.UpdateNameCacheRepOrBuilder getNameCacheOrBuilder() {
      if (nameCacheBuilder_ != null) {
        return nameCacheBuilder_.getMessageOrBuilder();
      } else {
        return nameCache_ == null ?
            io.grpc.jchannelRpc.UpdateNameCacheRep.getDefaultInstance() : nameCache_;
      }
    }
    /**
     * <code>.cn.yingming.grpc1.UpdateNameCacheRep nameCache = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        io.grpc.jchannelRpc.UpdateNameCacheRep, io.grpc.jchannelRpc.UpdateNameCacheRep.Builder, io.grpc.jchannelRpc.UpdateNameCacheRepOrBuilder> 
        getNameCacheFieldBuilder() {
      if (nameCacheBuilder_ == null) {
        nameCacheBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            io.grpc.jchannelRpc.UpdateNameCacheRep, io.grpc.jchannelRpc.UpdateNameCacheRep.Builder, io.grpc.jchannelRpc.UpdateNameCacheRepOrBuilder>(
                getNameCache(),
                getParentForChildren(),
                isClean());
        nameCache_ = null;
      }
      return nameCacheBuilder_;
    }

    private io.grpc.jchannelRpc.ViewRep clientView_;
    private com.google.protobuf.SingleFieldBuilderV3<
        io.grpc.jchannelRpc.ViewRep, io.grpc.jchannelRpc.ViewRep.Builder, io.grpc.jchannelRpc.ViewRepOrBuilder> clientViewBuilder_;
    /**
     * <code>.cn.yingming.grpc1.ViewRep clientView = 2;</code>
     * @return Whether the clientView field is set.
     */
    public boolean hasClientView() {
      return clientViewBuilder_ != null || clientView_ != null;
    }
    /**
     * <code>.cn.yingming.grpc1.ViewRep clientView = 2;</code>
     * @return The clientView.
     */
    public io.grpc.jchannelRpc.ViewRep getClientView() {
      if (clientViewBuilder_ == null) {
        return clientView_ == null ? io.grpc.jchannelRpc.ViewRep.getDefaultInstance() : clientView_;
      } else {
        return clientViewBuilder_.getMessage();
      }
    }
    /**
     * <code>.cn.yingming.grpc1.ViewRep clientView = 2;</code>
     */
    public Builder setClientView(io.grpc.jchannelRpc.ViewRep value) {
      if (clientViewBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        clientView_ = value;
        onChanged();
      } else {
        clientViewBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.cn.yingming.grpc1.ViewRep clientView = 2;</code>
     */
    public Builder setClientView(
        io.grpc.jchannelRpc.ViewRep.Builder builderForValue) {
      if (clientViewBuilder_ == null) {
        clientView_ = builderForValue.build();
        onChanged();
      } else {
        clientViewBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.cn.yingming.grpc1.ViewRep clientView = 2;</code>
     */
    public Builder mergeClientView(io.grpc.jchannelRpc.ViewRep value) {
      if (clientViewBuilder_ == null) {
        if (clientView_ != null) {
          clientView_ =
            io.grpc.jchannelRpc.ViewRep.newBuilder(clientView_).mergeFrom(value).buildPartial();
        } else {
          clientView_ = value;
        }
        onChanged();
      } else {
        clientViewBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.cn.yingming.grpc1.ViewRep clientView = 2;</code>
     */
    public Builder clearClientView() {
      if (clientViewBuilder_ == null) {
        clientView_ = null;
        onChanged();
      } else {
        clientView_ = null;
        clientViewBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.cn.yingming.grpc1.ViewRep clientView = 2;</code>
     */
    public io.grpc.jchannelRpc.ViewRep.Builder getClientViewBuilder() {
      
      onChanged();
      return getClientViewFieldBuilder().getBuilder();
    }
    /**
     * <code>.cn.yingming.grpc1.ViewRep clientView = 2;</code>
     */
    public io.grpc.jchannelRpc.ViewRepOrBuilder getClientViewOrBuilder() {
      if (clientViewBuilder_ != null) {
        return clientViewBuilder_.getMessageOrBuilder();
      } else {
        return clientView_ == null ?
            io.grpc.jchannelRpc.ViewRep.getDefaultInstance() : clientView_;
      }
    }
    /**
     * <code>.cn.yingming.grpc1.ViewRep clientView = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        io.grpc.jchannelRpc.ViewRep, io.grpc.jchannelRpc.ViewRep.Builder, io.grpc.jchannelRpc.ViewRepOrBuilder> 
        getClientViewFieldBuilder() {
      if (clientViewBuilder_ == null) {
        clientViewBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            io.grpc.jchannelRpc.ViewRep, io.grpc.jchannelRpc.ViewRep.Builder, io.grpc.jchannelRpc.ViewRepOrBuilder>(
                getClientView(),
                getParentForChildren(),
                isClean());
        clientView_ = null;
      }
      return clientViewBuilder_;
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


    // @@protoc_insertion_point(builder_scope:cn.yingming.grpc1.UpdateRepBetweenNodes)
  }

  // @@protoc_insertion_point(class_scope:cn.yingming.grpc1.UpdateRepBetweenNodes)
  private static final io.grpc.jchannelRpc.UpdateRepBetweenNodes DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new io.grpc.jchannelRpc.UpdateRepBetweenNodes();
  }

  public static io.grpc.jchannelRpc.UpdateRepBetweenNodes getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<UpdateRepBetweenNodes>
      PARSER = new com.google.protobuf.AbstractParser<UpdateRepBetweenNodes>() {
    @java.lang.Override
    public UpdateRepBetweenNodes parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new UpdateRepBetweenNodes(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<UpdateRepBetweenNodes> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<UpdateRepBetweenNodes> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public io.grpc.jchannelRpc.UpdateRepBetweenNodes getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

