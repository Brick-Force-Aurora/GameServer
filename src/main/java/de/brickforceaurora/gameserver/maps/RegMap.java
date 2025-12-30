package de.brickforceaurora.gameserver.maps;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class RegMap {

    private static final byte TAG_ABUSE = 16;

    private final int latestFileVer = 3;
    private int ver = 3;

    private int map = -1;
    private String developer = "";
    private String alias = "";
    private LocalDateTime regDate;

    private int modeMask; // ushort
    private int release;
    private int latestRelease;

    public int Rank;
    public int RankChg;

    public byte tagMask;

    private boolean clanMatchable;
    private boolean officialMap;
    private boolean blocked;

    private int likes;
    private int disLikes;
    private int downloadCount;
    private int downloadFee;

    public RegMap() {}

    public RegMap(
            int map,
            String developer,
            String alias,
            LocalDateTime regDate,
            int modeMask,
            boolean clanMatchable,
            boolean officialMap,
            int likes,
            int disLikes,
            int downloadCount,
            int downloadFee,
            int release,
            int latestRelease,
            byte tagMask,
            boolean blocked
    ) {
        this.map = map;
        this.developer = developer;
        this.alias = alias;
        this.regDate = regDate;
        this.modeMask = modeMask & 0xFFFF;
        this.clanMatchable = clanMatchable;
        this.officialMap = officialMap;
        this.likes = likes;
        this.disLikes = disLikes;
        this.downloadCount = downloadCount;
        this.downloadFee = downloadFee;
        this.release = release;
        this.latestRelease = latestRelease;
        this.tagMask = tagMask;
        this.blocked = blocked;
    }

    /* =======================
       Properties
       ======================= */

    public int getMap() { return map; }
    public String getDeveloper() { return developer; }
    public void setDeveloper(String v) { developer = v; }

    public String getAlias() { return alias; }
    public void setAlias(String v) { alias = v; }

    public String getVersion() {
        return "(" + release + "/" + latestRelease + ")";
    }

    public LocalDateTime getRegisteredDate() {
        return regDate;
    }

    public int getModeMask() { return modeMask; }
    public void setModeMask(int v) { modeMask = v & 0xFFFF; }

    public int getRelease() { return release; }
    public void setRelease(int v) { release = v; }

    public int getLatestRelease() { return latestRelease; }
    public void setLatestRelease(int v) {
        if (v > latestRelease) latestRelease = v;
    }

    public boolean isLatest() {
        return release == latestRelease;
    }

    public boolean isOfficialMap() { return officialMap; }
    public void setOfficialMap(boolean v) { officialMap = v; }

    public boolean isBlocked() { return blocked; }
    public void setBlocked(boolean v) { blocked = v; }

    public int getLikes() { return likes; }
    public int getDisLikes() { return disLikes; }
    public int getDownloadCount() { return downloadCount; }
    public int getDownloadFee() { return downloadFee; }

    public boolean isAbuseMap() {
        return (tagMask & TAG_ABUSE) != 0;
    }

    /* =======================
       Rank
       ======================= */

    public void setRank(int rk, int rkchg) {
        Rank = rk;
        RankChg = rkchg;
    }

    /* =======================
       File IO (C# compatible)
       ======================= */

    public boolean save(File file) {
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(file))) {

            writeIntLE(out, ver);
            writeIntLE(out, map);
            writeString(out, alias);
            writeString(out, developer);

            writeIntLE(out, regDate.getYear());
            out.writeByte(regDate.getMonthValue());
            out.writeByte(regDate.getDayOfMonth());
            out.writeByte(regDate.getHour());
            out.writeByte(regDate.getMinute());
            out.writeByte(regDate.getSecond());

            writeShortLE(out, modeMask);
            out.writeBoolean(clanMatchable);
            out.writeBoolean(officialMap);

            // thumbnail not implemented → same as C# when null
            writeIntLE(out, 0);

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean load(File file) {
        try (DataInputStream in = new DataInputStream(new FileInputStream(file))) {

            ver = readIntLE(in);
            map = readIntLE(in);
            alias = readString(in);
            developer = readString(in);

            int year = readIntLE(in);
            int month = in.readByte();
            int day = in.readByte();
            int hour = in.readByte();
            int min = in.readByte();
            int sec = in.readByte();
            regDate = LocalDateTime.of(year, month, day, hour, min, sec);

            if (ver > 2)
                modeMask = readUnsignedShortLE(in);
            else
                modeMask = in.readUnsignedByte();

            clanMatchable = in.readBoolean();
            officialMap = ver >= 2 && in.readBoolean();

            int thumbSize = readIntLE(in);
            if (thumbSize > 0) {
                in.skipBytes(thumbSize); // ignore image
            }

            if (ver < latestFileVer) {
                ver = latestFileVer;
                save(file);
            }

            return true;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* =======================
       Binary helpers (LE!)
       ======================= */

    private static void writeIntLE(DataOutputStream out, int v) throws IOException {
        out.writeByte(v & 0xFF);
        out.writeByte((v >>> 8) & 0xFF);
        out.writeByte((v >>> 16) & 0xFF);
        out.writeByte((v >>> 24) & 0xFF);
    }

    private static void writeShortLE(DataOutputStream out, int v) throws IOException {
        out.writeByte(v & 0xFF);
        out.writeByte((v >>> 8) & 0xFF);
    }

    private static int readIntLE(DataInputStream in) throws IOException {
        return (in.readUnsignedByte())
                | (in.readUnsignedByte() << 8)
                | (in.readUnsignedByte() << 16)
                | (in.readUnsignedByte() << 24);
    }

    private static int readUnsignedShortLE(DataInputStream in) throws IOException {
        return in.readUnsignedByte() | (in.readUnsignedByte() << 8);
    }

    private static void writeString(DataOutputStream out, String s) throws IOException {
        byte[] data = s.getBytes(StandardCharsets.UTF_8);
        writeIntLE(out, data.length);
        out.write(data);
    }

    private static String readString(DataInputStream in) throws IOException {
        int len = readIntLE(in);
        byte[] data = new byte[len];
        in.readFully(data);
        return new String(data, StandardCharsets.UTF_8);
    }
}

