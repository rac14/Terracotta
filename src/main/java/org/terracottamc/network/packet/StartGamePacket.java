package org.terracottamc.network.packet;

import org.terracottamc.entity.player.GameMode;
import org.terracottamc.math.Vector;
import org.terracottamc.network.packet.type.GamePublishSetting;
import org.terracottamc.world.Difficulty;
import org.terracottamc.world.Dimension;
import org.terracottamc.world.gamerule.GameRule;
import org.terracottamc.world.generator.GeneratorType;

import java.util.List;
import java.util.Map;

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
public class StartGamePacket extends Packet {

    private long entityUniqueId;
    private long entityId;
    private GameMode gameMode;
    private Vector vector;
    private float yaw;
    private float pitch;
    private int worldSeed;
    private int spawnBiomeType;
    private String userDefinedBiomeName;
    private Dimension dimension;
    private GeneratorType generatorType;
    private Difficulty difficulty;
    private boolean achievementsDisabled;
    private int dayCycleStopTime;
    private int educationEditionOffer;
    private boolean educationEditionFeaturesEnabled;
    private String educationEditionProductId;
    private float rainTime;
    private float thunderTime;
    private boolean confirmedPlatformLockedContent;
    private boolean multiplayerGame;
    private boolean broadcastToLAN;
    private GamePublishSetting xblBroadcastIntent;
    private GamePublishSetting platformBroadcastIntent;
    private boolean commandsEnabled;
    private boolean texturePacksRequired;
    private List<GameRule<?>> gameRules;
    private int experimentAmount;
    private boolean experimentsPreviouslyToggled;
    private boolean bonusChest;
    private boolean startWithMapEnabled;
    private int permissionLevel;
    private int serverChunkTickRange;
    private boolean lockedBehaviourPack;
    private boolean lockedResourcePack;
    private boolean fromLockedWorldTemplate;
    private boolean usingMsaGamerTagsOnly;
    private boolean fromWorldTemplate;
    private boolean worldTemplateOptionLocked;
    private boolean onlySpawningV1Villagers;
    private int limitedWorldWidth;
    private int limitedWorldHeight;
    private boolean netherType;
    private boolean experimentalGameplay;
    private String worldId;
    private String worldName;
    private String premiumWorldTemplateId;
    private boolean trail;
    private boolean movementServerAuthoritative;
    private int rewindHistorySize;
    private boolean serverAuthoritativeBlockBreaking;
    private long currentTick;
    private int enchantmentSeed;

    private int customBlocksAmount;
    private List<Map<String, Object>> itemPalette;
    private String multiplayerCorrelationId;
    private boolean inventoryServerAuthoritative;
    private String serverEngine;

    @Override
    public int getPacketId() {
        return Protocol.START_GAME_PACKET;
    }

    @Override
    public void serialize() {
        super.serialize();

        this.writeVarLong(this.entityUniqueId);
        this.writeVarLong(this.entityId);
        this.writeVarInt(this.gameMode.ordinal());
        this.writeVector(this.vector);
        this.writeFloatLE(this.yaw);
        this.writeFloatLE(this.pitch);
        this.writeVarInt(this.worldSeed);
        this.writeShortLE(this.spawnBiomeType);
        this.writeString(this.userDefinedBiomeName);
        this.writeVarInt(this.dimension.ordinal());
        this.writeVarInt(this.generatorType.ordinal());
        this.writeVarInt(this.gameMode.ordinal());
        this.writeVarInt(this.dimension.ordinal());
        this.writeBlockVector(this.vector);
        this.writeBoolean(this.achievementsDisabled);
        this.writeVarInt(this.dayCycleStopTime);
        this.writeVarInt(this.educationEditionOffer);
        this.writeBoolean(this.educationEditionFeaturesEnabled);
        this.writeString(this.educationEditionProductId);
        this.writeFloatLE(this.rainTime);
        this.writeFloatLE(this.thunderTime);
        this.writeBoolean(this.confirmedPlatformLockedContent);
        this.writeBoolean(this.multiplayerGame);
        this.writeBoolean(this.broadcastToLAN);
        this.writeVarInt(this.xblBroadcastIntent.ordinal());
        this.writeVarInt(this.platformBroadcastIntent.ordinal());
        this.writeBoolean(this.commandsEnabled);
        this.writeBoolean(this.texturePacksRequired);
        this.writeGameRules(this.gameRules);
        this.writeIntLE(this.experimentAmount);
        this.writeBoolean(this.experimentsPreviouslyToggled);
        this.writeBoolean(this.bonusChest);
        this.writeBoolean(this.startWithMapEnabled);
        this.writeVarInt(this.permissionLevel);
        this.writeIntLE(this.serverChunkTickRange);
        this.writeBoolean(this.lockedBehaviourPack);
        this.writeBoolean(this.lockedResourcePack);
        this.writeBoolean(this.fromLockedWorldTemplate);
        this.writeBoolean(this.usingMsaGamerTagsOnly);
        this.writeBoolean(this.fromWorldTemplate);
        this.writeBoolean(this.worldTemplateOptionLocked);
        this.writeBoolean(this.onlySpawningV1Villagers);
        this.writeString(Protocol.MINECRAFT_VERSION);
        this.writeIntLE(this.limitedWorldWidth);
        this.writeIntLE(this.limitedWorldHeight);
        this.writeBoolean(this.netherType);
        this.writeBoolean(this.experimentalGameplay);
        this.writeString(this.worldId);
        this.writeString(this.worldName);
        this.writeString(this.premiumWorldTemplateId);
        this.writeBoolean(this.trail);
        this.writeUnsignedVarInt(this.movementServerAuthoritative ? 1 : 0);
        this.writeVarInt(this.rewindHistorySize);
        this.writeBoolean(this.serverAuthoritativeBlockBreaking);
        this.writeLongLE(this.currentTick);
        this.writeVarInt(this.enchantmentSeed);
        this.writeUnsignedVarInt(this.customBlocksAmount);

        this.writeUnsignedVarInt(this.itemPalette.size());

        for (final Map<String, Object> item : this.itemPalette) {
            this.writeString((String) item.get("name"));
            this.writeShortLE((int) ((double) item.get("id")));
            this.writeBoolean(false);
        }

        this.writeString(this.multiplayerCorrelationId);
        this.writeBoolean(this.inventoryServerAuthoritative);
        this.writeString(this.serverEngine);
    }

