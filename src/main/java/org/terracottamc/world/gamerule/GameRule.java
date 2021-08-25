package org.terracottamc.world.gamerule;

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
public class GameRule<V> {

    private final GameRuleType gameRuleType;
    private V defaultValue;

    /**
     * Creates a new {@link org.terracottamc.world.gamerule.GameRule<V>} with the given default value
     * and {@link org.terracottamc.world.gamerule.GameRuleType}
     *
     * @param gameRuleType which is used to create this {@link org.terracottamc.world.gamerule.GameRule}
     * @param defaultValue which represents the default value of this {@link org.terracottamc.world.gamerule.GameRule}
     */
    public GameRule(final GameRuleType gameRuleType, V defaultValue) {
        this.gameRuleType = gameRuleType;
        this.defaultValue = defaultValue;
    }

    /**
     * Retrieves the [{@link org.terracottamc.world.gamerule.GameRuleType}
     * of this {@link org.terracottamc.world.gamerule.GameRule}
     *
     * @return a fresh {@link org.terracottamc.world.gamerule.GameRuleType}
     */
    public GameRuleType getGameRuleType() {
        return this.gameRuleType;
    }

    /**
     * Retrieves the default value of this {@link org.terracottamc.world.gamerule.GameRule}
     *
     * @return a fresh default value
     */
    public V getDefaultValue() {
        return this.defaultValue;
    }

    /**
     * Updates the default value of this {@link org.terracottamc.world.gamerule.GameRule}
     *
     * @param value that should be set
     */
    public void setValue(final Object value) {
        this.defaultValue = (V) value;
    }
}