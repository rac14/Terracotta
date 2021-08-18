package org.terracottamc.entity.player.skin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Kaooot
 * @version 1.0
 */
public class Skin {

    private final String fullSkinId = UUID.randomUUID().toString();

    private final List<SkinAnimation> skinAnimations = new ArrayList<>();
    private final List<PersonaPiece> personaPieces = new ArrayList<>();
    private final List<PersonaPieceTint> personaPieceTints = new ArrayList<>();

    private String skinId;
    private String playFabId = "";
    private String resourcePatch;
    private String geometryData;
    private String animationData;
    private String capeId;
    private String skinColor = "#0";
    private String armSize = "wide";

    private SkinImage skinData;
    private SkinImage capeData;

    private boolean premium;
    private boolean persona;
    private boolean capeOnClassic;
    private boolean trusted = false;

    /**
     * Retrieves the full skin id of this {@link Skin}
     *
     * @return a fresh {@link String}
     */
    public String getFullSkinId() {
        return this.fullSkinId;
    }

    /**
     * Retrieves all {@link SkinAnimation} of this {@link Skin}
     *
     * @return a fresh {@link List}
     */
    public List<SkinAnimation> getSkinAnimations() {
        return this.skinAnimations;
    }

    /**
     * Retrieves all {@link PersonaPiece} of this {@link Skin}
     *
     * @return a fresh {@link List}
     */
    public List<PersonaPiece> getPersonaPieces() {
        return this.personaPieces;
    }

    /**
     * Retrieves all {@link PersonaPieceTint} of this {@link Skin}
     *
     * @return a fresh {@link PersonaPieceTint}
     */
    public List<PersonaPieceTint> getPersonaPieceTints() {
        return this.personaPieceTints;
    }

    /**
     * Retrieves the skin id of this {@link Skin}
     *
     * @return a fresh {@link String}
     */
    public String getSkinId() {
        return this.skinId;
    }

    /**
     * Retrieves the play fab id of this {@link Skin}
     *
     * @return a fresh {@link String}
     */
    public String getPlayFabId() {
        return this.playFabId;
    }

    /**
     * Retrieves the resource patch of this {@link Skin}
     *
     * @return a fresh {@link String}
     */
    public String getResourcePatch() {
        return this.resourcePatch;
    }

    /**
     * Retrieves the geometry data of this {@link Skin}
     *
     * @return a fresh {@link String}
     */
    public String getGeometryData() {
        return this.geometryData;
    }

    /**
     * Retrieves the animation data of this {@link Skin}
     *
     * @return a fresh {@link String}
     */
    public String getAnimationData() {
        return this.animationData;
    }

    /**
     * Retrieves the cape id of this {@link Skin}
     *
     * @return a fresh {@link String}
     */
    public String getCapeId() {
        return this.capeId;
    }

    /**
     * Retrieves the skin color of this {@link Skin}
     *
     * @return a fresh {@link String}
     */
    public String getSkinColor() {
        return this.skinColor;
    }

    /**
     * Retrieves the arm size of this {@link Skin}
     *
     * @return a fresh {@link String}
     */
    public String getArmSize() {
        return this.armSize;
    }

    /**
     * Retrieves the skin data as {@link SkinImage} of this {@link Skin}
     *
     * @return a fresh {@link SkinImage}
     */
    public SkinImage getSkinData() {
        return this.skinData;
    }

    /**
     * Retrieves the cape data as {@link SkinImage} of this {@link Skin}
     *
     * @return a fresh {@link SkinImage}
     */
    public SkinImage getCapeData() {
        return this.capeData;
    }

    /**
     * Proofs whether this {@link Skin} is a premium skin
     *
     * @return whether this {@link Skin} is premium
     */
    public boolean isPremium() {
        return this.premium;
    }

    /**
     * Proofs whether this {@link Skin} is a persona skin
     *
     * @return whether this {@link Skin} is persona
     */
    public boolean isPersona() {
        return this.persona;
    }

    /**
     * Proofs whether this {@link Skin} is a cape on classic skin
     *
     * @return whether this {@link Skin} is cape on classic
     */
    public boolean isCapeOnClassic() {
        return this.capeOnClassic;
    }

    /**
     * Proofs whether this {@link Skin} is a trusted skin
     *
     * @return whether this {@link Skin} is trusted
     */
    public boolean isTrusted() {
        return this.trusted;
    }

    /**
     * Modifies the skin id of this {@link Skin}
     *
     * @param skinId which should be modified
     */
    public void setSkinId(final String skinId) {
        this.skinId = skinId;
    }

    /**
     * Modifies the play fab id of this {@link Skin}
     *
     * @param playFabId which should be modified
     */
    public void setPlayFabId(final String playFabId) {
        this.playFabId = playFabId;
    }

    /**
     * Modifies the resource patch of this {@link Skin}
     *
     * @param resourcePatch which should be modified
     */
    public void setResourcePatch(final String resourcePatch) {
        this.resourcePatch = resourcePatch;
    }

    /**
     * Modifies the geometry data of this {@link Skin}
     *
     * @param geometryData which should be modified
     */
    public void setGeometryData(final String geometryData) {
        this.geometryData = geometryData;
    }

    /**
     * Modifies the animation data of this {@link Skin}
     *
     * @param animationData which should be modified
     */
    public void setAnimationData(final String animationData) {
        this.animationData = animationData;
    }

    /**
     * Modifies the cape id of this {@link Skin}
     *
     * @param capeId which should be modified
     */
    public void setCapeId(final String capeId) {
        this.capeId = capeId;
    }

    /**
     * Modifies the skin color of this {@link Skin}
     *
     * @param skinColor which should be modified
     */
    public void setSkinColor(final String skinColor) {
        this.skinColor = skinColor;
    }

    /**
     * Modifies the arm size of this {@link Skin}
     *
     * @param armSize which should be modified
     */
    public void setArmSize(final String armSize) {
        this.armSize = armSize;
    }

    /**
     * Modifies the skin data of this {@link Skin}
     *
     * @param skinData which should be modified
     */
    public void setSkinData(final SkinImage skinData) {
        this.skinData = skinData;
    }

    /**
     * Modifies the cape data of this {@link Skin}
     *
     * @param capeData which should be modified
     */
    public void setCapeData(final SkinImage capeData) {
        this.capeData = capeData;
    }

    /**
     * Modifies whether this {@link Skin} is a premium skin
     *
     * @param premium which should be modified
     */
    public void setPremium(final boolean premium) {
        this.premium = premium;
    }

    /**
     * Modifies whether this {@link Skin} is a persona skin
     *
     * @param persona which should be modified
     */
    public void setPersona(final boolean persona) {
        this.persona = persona;
    }

    /**
     * Modifies whether this {@link Skin} is a cape on classic skin
     *
     * @param capeOnClassic which should be modified
     */
    public void setCapeOnClassic(final boolean capeOnClassic) {
        this.capeOnClassic = capeOnClassic;
    }

    /**
     * Modifies whether this {@link Skin} is a trusted skin
     *
     * @param trusted which should be modified
     */
    public void setTrusted(final boolean trusted) {
        this.trusted = trusted;
    }
}