    public void setEntityUniqueId(final long entityUniqueId) {
        this.entityUniqueId = entityUniqueId;
    }

    public void setEntityId(final long entityId) {
        this.entityId = entityId;
    }

    public void setGameMode(final GameMode gameMode) {
        this.gameMode = gameMode;
    }

    public void setVector(final Vector vector) {
        this.vector = vector;
    }

    public void setYaw(final float yaw) {
        this.yaw = yaw;
    }

    public void setPitch(final float pitch) {
        this.pitch = pitch;
    }

    public void setWorldSeed(final int worldSeed) {
        this.worldSeed = worldSeed;
    }

    public void setSpawnBiomeType(final int spawnBiomeType) {
        this.spawnBiomeType = spawnBiomeType;
    }

    public void setUserDefinedBiomeName(final String userDefinedBiomeName) {
        this.userDefinedBiomeName = userDefinedBiomeName;
    }

    public void setDimension(final Dimension dimension) {
        this.dimension = dimension;
    }

    public void setGeneratorType(final GeneratorType generatorType) {
        this.generatorType = generatorType;
    }

    public void setDifficulty(final Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setAchievementsDisabled(final boolean achievementsDisabled) {
        this.achievementsDisabled = achievementsDisabled;
    }

    public void setDayCycleStopTime(final int dayCycleStopTime) {
        this.dayCycleStopTime = dayCycleStopTime;
    }

    public void setEducationEditionOffer(final int educationEditionOffer) {
        this.educationEditionOffer = educationEditionOffer;
    }

    public void setEducationEditionFeaturesEnabled(final boolean educationEditionFeaturesEnabled) {
        this.educationEditionFeaturesEnabled = educationEditionFeaturesEnabled;
    }

    public void setEducationEditionProductId(final String educationEditionProductId) {
        this.educationEditionProductId = educationEditionProductId;
    }

    public void setRainTime(final float rainTime) {
        this.rainTime = rainTime;
    }

    public void setThunderTime(final float thunderTime) {
        this.thunderTime = thunderTime;
    }

    public void setConfirmedPlatformLockedContent(final boolean confirmedPlatformLockedContent) {
        this.confirmedPlatformLockedContent = confirmedPlatformLockedContent;
    }

    public void setMultiplayerGame(final boolean multiplayerGame) {
        this.multiplayerGame = multiplayerGame;
    }

    public void setBroadcastToLAN(final boolean broadcastToLAN) {
        this.broadcastToLAN = broadcastToLAN;
    }

    public void setXblBroadcastIntent(final GamePublishSetting xblBroadcastIntent) {
        this.xblBroadcastIntent = xblBroadcastIntent;
    }

    public void setPlatformBroadcastIntent(final GamePublishSetting platformBroadcastIntent) {
        this.platformBroadcastIntent = platformBroadcastIntent;
    }

    public void setCommandsEnabled(final boolean commandsEnabled) {
        this.commandsEnabled = commandsEnabled;
    }

    public void setTexturePacksRequired(final boolean texturePacksRequired) {
        this.texturePacksRequired = texturePacksRequired;
    }

    public void setGameRules(final List<GameRule<?>> gameRules) {
        this.gameRules = gameRules;
    }

    public void setExperimentAmount(final int experimentAmount) {
        this.experimentAmount = experimentAmount;
    }

    public void setExperimentsPreviouslyToggled(final boolean experimentsPreviouslyToggled) {
        this.experimentsPreviouslyToggled = experimentsPreviouslyToggled;
    }

    public void setBonusChest(final boolean bonusChest) {
        this.bonusChest = bonusChest;
    }

    public void setStartWithMapEnabled(final boolean startWithMapEnabled) {
        this.startWithMapEnabled = startWithMapEnabled;
    }

    public void setPermissionLevel(final int permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    public void setServerChunkTickRange(final int serverChunkTickRange) {
        this.serverChunkTickRange = serverChunkTickRange;
    }

    public void setLockedBehaviourPack(final boolean lockedBehaviourPack) {
        this.lockedBehaviourPack = lockedBehaviourPack;
    }

    public void setLockedResourcePack(final boolean lockedResourcePack) {
        this.lockedResourcePack = lockedResourcePack;
    }

    public void setFromLockedWorldTemplate(final boolean fromLockedWorldTemplate) {
        this.fromLockedWorldTemplate = fromLockedWorldTemplate;
    }

    public void setUsingMsaGamerTagsOnly(final boolean usingMsaGamerTagsOnly) {
        this.usingMsaGamerTagsOnly = usingMsaGamerTagsOnly;
    }

    public void setFromWorldTemplate(final boolean fromWorldTemplate) {
        this.fromWorldTemplate = fromWorldTemplate;
    }

    public void setWorldTemplateOptionLocked(final boolean worldTemplateOptionLocked) {
        this.worldTemplateOptionLocked = worldTemplateOptionLocked;
    }

    public void setOnlySpawningV1Villagers(final boolean onlySpawningV1Villagers) {
        this.onlySpawningV1Villagers = onlySpawningV1Villagers;
    }

    public void setLimitedWorldWidth(final int limitedWorldWidth) {
        this.limitedWorldWidth = limitedWorldWidth;
    }

    public void setLimitedWorldHeight(final int limitedWorldHeight) {
        this.limitedWorldHeight = limitedWorldHeight;
    }

    public void setNetherType(final boolean netherType) {
        this.netherType = netherType;
    }

    public void setExperimentalGameplay(final boolean experimentalGameplay) {
        this.experimentalGameplay = experimentalGameplay;
    }

    public void setWorldId(final String worldId) {
        this.worldId = worldId;
    }

    public void setWorldName(final String worldName) {
        this.worldName = worldName;
    }

    public void setPremiumWorldTemplateId(final String premiumWorldTemplateId) {
        this.premiumWorldTemplateId = premiumWorldTemplateId;
    }

    public void setTrail(final boolean trail) {
        this.trail = trail;
    }

    public void setMovementServerAuthoritative(final boolean movementServerAuthoritative) {
        this.movementServerAuthoritative = movementServerAuthoritative;
    }

    public void setRewindHistorySize(final int rewindHistorySize) {
        this.rewindHistorySize = rewindHistorySize;
    }

    public void setServerAuthoritativeBlockBreaking(final boolean serverAuthoritativeBlockBreaking) {
        this.serverAuthoritativeBlockBreaking = serverAuthoritativeBlockBreaking;
    }

    public void setCurrentTick(final long currentTick) {
        this.currentTick = currentTick;
    }

    public void setEnchantmentSeed(final int enchantmentSeed) {
        this.enchantmentSeed = enchantmentSeed;
    }

    public void setCustomBlocksAmount(final int customBlocksAmount) {
        this.customBlocksAmount = customBlocksAmount;
    }

    public void setItemPalette(final List<Map<String, Object>> itemPalette) {
        this.itemPalette = itemPalette;
    }

    public void setMultiplayerCorrelationId(final String multiplayerCorrelationId) {
        this.multiplayerCorrelationId = multiplayerCorrelationId;
    }

    public void setInventoryServerAuthoritative(final boolean inventoryServerAuthoritative) {
        this.inventoryServerAuthoritative = inventoryServerAuthoritative;
    }

    public void setServerEngine(final String serverEngine) {
        this.serverEngine = serverEngine;
    }
}