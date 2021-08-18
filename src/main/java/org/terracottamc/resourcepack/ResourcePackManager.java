package org.terracottamc.resourcepack;

import com.google.common.io.Files;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.terracottamc.server.Server;
import org.terracottamc.util.ChatColor;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

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
public class ResourcePackManager {

    private final Map<String, ResourcePack> resourcePacks = new HashMap<>();

    /**
     * Loads and registers all {@link org.terracottamc.resourcepack.ResourcePack} data
     */
    public void loadResourcePacks() {
        final File resourcePacksPath = new File(System.getProperty("user.dir") + "/resource_packs");

        if (!resourcePacksPath.exists()) {
            resourcePacksPath.mkdirs();
        }

        if (!resourcePacksPath.isDirectory()) {
            return;
        }

        for (final File file : Objects.requireNonNull(resourcePacksPath.listFiles())) {
            if (!file.isDirectory()) {
                final String fileEnding = Files.getFileExtension(file.getName());

                if (fileEnding.equalsIgnoreCase("zip") || fileEnding.equalsIgnoreCase("mcpack")) {
                    try (final ZipFile zipFile = new ZipFile(file)) {
                        final String manifestFileName = "manifest.json";
                        ZipEntry manifestEntry = zipFile.getEntry(manifestFileName);

                        // due to the mcpack file extension
                        if (manifestEntry == null) {
                            manifestEntry = zipFile.stream().filter(zipEntry -> !zipEntry.isDirectory() &&
                                    zipEntry.getName().toLowerCase().endsWith(manifestFileName)).filter(zipEntry -> {
                                final File zipEntryFile = new File(zipEntry.getName());

                                if (!zipEntryFile.getName().equalsIgnoreCase(manifestFileName)) {
                                    return false;
                                }

                                return zipEntryFile.getParent() == null ||
                                        zipEntryFile.getParentFile().getParent() == null;
                            }).findFirst().orElseThrow(() ->
                                    new IllegalArgumentException("The " + manifestFileName + " file could not be found"));
                        }

                        final JsonObject manifest = new JsonParser()
                                .parse(new InputStreamReader(zipFile.getInputStream(manifestEntry),
                                        StandardCharsets.UTF_8)).getAsJsonObject();

                        if (!this.isManifestValid(manifest)) {
                            throw new IllegalArgumentException("The " + manifestFileName + " file is invalid");
                        }

                        final JsonObject manifestHeader = manifest.getAsJsonObject("header");
                        final String resourcePackName = manifestHeader.get("name").getAsString();
                        final String resourcePackUuid = manifestHeader.get("uuid").getAsString();
                        final JsonArray resourcePackVersionArray = manifestHeader.get("version").getAsJsonArray();
                        final String resourcePackVersion = resourcePackVersionArray.toString()
                                .replace("[", "").replace("]", "")
                                .replaceAll(",", ".");
                        final int resourcePackSize = Math.toIntExact(file.length());
                        final byte[] resourcePackSha256 = MessageDigest.getInstance("SHA-256")
                                .digest(java.nio.file.Files.readAllBytes(file.toPath()));

                        Server.getInstance().getLogger().info("Read resource pack " + resourcePackName +
                                ChatColor.RESET + " successful from " + file.getName());

                        final ResourcePack resourcePack = new ResourcePack(file, resourcePackName, resourcePackUuid,
                                resourcePackVersion, resourcePackSize, resourcePackSha256, new byte[0]);

                        this.resourcePacks.put(resourcePackUuid, resourcePack);
                    } catch (final IOException | NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    /**
     * Retrieves a fresh {@link org.terracottamc.resourcepack.ResourcePack} by their {@link java.util.UUID}
     *
     * @param uuid which is used to retrieve the {@link org.terracottamc.resourcepack.ResourcePack}
     *
     * @return a fresh {@link org.terracottamc.resourcepack.ResourcePack}
     */
    public ResourcePack retrieveResourcePackById(final String uuid) {
        return this.resourcePacks.get(uuid);
    }

    /**
     * Retrieves all resource packs
     *
     * @return a fresh {@link java.util.Collection}
     */
    public Collection<ResourcePack> retrieveResourcePacks() {
        return this.resourcePacks.values();
    }

    /**
     * Proofs whether the given {@link com.google.gson.JsonObject} manifest is valid
     *
     * @param manifest which should be validated
     *
     * @return whether the manifest is valid
     */
    private boolean isManifestValid(final JsonObject manifest) {
        if (manifest.has("format_version") && manifest.has("header") &&
                manifest.has("modules")) {
            final JsonObject manifestHeader = manifest.get("header").getAsJsonObject();

            if (manifestHeader.has("description") && manifestHeader.has("name") &&
                    manifestHeader.has("uuid") && manifestHeader.has("version")) {
                final JsonArray headerVersion = manifestHeader.getAsJsonArray("version");

                return headerVersion.size() == 3;
            }
        }

        return false;
    }
}