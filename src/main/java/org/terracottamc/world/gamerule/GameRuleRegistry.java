package org.terracottamc.world.gamerule;

import java.util.ArrayList;
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
public class GameRuleRegistry {

    private static final List<GameRule<?>> gameRules = new ArrayList<>();

    /**
     * Registers all {@link org.terracottamc.world.gamerule.GameRule} objects with their default values
     */
    public void registerAllGameRules() {
        GameRuleRegistry.gameRules.addAll(Arrays.asList(
                new GameRule<>(GameRuleType.COMMAND_BLOCK_OUTPUT, true),
                new GameRule<>(GameRuleType.COMMAND_BLOCKS_ENABLED, false),
                new GameRule<>(GameRuleType.DO_DAYLIGHT_CYCLE, true),
                new GameRule<>(GameRuleType.DO_ENTITY_DROPS, true),
                new GameRule<>(GameRuleType.DO_FIRE_TICK, true),
                new GameRule<>(GameRuleType.DO_IMMEDIATE_RESPAWN, false),
                new GameRule<>(GameRuleType.DO_INSOMNIA, false),
                new GameRule<>(GameRuleType.DO_MOB_LOOT, true),
                new GameRule<>(GameRuleType.DO_MOB_SPAWNING, true),
                new GameRule<>(GameRuleType.DO_TILE_DROPS, true),
                new GameRule<>(GameRuleType.DO_WEATHER_CYCLE, true),
                new GameRule<>(GameRuleType.DROWNING_DAMAGE, true),
                new GameRule<>(GameRuleType.FALL_DAMAGE, true),
                new GameRule<>(GameRuleType.FIRE_DAMAGE, true),
                new GameRule<>(GameRuleType.FREEZE_DAMAGE, true),
                new GameRule<>(GameRuleType.FUNCTION_COMMAND_LIMIT, 10000),
                new GameRule<>(GameRuleType.KEEP_INVENTORY, false),
                new GameRule<>(GameRuleType.MAX_COMMAND_CHAIN_LENGTH, 65536),
                new GameRule<>(GameRuleType.MOB_GRIEFING, true),
                new GameRule<>(GameRuleType.NATURAL_REGENERATION, true),
                new GameRule<>(GameRuleType.PVP, true),
                new GameRule<>(GameRuleType.RANDOM_TICK_SPEED, 3),
                new GameRule<>(GameRuleType.SEND_COMMAND_FEEDBACK, true),
                new GameRule<>(GameRuleType.SHOW_COORDINATES, false),
                new GameRule<>(GameRuleType.SHOW_DEATH_MESSAGES, true),
                new GameRule<>(GameRuleType.SHOW_TAGS, true),
                new GameRule<>(GameRuleType.SPAWN_RADIUS, 5),
                new GameRule<>(GameRuleType.TNT_EXPLODES, true)
        ));
    }

    /**
     * Retrieves all registered {@link org.terracottamc.world.gamerule.GameRule}
     *
     * @return a fresh {@link java.util.List}
     */
    public static List<GameRule<?>> getGameRules() {
        return GameRuleRegistry.gameRules;
    }

    /**
     * Updates the value of the given game rule that is located
     * by their {@link org.terracottamc.world.gamerule.GameRuleType}
     *
     * @param gameRuleType which is used to retrieve the {@link org.terracottamc.world.gamerule.GameRule}
     *                     which should be updated
     * @param value        that represents the new value
     */
    public void setGameRule(final GameRuleType gameRuleType, final Object value) {
        for (final GameRule<?> gameRule : GameRuleRegistry.gameRules) {
            if (gameRule.getGameRuleType().equals(gameRuleType)) {
                gameRule.setValue(value);

                break;
            }
        }
    }
}