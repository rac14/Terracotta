package org.terracottamc.network.packet;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.terracottamc.entity.player.info.DeviceInfo;
import org.terracottamc.entity.player.info.DeviceOs;
import org.terracottamc.entity.player.info.GUIScale;
import org.terracottamc.entity.player.info.UIProfile;
import org.terracottamc.entity.player.skin.PersonaPiece;
import org.terracottamc.entity.player.skin.PersonaPieceTint;
import org.terracottamc.entity.player.skin.Skin;
import org.terracottamc.entity.player.skin.SkinAnimation;
import org.terracottamc.entity.player.skin.SkinImage;
import org.terracottamc.util.BinaryStream;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author Kaooot
 * @version 1.0
 */
public class LoginPacket extends Packet {

    private int protocolVersion;

    private String username;
    private String xboxId;
    private UUID uuid;
    private DeviceInfo deviceInfo;
    private String languageCode;
    private String gameVersion;
    private Skin skin;

    @Override
    public int getPacketId() {
        return Protocol.LOGIN_PACKET;
    }

    @Override
    public void deserialize() {
        super.deserialize();

        this.protocolVersion = this.readInt();

        final BinaryStream binaryStream = new BinaryStream(this.getBuffer());

        this.setBuffer(this.readBytes(binaryStream.readUnsignedVarInt()));

        final String chainToken = this.readString();
        final Gson gson = new Gson();

        final Map<?, ?> chainMap = gson.fromJson(chainToken, Map.class);
        final List<String> chains = new ArrayList<>();

        for (final Object object : ((List<?>) chainMap.get("chain"))) {
            chains.add(object.toString());
        }

        // ExtraData
        for (final String objectData : chains) {
            final String chainJson = this.readToken(objectData);

            final JsonObject chainData = gson.fromJson(chainJson, JsonObject.class);

            if (chainData != null) {
                if (chainData.has("extraData")) {
                    final JsonObject extraData = chainData.get("extraData").getAsJsonObject();

                    if (extraData.has("displayName")) {
                        this.username = extraData.get("displayName").getAsString();
                    }

                    if (extraData.has("XUID")) {
                        this.xboxId = extraData.get("XUID").getAsString();
                    }

                    if (extraData.has("identity")) {
                        this.uuid = UUID.fromString(extraData.get("identity").getAsString());
                    }
                }
            }
        }

        final String receivedSkinToken = this.readString();
        final String skinToken = this.readToken(receivedSkinToken);
        final JsonObject skinInfo = gson.fromJson(skinToken, JsonObject.class);

        // ChainData
        if (skinInfo.has("DeviceModel") && skinInfo.has("DeviceId") &&
                skinInfo.has("ClientRandomId") && skinInfo.has("DeviceOS") &&
                skinInfo.has("GuiScale") && skinInfo.has("UIProfile")) {
            final String deviceName = skinInfo.get("DeviceModel").getAsString();
            final String deviceId = skinInfo.get("DeviceId").getAsString();
            final long clientId = skinInfo.get("ClientRandomId").getAsLong();
            final int deviceOsId = skinInfo.get("DeviceOS").getAsInt();
            final int gameUIScaleId = skinInfo.get("GuiScale").getAsInt();
            final int uiProfileId = skinInfo.get("UIProfile").getAsInt();

            final DeviceOs deviceOs = DeviceOs.retrieveDeviceOsById(deviceOsId);
            final GUIScale gameUIScale = GUIScale.retrieveGUIScaleById(gameUIScaleId);
            final UIProfile uiProfile = UIProfile.retrieveUIProfileById(uiProfileId);

            this.deviceInfo = new DeviceInfo(deviceName, deviceId, clientId, deviceOs, gameUIScale, uiProfile);
        }

        if (skinInfo.has("LanguageCode")) {
            this.languageCode = skinInfo.get("LanguageCode").getAsString();
        }

        if (skinInfo.has("GameVersion")) {
            this.gameVersion = skinInfo.get("GameVersion").getAsString();
        }

        // SkinData
        this.skin = new Skin();

        if (skinInfo.has("AnimatedImageData")) {
            final JsonArray skinAnimatedData = skinInfo.getAsJsonArray("AnimatedImageData");

            for (final JsonElement animationDataElement : skinAnimatedData) {
                final JsonObject animationData = animationDataElement.getAsJsonObject();

                final byte[] skinImageData = Base64.getDecoder().decode(animationData.get("Image").getAsString());
                final int skinImageWidth = animationData.get("ImageWidth").getAsInt();
                final int skinImageHeight = animationData.get("ImageHeight").getAsInt();
                final int type = animationData.get("Type").getAsInt();
                final float frames = animationData.get("Frames").getAsFloat();
                final int expression = animationData.get("AnimationExpression").getAsInt();

                final SkinAnimation skinAnimation = new SkinAnimation(new SkinImage(skinImageWidth, skinImageHeight,
                        skinImageData), type, frames, expression);

                this.skin.getSkinAnimations().add(skinAnimation);
            }
        }

        if (skinInfo.has("PersonaPieces")) {
            final JsonArray personaPieces = skinInfo.getAsJsonArray("PersonaPieces");

            for (final JsonElement personaPieceElement : personaPieces) {
                final JsonObject personaPieceData = personaPieceElement.getAsJsonObject();

                final String pieceId = personaPieceData.get("PieceId").getAsString();
                final String pieceType = personaPieceData.get("PieceType").getAsString();
                final String packId = personaPieceData.get("PackId").getAsString();
                final String productId = personaPieceData.get("ProductId").getAsString();
                final boolean isDefault = personaPieceData.get("IsDefault").getAsBoolean();

                final PersonaPiece personaPiece = new PersonaPiece(pieceId, pieceType, packId, productId, isDefault);

                this.skin.getPersonaPieces().add(personaPiece);
            }
        }

        if (skinInfo.has("PersonaPieceTints")) {
            final JsonArray personaPieceTints = skinInfo.getAsJsonArray("PersonaPieceTints");

            for (final JsonElement personaPieceTintElement : personaPieceTints) {
                final JsonObject personaPieceTintData = personaPieceTintElement.getAsJsonObject();

                final String pieceType = personaPieceTintData.get("PieceType").getAsString();
                final List<String> colors = new ArrayList<>();

                for (final JsonElement colorElement : personaPieceTintData.get("Colors").getAsJsonArray()) {
                    colors.add(colorElement.getAsString());
                }

                final PersonaPieceTint personaPieceTint = new PersonaPieceTint(pieceType, colors);

                this.skin.getPersonaPieceTints().add(personaPieceTint);
            }
        }

        if (skinInfo.has("SkinId")) {
            this.skin.setSkinId(skinInfo.get("SkinId").getAsString());
        }

        if (skinInfo.has("PlayFabID")) {
            this.skin.setPlayFabId(skinInfo.get("PlayFabID").getAsString());
        }

        if (skinInfo.has("SkinResourcePatch")) {
            final byte[] resourcePatch = Base64.getDecoder().decode(skinInfo.get("SkinResourcePatch").getAsString());

            this.skin.setResourcePatch(new String(resourcePatch));
        }

        if (skinInfo.has("SkinGeometryData")) {
            final byte[] geometryData = Base64.getDecoder().decode(skinInfo.get("SkinGeometryData").getAsString());

            this.skin.setGeometryData(new String(geometryData));
        }

        if (skinInfo.has("AnimationData")) {
            final byte[] animationData = Base64.getDecoder().decode(skinInfo.get("AnimationData").getAsString());

            this.skin.setAnimationData(new String(animationData));
        }

        if (skinInfo.has("CapeId")) {
            this.skin.setCapeId(skinInfo.get("CapeId").getAsString());
        }

        if (skinInfo.has("SkinColor")) {
            this.skin.setSkinColor(skinInfo.get("SkinColor").getAsString());
        }

        if (skinInfo.has("ArmSize")) {
            this.skin.setArmSize(skinInfo.get("ArmSize").getAsString());
        }

        this.skin.setSkinData(this.retrieveSkinImage(skinInfo, "Skin"));
        this.skin.setCapeData(this.retrieveSkinImage(skinInfo, "Cape"));

        if (skinInfo.has("PremiumSkin")) {
            this.skin.setPremium(skinInfo.get("PremiumSkin").getAsBoolean());
        }

        if (skinInfo.has("PersonaSkin")) {
            this.skin.setPersona(skinInfo.get("PersonaSkin").getAsBoolean());
        }

        if (skinInfo.has("CapeOnClassicSkin")) {
            this.skin.setCapeOnClassic(skinInfo.get("CapeOnClassicSkin").getAsBoolean());
        }
    }

