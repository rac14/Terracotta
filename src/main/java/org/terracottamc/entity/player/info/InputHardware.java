package org.terracottamc.entity.player.info;

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
public enum InputHardware {

    KEYBOARD,
    TOUCH,
    CONTROLLER;

    /**
     * Retrieves this {@link org.terracottamc.entity.player.info.InputHardware} by their given identifier
     *
     * @param inputHardwareId the identifier which is used for identification
     *                        of this {@link org.terracottamc.entity.player.info.InputHardware}
     *
     * @return a fresh {@link org.terracottamc.entity.player.info.InputHardware}
     */
    public static InputHardware retrieveInputHardwareById(final int inputHardwareId) {
        for (final InputHardware inputHardware : InputHardware.values()) {
            if (inputHardwareId == (inputHardware.ordinal() + 1)) {
                return inputHardware;
            }
        }

        return null;
    }
}