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

        public final Builder id(final int id) {
            this.id = id;
            return this;
        }

        public final Builder creator(final String creator) {
            this.creator = creator;
            return this;
        }

        public final Builder name(final String name) {
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

        public final Builder registrationDate(final LocalDateTime registrationDate) {
            this.registrationDate = registrationDate;
            return this;
        }

        public final Builder downloadFee(final int downloadFee) {
            this.downloadFee = downloadFee;
            return this;
        }

        public final Builder release(final int release) {
            this.release = release;
            return this;
        }

        public final Builder latestRelease(final int latestRelease) {
            this.latestRelease = latestRelease;
            return this;
        }

        public final Builder likes(final int likes) {
            this.likes = likes;
            return this;
        }

        public final Builder dislikes(final int dislikes) {
            this.dislikes = dislikes;
            return this;
        }

        public final Builder downloadCount(final int downloadCount) {
            this.downloadCount = downloadCount;
            return this;
        }

        public RegisteredMap build() {
            return new RegisteredMap(id, creator, name, mode.immutable(), tags.immutable(), meta.immutable(), registrationDate, downloadFee,
                release, latestRelease, likes, dislikes, downloadCount);
        }

    }

    public void toBuffer(final PacketBuf buf) {
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

    public static RegisteredMap fromBuffer(final PacketBuf buf) {
        final int id = buf.readInt();
        final String creator = buf.readString();
        final String name = buf.readString();
        final ImmutableFlags<GameMode> mode = GameMode.MANAGER.newImmutable(buf.readUnsignedShort());
        final ImmutableFlags<MapMeta> meta = MapMeta.MANAGER.newImmutable(buf.readByte());
        final ImmutableFlags<MapTag> tags = MapTag.MANAGER.newImmutable(buf.readByte());
        final LocalDateTime registrationDate = buf.readDateTime();
        final int downloadFee = buf.readInt();
        final int release = buf.readInt();
        final int latestRelease = buf.readInt();
        final int likes = buf.readInt();
        final int dislikes = buf.readInt();
        final int downloadCount = buf.readInt();
        return new RegisteredMap(id, creator, name, mode, tags, meta, registrationDate, downloadFee, release, latestRelease, likes,
            dislikes, downloadCount);
    }

    public static Tuple<RegisteredMap, BufferedImage> fromFile(final File file) throws IOException {
        try (PacketBuf buf = new PacketBuf(Files.readAllBytes(file.toPath()))) {
            final int formatVersion = buf.readInt();
            final int id = buf.readInt();
            final String name = buf.readString();
            final String creator = buf.readString();
            final LocalDateTime registrationDate = buf.readDateTime();
            final ImmutableFlags<GameMode> mode = GameMode.MANAGER
                .newImmutable(formatVersion > 2 ? buf.readUnsignedShort() : buf.readUnsignedByte());
            final Flags<MapMeta> meta = MapMeta.MANAGER.newMutable();
            meta.set(MapMeta.ALLOWS_CLAN_MATCHES, buf.readBoolean());
            meta.set(MapMeta.OFFICIAL, formatVersion >= 2 && buf.readBoolean());
            return Tuple.of(new RegisteredMap(id, creator, name, mode, MapTag.MANAGER.newImmutable(), meta.immutable(), registrationDate, 0,
                0, 0, 0, 0, 0), buf.readImage());
        }
    }

}