    @Override
    public String readString() {
        final byte[] bytes = new byte[this.readIntLE()];

        this.readBytes(bytes);

        return new String(bytes, StandardCharsets.UTF_8);
    }

    /**
     * Retrieves the read protocol version of this {@link org.terracottamc.network.packet.LoginPacket}
     *
     * @return a fresh protocol version
     */
    public int getProtocolVersion() {
        return this.protocolVersion;
    }

    /**
     * Retrieves the username of this {@link org.terracottamc.network.packet.LoginPacket}
     *
     * @return a fresh {@link java.lang.String}
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Retrieves the xbox account id of this {@link org.terracottamc.network.packet.LoginPacket}
     *
     * @return a fresh {@link java.lang.String}
     */
    public String getXboxId() {
        return this.xboxId;
    }

    /**
     * Retrieves the {@link java.util.UUID} of this {@link org.terracottamc.network.packet.LoginPacket}
     *
     * @return a fresh {@link java.util.UUID}
     */
    public UUID getUuid() {
        return this.uuid;
    }

    /**
     * Retrieves the {@link org.terracottamc.entity.player.info.DeviceInfo}
     * of this {@link org.terracottamc.network.packet.LoginPacket}
     *
     * @return a fresh {@link org.terracottamc.entity.player.info.DeviceInfo}
     */
    public DeviceInfo getDeviceInfo() {
        return this.deviceInfo;
    }

