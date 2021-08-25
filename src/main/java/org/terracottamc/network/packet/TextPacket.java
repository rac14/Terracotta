package org.terracottamc.network.packet;

import org.terracottamc.network.packet.type.ChatType;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2021, TerracottaMC
 * All rights reserved.
 *
 * <p>
 * This project is licensed under the BSD 3-Clause License which
 * can be found in the root directory of this source tree
 *
 * @author Kaooot
 * @version 1.0
 */
public class TextPacket extends Packet {

    private final List<String> arguments = new ArrayList<>();

    private ChatType chatType;
    private String messageSender;
    private String message;
    private boolean localized;
    private String xboxId;
    private String deviceId;

    @Override
    public int getPacketId() {
        return Protocol.TEXT_PACKET;
    }

    @Override
    public void deserialize() {
        super.deserialize();

        final byte chatTypeId = this.readByte();

        this.chatType = ChatType.retrieveChatTypeById(chatTypeId);
        this.localized = this.readBoolean() || this.chatType.equals(ChatType.TRANSLATION);

        switch (this.chatType) {
            case CHAT:
            case WHISPER:
            case ANNOUNCEMENT:
                this.messageSender = this.readString();
            case CLIENT:
            case TIP:
            case SYSTEM:
            case OBJECT:
            case OBJECT_WHISPER:
                this.message = this.readString();
                break;
            case TRANSLATION:
            case POPUP:
            case JUKEBOX_POPUP:
                this.message = this.readString();

                final int amount = this.readUnsignedVarInt();

                for (int i = 0; i < amount; i++) {
                    this.arguments.add(this.readString());
                }
        }

        this.xboxId = this.readString();
        this.deviceId = this.readString();
    }

    @Override
    public void serialize() {
        super.serialize();

        this.writeByte(this.chatType.ordinal());
        this.writeBoolean(this.localized || this.chatType.equals(ChatType.TRANSLATION));

        switch (this.chatType) {
            case CHAT:
            case WHISPER:
            case ANNOUNCEMENT:
                this.writeString(this.messageSender);
            case CLIENT:
            case TIP:
            case SYSTEM:
            case OBJECT:
            case OBJECT_WHISPER:
                this.writeString(this.message);
                break;
            case TRANSLATION:
            case POPUP:
            case JUKEBOX_POPUP:
                this.writeString(this.message);
                this.writeUnsignedVarInt(this.arguments.size());

                for (final String argument : this.arguments) {
                    this.writeString(argument);
                }
        }

        this.writeString(this.xboxId);
        this.writeString(this.deviceId);
    }

    /**
     * Retrieves the arguments of this {@link org.terracottamc.network.packet.TextPacket}
     *
     * @return a fresh {@link java.util.List}
     */
    public List<String> getArguments() {
        return this.arguments;
    }

    /**
     * Retrieves the {@link org.terracottamc.network.packet.type.ChatType}
     * of this {@link org.terracottamc.network.packet.TextPacket}
     *
     * @return a fresh {@link org.terracottamc.network.packet.type.ChatType}
     */
    public ChatType getChatType() {
        return this.chatType;
    }

    /**
     * Retrieves the message sender name of this {@link org.terracottamc.network.packet.TextPacket}
     *
     * @return a fresh {@link java.lang.String}
     */
    public String getMessageSender() {
        return this.messageSender;
    }

    /**
     * Retrieves the message of this {@link org.terracottamc.network.packet.TextPacket}
     *
     * @return a fresh {@link java.lang.String}
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Retrieves the xbox account id of this {@link org.terracottamc.network.packet.TextPacket}
     *
     * @return a fresh {@link java.lang.String}
     */
    public String getXboxId() {
        return this.xboxId;
    }

    /**
     * Retrieves the device identifier of this {@link org.terracottamc.network.packet.TextPacket}
     *
     * @return a fresh {@link java.lang.String}
     */
    public String getDeviceId() {
        return this.deviceId;
    }

    /**
     * Adds a new argument to the @code arguments {@link java.util.List}
     *
     * @param argument that should be added
     */
    public void addArgument(final String argument) {
        this.arguments.add(argument);
    }

    /**
     * Updates the {@link org.terracottamc.network.packet.type.ChatType}
     * of this {@link org.terracottamc.network.packet.TextPacket}
     *
     * @param chatType which represents the updated value
     */
    public void setChatType(final ChatType chatType) {
        this.chatType = chatType;
    }

    /**
     * Updates the message sender name of this {@link org.terracottamc.network.packet.TextPacket}
     *
     * @param messageSender which represents the updated value
     */
    public void setMessageSender(final String messageSender) {
        this.messageSender = messageSender;
    }

    /**
     * Updates the message of this {@link org.terracottamc.network.packet.TextPacket}
     *
     * @param message which represents the updated value
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * Updates the xbox account id of this {@link org.terracottamc.network.packet.TextPacket}
     *
     * @param xboxId which represents the updated value
     */
    public void setXboxId(final String xboxId) {
        this.xboxId = xboxId;
    }

    /**
     * Updates the device identifier of this {@link org.terracottamc.network.packet.TextPacket}
     *
     * @param deviceId which represents the updated value
     */
    public void setDeviceId(final String deviceId) {
        this.deviceId = deviceId;
    }
}