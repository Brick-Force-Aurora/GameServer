package de.brickforceaurora.server.net.protocol.data;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;

import de.brickforceaurora.server.net.protocol.PacketBuf;
import de.brickforceaurora.server.util.flag.Flags;
import de.brickforceaurora.server.util.flag.IFlags;
import de.brickforceaurora.server.util.flag.ImmutableFlags;
import me.lauriichan.snowframe.util.Tuple;

public record RegisteredMap(int id, String creator, String name, ImmutableFlags<GameMode> mode, ImmutableFlags<MapTag> tags,
    ImmutableFlags<MapMeta> meta, LocalDateTime registrationDate, int downloadFee, int release, int latestRelease, int likes, int dislikes,
    int downloadCount) {

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private final IFlags.Builder<Builder, GameMode> mode = GameMode.MANAGER.newBuilder(this);
        private final IFlags.Builder<Builder, MapMeta> meta = MapMeta.MANAGER.newBuilder(this);
        private final IFlags.Builder<Builder, MapTag> tags = MapTag.MANAGER.newBuilder(this);

        private int id, downloadFee, release, latestRelease, likes, dislikes, downloadCount;
        private String creator, name;
        private LocalDateTime registrationDate;

        private Builder() {}

        public final Builder id(int id) {
            this.id = id;
            return this;
        }

        public final Builder creator(String creator) {
            this.creator = creator;
            return this;
        }

        public final Builder name(String name) {
            this.name = name;
            return this;
        }

        public final IFlags.Builder<Builder, GameMode> mode() {
            return mode;
        }

        public final IFlags.Builder<Builder, MapTag> tags() {
            return tags;
        }

        public final IFlags.Builder<Builder, MapMeta> meta() {
            return meta;
        }

        public final Builder registrationDate(LocalDateTime registrationDate) {
            this.registrationDate = registrationDate;
            return this;
        }

        public final Builder downloadFee(int downloadFee) {
            this.downloadFee = downloadFee;
            return this;
        }

        public final Builder release(int release) {
            this.release = release;
            return this;
        }

        public final Builder latestRelease(int latestRelease) {
            this.latestRelease = latestRelease;
            return this;
        }

        public final Builder likes(int likes) {
            this.likes = likes;
            return this;
        }

        public final Builder dislikes(int dislikes) {
            this.dislikes = dislikes;
            return this;
        }

        public final Builder downloadCount(int downloadCount) {
            this.downloadCount = downloadCount;
            return this;
        }

        public RegisteredMap build() {
            return new RegisteredMap(id, creator, name, mode.immutable(), tags.immutable(), meta.immutable(), registrationDate, downloadFee,
                release, latestRelease, likes, dislikes, downloadCount);
        }

    }

    public void toBuffer(PacketBuf buf) {
        buf.writeInt(id);
        buf.writeString(creator);
        buf.writeString(name);
        buf.writeShort(mode.value());
        buf.writeByte(meta.value());
        buf.writeByte(tags.value());
        buf.writeDateTime(registrationDate);
        buf.writeInt(downloadFee);
        buf.writeInt(release);
        buf.writeInt(latestRelease);
        buf.writeInt(likes);
        buf.writeInt(dislikes);
        buf.writeInt(downloadCount);
    }

    public static RegisteredMap fromBuffer(PacketBuf buf) {
        int id = buf.readInt();
        String creator = buf.readString();
        String name = buf.readString();
        ImmutableFlags<GameMode> mode = GameMode.MANAGER.newImmutable(buf.readUnsignedShort());
        ImmutableFlags<MapMeta> meta = MapMeta.MANAGER.newImmutable(buf.readByte());
        ImmutableFlags<MapTag> tags = MapTag.MANAGER.newImmutable(buf.readByte());
        LocalDateTime registrationDate = buf.readDateTime();
        int downloadFee = buf.readInt();
        int release = buf.readInt();
        int latestRelease = buf.readInt();
        int likes = buf.readInt();
        int dislikes = buf.readInt();
        int downloadCount = buf.readInt();
        return new RegisteredMap(id, creator, name, mode, tags, meta, registrationDate, downloadFee, release, latestRelease, likes,
            dislikes, downloadCount);
    }

    public static Tuple<RegisteredMap, BufferedImage> fromFile(File file) throws IOException {
        try (PacketBuf buf = new PacketBuf(Files.readAllBytes(file.toPath()))) {
            int formatVersion = buf.readInt();
            int id = buf.readInt();
            String name = buf.readString();
            String creator = buf.readString();
            LocalDateTime registrationDate = buf.readDateTime();
            ImmutableFlags<GameMode> mode = GameMode.MANAGER
                .newImmutable(formatVersion > 2 ? buf.readUnsignedShort() : buf.readUnsignedByte());
            Flags<MapMeta> meta = MapMeta.MANAGER.newMutable();
            meta.set(MapMeta.ALLOWS_CLAN_MATCHES, buf.readBoolean());
            meta.set(MapMeta.OFFICIAL, formatVersion >= 2 && buf.readBoolean());
            return Tuple.of(new RegisteredMap(id, creator, name, mode, MapTag.MANAGER.newImmutable(), meta.immutable(), registrationDate, 0,
                0, 0, 0, 0, 0), buf.readImage());
        }
    }

}
