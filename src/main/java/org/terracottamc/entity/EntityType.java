package org.terracottamc.entity;

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
public enum EntityType {

    // Drops
    DROPPED_ITEM(0x40, "item"),
    EXPERIENCE_ORB(0x45, "xp_orb"),

    // Blocks
    PRIMED_TNT(0x41, "tnt"),
    FALLING_BLOCK(0x42, "falling_block"),
    MOVING_BLOCK(0x43, "moving_block"),

    // Immobile and Projectiles
    ARMOR_STAND(0x3d, "armor_stand"),
    THROWN_EXPERIENCE_BOTTLE(0x44, "xp_bottle"),
    EYE_OF_ENDER(0x46, "eye_of_ender_signal"),
    ENDER_CRYSTAL(0x47, "ender_crystal"),
    FIREWORK_ROCKET(0x48, "fireworks_rocket"),
    THROWN_TRIDENT(0x49, "thrown_trident"),
    SHULKER_BULLET(0x4c, "shulker_bullet"),
    FISHING_ROD_HOOK(0x4d, "fishing_hook"),
    DRAGON_FIRE_BALL(0x4f, "dragon_fireball"),
    ARROW(0x50, "arrow"),
    THROWN_SNOWBALL(0x51, "snowball"),
    THROWN_EGG(0x52, "egg"),
    PAINTING(0x53, "painting"),
    MINECART(0x54, "minecart"),
    GHAST_FIREBALL(0x55, "fireball"),
    THROWN_SPLASH_POTION(0x56, "splash_potion"),
    THROWN_ENDER_PEARL(0x57, "ender_pearl"),
    LEASH_KNOT(0x58, "leash_knot"),
    WITHER_SKULL(0x59, "wither_skull"),
    BOAT(0x5a, "boat"),
    BLUE_WITHER_SKULL(0x5b, "wither_skull_dangerous"),
    LIGHTNING_BOLT(0x5d, "lightning_bolt"),
    BLAZE_FIREBALL(0x5e, "small_fireball"),
    AREA_EFFECT_CLOUD(0x5f, "area_affect_cloud"),
    MINECART_WITH_HOPPER(0x60, "hopper_minecart"),
    MINECART_WITH_TNT(0x61, "tnt_minecart"),
    MINECART_WITH_CHEST(0x62, "chest_minecart"),
    MINECART_WITH_COMMAND_BLOCK(0x64, "command_block_minecart"),
    THROWN_LINGERING_POTION(0x65, "lingering_potion"),
    LLAMA_SPIT(0x66, "llama_spit"),
    EVOCATION_FANGS(0x67, "evocation_fang"),
    ICE_BOMB(0x6a, "ice_bomb"),
    BALLOON(0x6b, "balloon"),

    // Hostile Monsters
    ZOMBIE(0x20, "zombie"),
    CREEPER(0x21, "creeper"),
    SKELETON(0x22, "skeleton"),
    SPIDER(0x23, "spider"),
    ZOMBIFIED_PIGLIN(0x24, "zombie_pigman"),
    SLIME(0x25, "slime"),
    ENDERMAN(0x26, "enderman"),
    SILVERFISH(0x27, "silverfish"),
    CAVE_SPIDER(0x28, "cave_spider"),
    GHAST(0x29, "ghast"),
    MAGMA_CUBE(0x2a, "magma_cube"),
    BLAZE(0x2b, "blaze"),
    ZOMBIE_VILLAGER(0x2c, "zombie_villager"),
    WITCH(0x2d, "witch"),
    STRAY(0x2e, "stray"),
    HUSK(0x2f, "husk"),
    WITHER_SKELETON(0x30, "wither_skeleton"),
    GUARDIAN(0x31, "guardian"),
    ELDER_GUARDIAN(0x32, "elder_guardian"),
    WITHER(0x34, "wither"),
    ENDER_DRAGON(0x35, "ender_dragon"),
    SHULKER(0x36, "shulker"),
    ENDERMITE(0x37, "endermite"),
    VINDICATOR(0x39, "vindicator"),
    PHANTOM(0x3a, "phantom"),
    RAVAGER(0x3b, "ravager"),
    EVOKER(0x68, "evocation_illager"),
    VEX(0x69, "vex"),
    DROWNED(0x6e, "drowned"),
    PILLAGER(0x72, "pillager"),
    ZOMBIE_VILLAGER_V6(0x74, "zombie_villager_v2"),
    PIGLIN(0x7b, "piglin"),
    HOGLIN(0x7c, "hoglin"),
    ZOGLIN(0x7e, "zoglin"),
    PIGLIN_BRUTE(0x7f, "piglin_brute"),

    // Passive and neutral Monsters
    CHICKEN(0xa, "chicken"),
    COW(0xb, "cow"),
    PIG(0xc, "pig"),
    SHEEP(0xd, "sheep"),
    WOLF(0xe, "wolf"),
    VILLAGER(0xf, "villager"),
    MOOSHROOM(0x10, "mooshroom"),
    SQUID(0x11, "squid"),
    RABBIT(0x12, "rabbit"),
    BAT(0x13, "bat"),
    IRON_GOLEM(0x14, "iron_golem"),
    SNOW_GOLEM(0x15, "snow_golem"),
    OCELOT(0x16, "ocelot"),
    HORSE(0x17, "horse"),
    DONKEY(0x18, "donkey"),
    MULE(0x19, "mule"),
    SKELETON_HORSE(0x1a, "skeleton_horse"),
    ZOMBIE_HORSE(0x1b, "zombie_horse"),
    POLAR_BEAR(0x1c, "polar_bear"),
    LLAMA(0x1d, "llama"),
    PARROT(0x1e, "parrot"),
    DOLPHIN(0x1f, "dolphin"),
    TURTLE(0x4a, "turtle"),
    CAT(0x4b, "cat"),
    PUFFERFISH(0x6c, "pufferfish"),
    SALMON(0x6d, "salmon"),
    TROPICAL_FISH(0x6f, "tropicalfish"),
    COD(0x70, "cod"),
    PANDA(0x71, "panda"),
    VILLAGER_V2(0x73, "villager_v2"),
    WANDERING_TRADER(0x76, "wandering_trader"),
    FOX(0x79, "fox"),
    BEE(0x7a, "bee"),
    STRIDER(0x7d, "strider"),
    GOAT(0x80, "goat"),

    // Other
    PLAYER(0x3f, "player"),
    SHIELD(0x75, "shield");

    private final int networkId;
    private final String identifier;

    EntityType(final int networkId, final String identifier) {
        this.networkId = networkId;
        this.identifier = identifier;
    }

    /**
     * Retrieves the network identifier as number representation of this {@link org.terracottamc.entity.EntityType}
     *
     * @return a fresh network id of this {@link org.terracottamc.entity.EntityType}
     */
    public int getNetworkId() {
        return this.networkId;
    }

    /**
     * Retrieves the {@link java.lang.String} identifier of this {@link org.terracottamc.entity.EntityType}
     *
     * @return a fresh {@link java.lang.String}
     */
    public String getIdentifier() {
        return this.identifier;
    }
}