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
public enum PlayStatus {

    LOGIN_SUCCESS,
    LOGIN_FAILED_CLIENT_OUTDATED,
    LOGIN_FAILED_SERVER_OUTDATED,
    PLAYER_SPAWN,
    LOGIN_FAILED_INVALID_CLIENT,
    LOGIN_FAILED_VANILLA_EDU_SERVER,
    LOGIN_FAILED_EDU_VANILLA_SERVER,
    LOGIN_FAILED_SERVER_FULL
}