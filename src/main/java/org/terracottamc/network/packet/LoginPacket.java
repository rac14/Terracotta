package org.terracottamc.network.packet;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import lombok.ToString;
import org.terracottamc.entity.player.info.DeviceInfo;
import org.terracottamc.entity.player.info.DeviceOs;
import org.terracottamc.entity.player.info.GUIScale;
import org.terracottamc.entity.player.info.InputHardware;
import org.terracottamc.entity.player.info.UIProfile;
import org.terracottamc.entity.player.skin.PersonaPiece;
import org.terracottamc.entity.player.skin.PersonaPieceTint;
import org.terracottamc.entity.player.skin.Skin;
import org.terracottamc.entity.player.skin.SkinAnimation;
import org.terracottamc.entity.player.skin.SkinImage;
import org.terracottamc.network.security.MojangSecurityDecryptionHelper;
import org.terracottamc.network.security.jwt.Jwt;
import org.terracottamc.network.security.jwt.JwtEncryptionAlgorithm;
import org.terracottamc.network.security.jwt.JwtHeader;
import org.terracottamc.network.security.jwt.JwtSignature;
import org.terracottamc.server.Server;
import org.terracottamc.util.BinaryStream;

import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.security.PublicKey;
import java.security.interfaces.ECPublicKey;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

/**
 * @author Kaooot
 * @version 1.0
 */
@ToString
public class LoginPacket extends Packet {

    private int protocolVersion;

    private String username;
    private String xboxId;
    private UUID uuid;
    private DeviceInfo deviceInfo;
    private String languageCode;
    private String gameVersion;
    private String platformOfflineId;
    private String platformOnlineId;
    private String selfSignedId;
    private String serverAddress;
    private String thirdPartyName;
    private boolean thirdPartyNameOnly;
    private Skin skin;
    private ECPublicKey clientPublicKey;

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

        final String jsonWebToken = this.readString();
        final JsonObject jwtData = new JsonParser()
                .parse(new JsonReader(new StringReader(jsonWebToken))).getAsJsonObject();
        final JsonArray jwtChain = jwtData.getAsJsonArray("chain");

        Jwt jwt = null;
        JsonObject jwtPayload = null;

        for (final JsonElement rawToken : jwtChain) {
            jwt = Jwt.read(rawToken.getAsString());

            if (jwt != null) {
                final JwtHeader jwtHeader = jwt.getJwtHeader();

                jwtPayload = jwt.getJwtPayload();

                // verifying the jwt data
                if (jwtHeader.isValid() && jwtPayload.has("iss") && jwtPayload.get("iss").getAsString()
                        .equalsIgnoreCase("Mojang") && jwtPayload.has("identityPublicKey") &&
                        jwtPayload.has("extraData") &&
                        jwtPayload.get("exp").getAsLong() < System.currentTimeMillis()) {
                    this.clientPublicKey = Server.getInstance().getMojangSecurityDecryptionHelper()
                            .generateMojangIdentityPublicKey(jwtPayload.get("identityPublicKey").getAsString());

                    final PublicKey publicKey = Server.getInstance().getMojangSecurityKeyFactory()
                            .generatePublicKey(jwtHeader.getHeaderData().get("x5u").getAsString());
                    final JwtEncryptionAlgorithm jwtEncryptionAlgorithm = jwtHeader.getJwtEncryptionAlgorithm();
                    final JwtSignature jwtSignature = jwtEncryptionAlgorithm.getJwtSignature();

                    if (jwtSignature.isValid(publicKey, jwt.getSignatureData(), jwt.getSignatureDigest())) {
                        break;
                    }
                }
            }
        }

