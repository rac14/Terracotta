package org.terracottamc.network.packet;

import lombok.ToString;
import org.terracottamc.network.packet.type.ViolationSeverity;
import org.terracottamc.network.packet.type.ViolationType;

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
@ToString
public class ViolationWarningPacket extends Packet {

    private ViolationType violationType;
    private ViolationSeverity violationSeverity;
    private int packetId;
    private String context;

    @Override
    public int getPacketId() {
        return Protocol.VIOLATION_WARNING_PACKET;
    }

    @Override
    public void deserialize() {
        super.deserialize();

        final int violationTypeId = this.readVarInt();

        this.violationType = ViolationType.retrieveViolationTypeById(violationTypeId);

        final int violationSeverityId = this.readVarInt();

        this.violationSeverity = ViolationSeverity.retrieveViolationSeverityById(violationSeverityId);
        this.packetId = this.readVarInt();
        this.context = this.readString();
    }

    /**
     * Retrieves the {@link org.terracottamc.network.packet.type.ViolationType}
     * of this {@link org.terracottamc.network.packet.ViolationWarningPacket}
     *
     * @return a fresh {@link org.terracottamc.network.packet.type.ViolationType}
     */
    public ViolationType getViolationType() {
        return this.violationType;
    }

    /**
     * Retrieves the {@link org.terracottamc.network.packet.type.ViolationSeverity}
     * of this {@link org.terracottamc.network.packet.ViolationWarningPacket}
     *
     * @return a fresh {@link org.terracottamc.network.packet.type.ViolationSeverity}
     */
    public ViolationSeverity getViolationSeverity() {
        return this.violationSeverity;
    }

    /**
     * Retrieves the received packet identifier of this {@link org.terracottamc.network.packet.ViolationWarningPacket}
     *
     * @return a fresh packet id
     */
    public int getPacketIdentifier() {
        return this.packetId;
    }

    /**
     * Retrieves the context in which this {@link org.terracottamc.network.packet.ViolationWarningPacket} was sent
     *
     * @return a fresh {@link java.lang.String}
     */
    public String getContext() {
        return this.context;
    }
}