package org.terracottamc.util;

import com.google.common.io.ByteStreams;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.terracottamc.network.packet.Protocol;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

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
public class BedrockResourceDataReader {

    private static final Map<Integer, String> protocolMinecraftVersions = new HashMap<>();
    private static final Map<Integer, List<Map<String, Object>>> itemPalettes = new HashMap<>();
    private static final Map<Integer, List<Map<String, Object>>> creativeItems = new HashMap<>();
    private static final Map<Integer, Map<String, Integer>> itemNameRuntimeIds = new HashMap<>();
    private static final Map<Integer, byte[]> entityIdentifiersData = new HashMap<>();
    private static final Map<Integer, byte[]> biomeDefinitionsData = new HashMap<>();

    /**
     * Initializes this {@link org.terracottamc.util.BedrockResourceDataReader}
     */
    public static void initialize() {
        BedrockResourceDataReader.protocolMinecraftVersions.put(Protocol.CURRENT_PROTOCOL, Protocol.MINECRAFT_VERSION);
        BedrockResourceDataReader.protocolMinecraftVersions.put(Protocol.PROTOCOL_v1_17_0, Protocol.MINECRAFT_VERSION_v1_17_0);

        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        final File bedrockFile = new File("src/main/resources/bedrock/");
        final File itemPaletteFolder = new File(bedrockFile.getPath() + "/item_palette/");
        final File creativeItemsFolder = new File(bedrockFile.getPath() + "/creative_items/");
        final File entityIdentifiersFolder = new File(bedrockFile.getPath() + "/entity_identifiers/");
        final File biomeDefinitionsFolder = new File(bedrockFile.getPath() + "/biome_definitions/");

        // ItemPalette
        if (itemPaletteFolder.isDirectory()) {
            for (final File file : Objects.requireNonNull(itemPaletteFolder.listFiles())) {
                try {
                    final String minecraftVersion = file.getName().split("\\.")[1]
                            .replaceAll("_", ".");
                    final int protocolVersion = BedrockResourceDataReader
                            .retrieveProtocolVersionByMinecraftVersion(minecraftVersion);

                    if (protocolVersion != -1) {
                        final JsonElement elementToParse = new JsonParser()
                                .parse(new InputStreamReader(new FileInputStream(file)));

                        BedrockResourceDataReader.itemPalettes.put(protocolVersion,
                                gson.fromJson(elementToParse, List.class));
                    }
                } catch (final FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }

        final Set<Map.Entry<Integer, List<Map<String, Object>>>> itemPaletteEntries =
                BedrockResourceDataReader.itemPalettes.entrySet();

        for (final Map.Entry<Integer, List<Map<String, Object>>> itemPaletteEntry : itemPaletteEntries) {
            final int protocolVersion = itemPaletteEntry.getKey();
            final List<Map<String, Object>> itemPalette = itemPaletteEntry.getValue();
            final Map<String, Integer> itemNameRuntimeIdMap = new HashMap<>();

            for (final Map<String, Object> item : itemPalette) {
                itemNameRuntimeIdMap.put((String) item.get("name"), (int) ((double) item.get("id")));
            }

            BedrockResourceDataReader.itemNameRuntimeIds.put(protocolVersion, itemNameRuntimeIdMap);
        }

        // CreativeItems
        if (creativeItemsFolder.isDirectory()) {
            for (final File file : Objects.requireNonNull(creativeItemsFolder.listFiles())) {
                final String minecraftVersion = file.getName().split("\\.")[1]
                        .replaceAll("_", ".");
                final int protocolVersion = BedrockResourceDataReader
                        .retrieveProtocolVersionByMinecraftVersion(minecraftVersion);

                if (protocolVersion != -1) {
                    try {
                        final JsonArray arrayToParse = new JsonParser()
                                .parse(new InputStreamReader(new FileInputStream(file))).getAsJsonObject()
                                .getAsJsonArray("items");

                        BedrockResourceDataReader.creativeItems.put(protocolVersion,
                                gson.fromJson(arrayToParse, List.class));
                    } catch (final FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        // EntityIdentifiers
        if (entityIdentifiersFolder.isDirectory()) {
            for (final File file : Objects.requireNonNull(entityIdentifiersFolder.listFiles())) {
                final String minecraftVersion = file.getName().split("\\.")[1]
                        .replaceAll("_", ".");
                final int protocolVersion = BedrockResourceDataReader
                        .retrieveProtocolVersionByMinecraftVersion(minecraftVersion);

                if (protocolVersion != -1) {
                    try {
                        BedrockResourceDataReader.entityIdentifiersData.put(protocolVersion,
                                ByteStreams.toByteArray(new FileInputStream(file)));
                    } catch (final IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        // BiomeDefinitions
        if (biomeDefinitionsFolder.isDirectory()) {
            for (final File file : Objects.requireNonNull(biomeDefinitionsFolder.listFiles())) {
                final String minecraftVersion = file.getName().split("\\.")[1]
                        .replaceAll("_", ".");
                final int protocolVersion = BedrockResourceDataReader
                        .retrieveProtocolVersionByMinecraftVersion(minecraftVersion);

                if (protocolVersion != -1) {
                    try {
                        BedrockResourceDataReader.biomeDefinitionsData.put(protocolVersion,
                                ByteStreams.toByteArray(new FileInputStream(file)));
                    } catch (final IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Retrieves the correct item palette for the given protocol version of the player
     *
     * @param protocolVersion which is used to retrieve the item palette and to support multiple protocol functionality
     *
     * @return a fresh {@link java.util.List}
     */
    public static List<Map<String, Object>> retrieveItemPaletteByProtocolVersion(final int protocolVersion) {
        final Set<Map.Entry<Integer, List<Map<String, Object>>>> itemPaletteEntries =
                BedrockResourceDataReader.itemPalettes.entrySet();

        for (final Map.Entry<Integer, List<Map<String, Object>>> itemPaletteEntry : itemPaletteEntries) {
            if (itemPaletteEntry.getKey() == protocolVersion) {
                return itemPaletteEntry.getValue();
            }
        }

        return null;
    }

    /**
     * Retrieves the item name and item runtime identifier mapping for the given protocol version of the player
     *
     * @param protocolVersion which is used to retrieve the mapping and to support multiple protocol functionality
     *
     * @return a fresh {@link java.util.Map}
     */
    public static Map<String, Integer> retrieveItemNameRuntimeIdsByProtocolVersion(final int protocolVersion) {
        final Set<Map.Entry<Integer, Map<String, Integer>>> itemNameRuntimeIdsEntries =
                BedrockResourceDataReader.itemNameRuntimeIds.entrySet();

        for (final Map.Entry<Integer, Map<String, Integer>> itemNameRuntimeIdsEntry : itemNameRuntimeIdsEntries) {
            if (itemNameRuntimeIdsEntry.getKey() == protocolVersion) {
                return itemNameRuntimeIdsEntry.getValue();
            }
        }

        return null;
    }

    /**
     * Retrieves the correct creative items for the given protocol version of the player
     *
     * @param protocolVersion which is used to retrieve the creative items and to support multiple protocol functionality
     *
     * @return a fresh {@link java.util.List}
     */
    public static List<Map<String, Object>> retrieveCreativeItemsByProtocolVersion(final int protocolVersion) {
        final Set<Map.Entry<Integer, List<Map<String, Object>>>> creativeItemsEntries =
                BedrockResourceDataReader.creativeItems.entrySet();

        for (final Map.Entry<Integer, List<Map<String, Object>>> creativeItemsEntry : creativeItemsEntries) {
            if (creativeItemsEntry.getKey() == protocolVersion) {
                return creativeItemsEntry.getValue();
            }
        }

        return null;
    }

    /**
     * Retrieves the entity identifiers data for the given protocol version of the player
     *
     * @param protocolVersion which is used to retrieve the entity identifiers data
     *                        and to support multiple protocol functionality
     *
     * @return fresh entity identifiers data
     */
    public static byte[] retrieveEntityIdentifiersDataByProtocolVersion(final int protocolVersion) {
        final Set<Map.Entry<Integer, byte[]>> entityIdentifiersDataEntries =
                BedrockResourceDataReader.entityIdentifiersData.entrySet();

        for (final Map.Entry<Integer, byte[]> entityIdentifiersDataEntry : entityIdentifiersDataEntries) {
            if (entityIdentifiersDataEntry.getKey() == protocolVersion) {
                return entityIdentifiersDataEntry.getValue();
            }
        }

        return null;
    }

    /**
     * Retrieves the biome definition data for the given protocol version of the player
     *
     * @param protocolVersion which is used to retrieve the biome definition data
     *                        and to support multiple protocol functionality
     *
     * @return fresh biome definition data
     */
    public static byte[] retrieveBiomeDefinitionsDataByProtocolVersion(final int protocolVersion) {
        final Set<Map.Entry<Integer, byte[]>> biomeDefinitionsDataEntries =
                BedrockResourceDataReader.biomeDefinitionsData.entrySet();

        for (final Map.Entry<Integer, byte[]> biomeDefinitionsDataEntry : biomeDefinitionsDataEntries) {
            if (biomeDefinitionsDataEntry.getKey() == protocolVersion) {
                return biomeDefinitionsDataEntry.getValue();
            }
        }

        return null;
    }

    /**
     * Retrieves the protocol version from its minecraft version as a {@link java.lang.String}
     *
     * @param minecraftVersion that is needed to retrieve the protocol version from it
     *
     * @return a fresh {@link java.lang.Integer} as protocol version
     */
    private static Integer retrieveProtocolVersionByMinecraftVersion(final String minecraftVersion) {
        for (final Map.Entry<Integer, String> versionEntry : BedrockResourceDataReader.protocolMinecraftVersions.entrySet()) {
            if (versionEntry.getValue().equalsIgnoreCase(minecraftVersion)) {
                return versionEntry.getKey();
            }
        }

        return -1;
    }
}