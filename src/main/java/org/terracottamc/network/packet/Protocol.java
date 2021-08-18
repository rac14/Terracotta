package org.terracottamc.network.packet;

import java.util.Arrays;
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
public interface Protocol {

    int CURRENT_PROTOCOL = 448; // 1.17.10 until 1.17.11
    int PROTOCOL_v1_17_0 = 440; // 1.17.0 until 1.17.2

    List<Integer> SUPPORTED_PROTOCOL_VERSIONS = Arrays.asList(Protocol.CURRENT_PROTOCOL, Protocol.PROTOCOL_v1_17_0);

    String MINECRAFT_VERSION = "1.17.11";

    int BATCH_PACKET = 0xfe;
    byte LOGIN_PACKET = 0x01;
    byte PLAY_STATUS_PACKET = 0x02;
    // ServerToClientHandshakePacket 0x03
    // ClientToServerHandshakePacket 0x04
    byte DISCONNECT_PACKET = 0x05;
    byte RESOURCE_PACKS_INFO_PACKET = 0x06;
    byte RESOURCE_PACK_STACK_PACKET = 0x07;
    byte RESOURCE_PACK_CLIENT_RESPONSE_PACKET = 0x08;
    byte RESOURCE_PACK_DATA_INFO_PACKET = 0x52;
    byte RESOURCE_PACK_CHUNK_DATA_PACKET = 0x53;
    byte RESOURCE_PACK_CHUNK_REQUEST_PACKET = 0x54;
    int CLIENT_CACHE_STATUS_PACKET = 0x81;
    int VIOLATION_WARNING_PACKET = 0x9c;
}