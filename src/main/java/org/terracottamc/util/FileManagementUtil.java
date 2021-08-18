package org.terracottamc.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.terracottamc.server.ServerConfigurationData;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

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
public class FileManagementUtil {

    private static ServerConfigurationData serverConfigurationData;

    /**
     * Creates all necessary files which are important to satisfy Terracotta's needs
     */
    public static void createNecessaryFiles() {
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        final File directory = new File(System.getProperty("user.dir"));
        final File propertiesFile = new File(directory.getPath(), "properties.json");

        try {
            if (!propertiesFile.exists()) {
                propertiesFile.createNewFile();

                try (final FileWriter fileWriter = new FileWriter(propertiesFile)) {
                    final ServerConfigurationData serverConfigurationData = new ServerConfigurationData();
                    serverConfigurationData.setAddress("0.0.0.0");
                    serverConfigurationData.setPort(19132);
                    serverConfigurationData.setMaxPlayers(20);
                    serverConfigurationData.setMotd("A fresh Terracotta server");
                    serverConfigurationData.setSubmotd("developed by Kaooot");
                    serverConfigurationData.setDefaultGameMode("Creative");
                    serverConfigurationData.setForceResourcePacks(false);

                    fileWriter.write(gson.toJson(serverConfigurationData));

                    FileManagementUtil.serverConfigurationData = serverConfigurationData;
                }
            } else {
                try (final FileReader fileReader = new FileReader(propertiesFile)) {
                    FileManagementUtil.serverConfigurationData = gson.fromJson(fileReader, ServerConfigurationData.class);
                }
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the {@link org.terracottamc.server.ServerConfigurationData}
     *
     * @return a fresh {@link org.terracottamc.server.ServerConfigurationData}
     */
    public static ServerConfigurationData getServerConfigurationData() {
        return FileManagementUtil.serverConfigurationData;
    }
}