    /**
     * Retrieves the language code of this {@link org.terracottamc.network.packet.LoginPacket}
     *
     * @return a fresh {@link java.lang.String}
     */
    public String getLanguageCode() {
        return this.languageCode;
    }

    /**
     * Retrieves the game version of this {@link org.terracottamc.network.packet.LoginPacket}
     *
     * @return a fresh {@link java.lang.String}
     */
    public String getGameVersion() {
        return this.gameVersion;
    }

    /**
     * Returns the {@link org.terracottamc.entity.player.skin.Skin}
     * of this {@link org.terracottamc.network.packet.LoginPacket}
     *
     * @return a fresh {@link org.terracottamc.entity.player.skin.Skin}
     */
    public Skin getSkin() {
        return this.skin;
    }

    /**
     * Creates a {@link org.terracottamc.entity.player.skin.SkinImage}
     * optimized for the skin data and cape data of this {@link org.terracottamc.network.packet.LoginPacket}
     *
     * @param skinInfo which represents the skin {@link java.util.Map} to work with
     * @param dataType that is the type which can be specified for example: Cape and Skin
     *
     * @return a fresh {@link org.terracottamc.entity.player.skin.SkinImage}
     */
    private SkinImage retrieveSkinImage(final JsonObject skinInfo, final String dataType) {
        if (skinInfo.has(dataType + "Data")) {
            final byte[] skinImageData = Base64.getDecoder().decode(skinInfo.get(dataType + "Data").getAsString());

            if (skinInfo.has(dataType + "ImageWidth") && skinInfo.has(dataType + "ImageHeight")) {
                final int width = skinInfo.get(dataType + "ImageWidth").getAsInt();
                final int height = skinInfo.get(dataType + "ImageHeight").getAsInt();

                return new SkinImage(width, height, skinImageData);
            } else {
                return SkinImage.getImage(skinImageData);
            }
        }

        return new SkinImage(0, 0, new byte[0]);
    }

    /**
     * Reads a {@link java.lang.String} token and validates it by their components
     *
     * @param token which should be read and validated
     *
     * @return a fresh {@link java.lang.String} that represents the deserialized token data
     */
    private String readToken(final String token) {
        final String[] elements = token.split("\\.");

        if (elements.length < 2) {
            return null;
        }

        return new String(Base64.getDecoder().decode(elements[1]), StandardCharsets.UTF_8);
    }
}