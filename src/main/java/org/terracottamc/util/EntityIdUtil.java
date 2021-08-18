package org.terracottamc.util;

import org.terracottamc.entity.Entity;

import java.util.HashMap;
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
public class EntityIdUtil {

    private static final Map<Long, Entity> entityIdMap = new HashMap<>();

    /**
     * Registers a new entity identifier
     *
     * @param entity who should receive the new identifier
     *
     * @return the new entity id
     */
    public static long registerEntityId(final Entity entity) {
        final long entityId = EntityIdUtil.calculateNextFreeEntityId();

        EntityIdUtil.entityIdMap.put(entityId, entity);

        return entityId;
    }

    /**
     * Removes an already existing entity identifier
     *
     * @param entity who owns the entity identifier
     */
    public static void unregisterEntityId(final Entity entity) {
        EntityIdUtil.entityIdMap.remove(entity.getEntityId());
    }

    /**
     * Calculates the next free entity id to use
     *
     * @return a fresh {@link java.lang.Long} value
     */
    private static long calculateNextFreeEntityId() {
        for (long value = 0; value < (EntityIdUtil.entityIdMap.keySet().size() + 10); value++) {
            if (EntityIdUtil.entityIdMap.containsKey(value)) {
                return value;
            }
        }

        return -1;
    }
}