        if (jwt != null && jwtPayload != null && this.clientPublicKey != null) {
            final MojangSecurityDecryptionHelper mojangSecurityDecryptionHelper = Server.getInstance()
                    .getMojangSecurityDecryptionHelper();

            if (mojangSecurityDecryptionHelper.getMojangRootKey() != null) {
                // ExtraData
                final JsonObject extraData = jwtPayload.getAsJsonObject("extraData");

                if (extraData.has("XUID")) {
                    this.xboxId = extraData.get("XUID").getAsString();
                }

                if (extraData.has("identity")) {
                    this.uuid = UUID.fromString(extraData.get("identity").getAsString());
                }

                if (extraData.has("displayName")) {
                    this.username = extraData.get("displayName").getAsString();
                }

                final String skinJwtRaw = this.readString();
                final Jwt skinJwt = Jwt.read(skinJwtRaw);

                if (skinJwt != null) {
                    final JwtHeader skinJwtHeader = skinJwt.getJwtHeader();

                    if (skinJwtHeader.isValid()) {
                        final PublicKey publicKey = Server.getInstance().getMojangSecurityKeyFactory()
                                .generatePublicKey(skinJwtHeader.getHeaderData().get("x5u").getAsString());
                        final JwtSignature skinJwtSignature = skinJwt.getJwtHeader().getJwtEncryptionAlgorithm()
                                .getJwtSignature();

                        if (skinJwtSignature.isValid(publicKey, skinJwt.getSignatureData(), skinJwt.getSignatureDigest())) {
                            final JsonObject skinJwtPayload = skinJwt.getJwtPayload();

                            // DeviceInfo
                            if (skinJwtPayload.has("ClientRandomId") &&
                                    skinJwtPayload.has("CurrentInputMode") &&
                                    skinJwtPayload.has("DefaultInputMode") &&
                                    skinJwtPayload.has("DeviceId") &&
                                    skinJwtPayload.has("DeviceModel") &&
                                    skinJwtPayload.has("DeviceOS") &&
                                    skinJwtPayload.has("GuiScale") &&
                                    skinJwtPayload.has("UIProfile")) {
                                final String deviceName = skinJwtPayload.get("DeviceModel").getAsString();
                                final String deviceId = skinJwtPayload.get("DeviceId").getAsString();
                                final long clientId = skinJwtPayload.get("ClientRandomId").getAsLong();
                                final int deviceOsId = skinJwtPayload.get("DeviceOS").getAsInt();
                                final int guiScaleId = skinJwtPayload.get("GuiScale").getAsInt();
                                final int uiProfileId = skinJwtPayload.get("UIProfile").getAsInt();
                                final int currentInputHardwareId = skinJwtPayload.get("CurrentInputMode").getAsInt();
                                final int defaultInputHardwareId = skinJwtPayload.get("DefaultInputMode").getAsInt();

                                final DeviceOs deviceOs = DeviceOs.retrieveDeviceOsById(deviceOsId);
                                final GUIScale guiScale = GUIScale.retrieveGUIScaleById(guiScaleId);
                                final UIProfile uiProfile = UIProfile.retrieveUIProfileById(uiProfileId);
                                final InputHardware currentInputHardware = InputHardware
                                        .retrieveInputHardwareById(currentInputHardwareId);
                                final InputHardware defaultInputHardware = InputHardware
                                        .retrieveInputHardwareById(defaultInputHardwareId);

                                this.deviceInfo = new DeviceInfo(deviceName, deviceId, clientId, deviceOs, guiScale,
                                        uiProfile, currentInputHardware, defaultInputHardware);
                            }

                            // LoginChainInfo
                            if (skinJwtPayload.has("GameVersion")) {
                                this.gameVersion = skinJwtPayload.get("GameVersion").getAsString();
                            }

                            if (skinJwtPayload.has("LanguageCode")) {
                                this.languageCode = skinJwtPayload.get("LanguageCode").getAsString();
                            }

                            if (skinJwtPayload.has("PlatformOfflineId")) {
                                this.platformOfflineId = skinJwtPayload.get("PlatformOfflineId").getAsString();
                            }

                            if (skinJwtPayload.has("PlatformOnlineId")) {
                                this.platformOnlineId = skinJwtPayload.get("PlatformOnlineId").getAsString();
                            }

                            if (skinJwtPayload.has("SelfSignedId")) {
                                this.selfSignedId = skinJwtPayload.get("SelfSignedId").getAsString();
                            }

                            if (skinJwtPayload.has("ServerAddress")) {
                                this.serverAddress = skinJwtPayload.get("ServerAddress").getAsString();
                            }

                            if (skinJwtPayload.has("ThirdPartyName")) {
                                this.thirdPartyName = skinJwtPayload.get("ThirdPartyName").getAsString();
                            }

                            if (skinJwtPayload.has("ThirdPartyNameOnly")) {
                                this.thirdPartyNameOnly = skinJwtPayload.get("ThirdPartyNameOnly").getAsBoolean();
                            }

                            // SkinInfo
                            this.skin = new Skin();

                            if (skinJwtPayload.has("AnimatedImageData")) {
                                final JsonArray skinAnimatedData = skinJwtPayload.getAsJsonArray("AnimatedImageData");

                                for (final JsonElement animationDataElement : skinAnimatedData) {
                                    final JsonObject animationData = animationDataElement.getAsJsonObject();

                                    final byte[] skinImageData = Base64.getDecoder()
                                            .decode(animationData.get("Image").getAsString());
                                    final int skinImageWidth = animationData.get("ImageWidth").getAsInt();
                                    final int skinImageHeight = animationData.get("ImageHeight").getAsInt();
                                    final int type = animationData.get("Type").getAsInt();
                                    final float frames = animationData.get("Frames").getAsFloat();
                                    final int expression = animationData.get("AnimationExpression").getAsInt();

                                    final SkinAnimation skinAnimation = new SkinAnimation(new SkinImage(skinImageWidth,
                                            skinImageHeight, skinImageData), type, frames, expression);

                                    this.skin.getSkinAnimations().add(skinAnimation);
                                }
                            }

                            if (skinJwtPayload.has("ArmSize")) {
                                this.skin.setArmSize(skinJwtPayload.get("ArmSize").getAsString());
                            }

                            if (skinJwtPayload.has("CapeData")) {
                                this.skin.setCapeData(this.retrieveSkinImage(skinJwtPayload, "Cape"));
                            }

                            if (skinJwtPayload.has("CapeId")) {
                                this.skin.setCapeId(skinJwtPayload.get("CapeId").getAsString());
                            }

                            if (skinJwtPayload.has("CapeOnClassicSkin")) {
                                this.skin.setCapeOnClassic(skinJwtPayload.get("CapeOnClassicSkin").getAsBoolean());
                            }

                            if (skinJwtPayload.has("PersonaPieces")) {
                                final JsonArray personaPieces = skinJwtPayload.getAsJsonArray("PersonaPieces");

                                for (final JsonElement personaPieceElement : personaPieces) {
                                    final JsonObject personaPieceData = personaPieceElement.getAsJsonObject();

                                    final String pieceId = personaPieceData.get("PieceId").getAsString();
                                    final String pieceType = personaPieceData.get("PieceType").getAsString();
                                    final String packId = personaPieceData.get("PackId").getAsString();
                                    final String productId = personaPieceData.get("ProductId").getAsString();
                                    final boolean isDefault = personaPieceData.get("IsDefault").getAsBoolean();

                                    final PersonaPiece personaPiece = new PersonaPiece(pieceId, pieceType, packId,
                                            productId, isDefault);

                                    this.skin.getPersonaPieces().add(personaPiece);
                                }
                            }

                            if (skinJwtPayload.has("PersonaSkin")) {
                                this.skin.setPersona(skinJwtPayload.get("PersonaSkin").getAsBoolean());
                            }

                            if (skinJwtPayload.has("PieceTintColors")) {
                                final JsonArray personaPieceTints = skinJwtPayload.getAsJsonArray("PieceTintColors");

                                for (final JsonElement personaPieceTintElement : personaPieceTints) {
                                    final JsonObject personaPieceTintData = personaPieceTintElement.getAsJsonObject();

                                    final String pieceType = personaPieceTintData.get("PieceType").getAsString();
                                    final List<String> colors = new ArrayList<>();

                                    for (final JsonElement colorElement : personaPieceTintData.get("Colors")
                                            .getAsJsonArray()) {
                                        colors.add(colorElement.getAsString());
                                    }

                                    final PersonaPieceTint personaPieceTint = new PersonaPieceTint(pieceType, colors);

                                    this.skin.getPersonaPieceTints().add(personaPieceTint);
                                }
                            }

                            if (skinJwtPayload.has("PlayFabId")) {
                                this.skin.setPlayFabId(skinJwtPayload.get("PlayFabId").getAsString());
                            }

                            if (skinJwtPayload.has("PremiumSkin")) {
                                this.skin.setPremium(skinJwtPayload.get("PremiumSkin").getAsBoolean());
                            }

                            if (skinJwtPayload.has("SkinAnimationData")) {
                                final byte[] animationData = Base64.getDecoder()
                                        .decode(skinJwtPayload.get("SkinAnimationData").getAsString());

                                this.skin.setAnimationData(new String(animationData));
                            }

                            if (skinJwtPayload.has("SkinColor")) {
                                this.skin.setSkinColor(skinJwtPayload.get("SkinColor").getAsString());
                            }

                            if (skinJwtPayload.has("SkinData")) {
                                this.skin.setSkinData(this.retrieveSkinImage(skinJwtPayload, "Skin"));
                            }

                            if (skinJwtPayload.has("SkinGeometryData")) {
                                final byte[] geometryData = Base64.getDecoder()
                                        .decode(skinJwtPayload.get("SkinGeometryData").getAsString());

                                this.skin.setGeometryData(new String(geometryData));
                            }

                            if (skinJwtPayload.has("SkinId")) {
                                this.skin.setSkinId(skinJwtPayload.get("SkinId").getAsString());
                            }

                            if (skinJwtPayload.has("SkinResourcePatch")) {
                                final byte[] resourcePatch = Base64.getDecoder()
                                        .decode(skinJwtPayload.get("SkinResourcePatch").getAsString());

                                this.skin.setResourcePatch(new String(resourcePatch));
                            }
                        }
                    }
                }
            }
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
     * Retrieves the platform offline id of this {@link org.terracottamc.network.packet.LoginPacket}
     *
     * @return a fresh {@link java.lang.String}
     */
    public String getPlatformOfflineId() {
        return this.platformOfflineId;
    }

    /**
     * Retrieves the platform online id of this {@link org.terracottamc.network.packet.LoginPacket}
     *
     * @return a fresh {@link java.lang.String}
     */
    public String getPlatformOnlineId() {
        return this.platformOnlineId;
    }

    /**
     * Retrieves the self signed identifier of this {@link org.terracottamc.network.packet.LoginPacket}
     *
     * @return a fresh {@link java.lang.String}
     */
    public String getSelfSignedId() {
        return this.selfSignedId;
    }

    /**
     * Retrieves the server address of this {@link org.terracottamc.network.packet.LoginPacket}
     *
     * @return a fresh {@link java.lang.String}
     */
    public String getServerAddress() {
        return this.serverAddress;
    }

    /**
     * Retrieves the third party name of this {@link org.terracottamc.network.packet.LoginPacket}
     *
     * @return a fresh {@link java.lang.String}
     */
    public String getThirdPartyName() {
        return this.thirdPartyName;
    }

    /**
     * Proofs whether third party name only takes part
     *
     * @return whether third party name only takes part
     */
    public boolean isThirdPartyNameOnly() {
        return this.thirdPartyNameOnly;
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
     * Retrieves the client {@link java.security.interfaces.ECPublicKey}
     * of this {@link org.terracottamc.network.packet.LoginPacket}
     *
     * @return a fresh {@link java.security.interfaces.ECPublicKey}
     */
    public ECPublicKey getClientPublicKey() {
        return this.clientPublicKey;
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
}