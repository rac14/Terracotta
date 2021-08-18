package org.terracottamc;

import org.terracottamc.server.Server;
import org.terracottamc.util.FileManagementUtil;

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
public class Terracotta {

    public static void main(final String[] args) {
        FileManagementUtil.createNecessaryFiles();

        final Server server = new Server();
        server.start();
    }
}