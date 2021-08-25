package org.terracottamc.network.packet.type;

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
public enum ChatType {

    CLIENT,
    CHAT,
    TRANSLATION,
    POPUP,
    JUKEBOX_POPUP,
    TIP,
    SYSTEM,
    WHISPER,
    ANNOUNCEMENT,
    OBJECT,
    OBJECT_WHISPER;

    /**
     * Retrieves the {@link org.terracottamc.network.packet.type.ChatType} by their identifier
     *
     * @param chatTypeId which is used to retrieve
     *                   the {@link org.terracottamc.network.packet.type.ChatType}
     *
     * @return a fresh {@link org.terracottamc.network.packet.type.ChatType}
     */
    public static ChatType retrieveChatTypeById(final int chatTypeId) {
        for (final ChatType chatType : ChatType.values()) {
            if (chatType.ordinal() == chatTypeId) {
                return chatType;
            }
        }

        return null;
    }
}