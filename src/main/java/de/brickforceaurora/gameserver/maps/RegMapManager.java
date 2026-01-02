package de.brickforceaurora.gameserver.maps;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class RegMapManager {
    /* ===================== CONSTANTS ===================== */

    private static final int PAGE_SIZE = 12;

    private static final int TAG_GOLD_RIBBON = 2;
    private static final int TAG_MEDAL = 4;
    private static final int TAG_GLORY = 8;

    /* ===================== SINGLETON ===================== */

    private static final RegMapManager INSTANCE = new RegMapManager();

    public static RegMapManager getInstance() {
        return INSTANCE;
    }

    /* ===================== FIELDS ===================== */

    private final Map<Integer, RegMap> regMaps = new HashMap<>();
    private final Map<Integer, List<Snipets>> comments = new HashMap<>();

    /* ===================== CONSTRUCTOR ===================== */

    private RegMapManager() {
        regMaps.clear();
    }

    /* ===================== LOAD MAPS ===================== */

    public void loadMaps() {
        Path path = Path.of(System.getProperty("user.dir"), "Cache");

        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            try (DirectoryStream<Path> stream =
                         Files.newDirectoryStream(path, "*.regmap")) {

                for (Path file : stream) {
                    RegMap regMap = new RegMap();
                    if (regMap.load(file.toFile())) {
                        regMaps.put(regMap.getMap(), regMap);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* ===================== BASIC ACCESS ===================== */

    public void registerMap(RegMap map) {
        regMaps.put(map.getMap(), map);
    }

    public RegMap getMap(int id) {
        return regMaps.get(id);
    }

    public void changeAlias(int id, String newAlias) {
        RegMap map = regMaps.get(id);
        if (map != null) {
            map.setAlias(newAlias);
        }
    }

    public void changeFee(int id, int fee) {
        RegMap map = regMaps.get(id);
        if (map != null) {
            map.setDownloadFee(fee);
        }
    }

    public Map<Integer, RegMap> getMaps(){
        return regMaps;
    }

    public List<RegMap> getMapsAsList() {
        return new ArrayList<>(regMaps.values());
    }

    /* ===================== COMMENTS ===================== */

    public Snipets[] getMapDetail(int mapSeq) {
        List<Snipets> list = comments.get(mapSeq);
        return list != null ? list.toArray(new Snipets[0]) : new Snipets[0];
    }

    public void addComment(int mapSeq,
                           int cmtSeq,
                           String nick,
                           String cmt,
                           byte likeOrDislike) {

        comments
                .computeIfAbsent(mapSeq, k -> new ArrayList<>())
                .add(new Snipets(cmtSeq, nick, cmt, likeOrDislike));
    }

    /* ===================== QUERY ===================== */

    private List<RegMap> baseMapQuery(int modeMask, int flag, String filter) {

        return regMaps.values().stream()
                .filter(m -> (m.getModeMask() & modeMask) != 0)
                .filter(m -> {
                    if (filter == null || filter.isEmpty()) return true;

                    String f = filter.replace("%", "").toLowerCase(Locale.ROOT);

                    if (flag == 1)
                        return m.getAlias().toLowerCase(Locale.ROOT).contains(f);
                    if (flag == 2)
                        return m.getDeveloper().toLowerCase(Locale.ROOT).contains(f);

                    return true;
                })
                .collect(Collectors.toList());
    }

    public List<RegMap> getAllMapsPage(int prevPage,
                                       int nextPage,
                                       int indexer,
                                       int modeMask,
                                       int flag,
                                       String filter) {

        List<RegMap> q = baseMapQuery(modeMask, flag, filter)
                .stream()
                .sorted(Comparator.comparingInt((RegMap m) -> m.getMap()).reversed())
                .collect(Collectors.toList());

        if (indexer == 0) {
            return q.stream().limit(PAGE_SIZE).toList();
        }

        if (nextPage > prevPage) {
            return q.stream()
                    .filter(m -> m.getMap() < indexer)
                    .limit(PAGE_SIZE)
                    .toList();
        }

        return q.stream()
                .filter(m -> m.getMap() > indexer)
                .limit(PAGE_SIZE)
                .toList();
    }

    /* ===================== DOWNLOAD ===================== */

    public boolean download(int mapSeq) {
        RegMap map = regMaps.get(mapSeq);
        if (map != null) {
            map.increaseDownloadCount();
            return true;
        }
        return false;
    }

    /* ===================== RANKINGS ===================== */

    public Map<Integer, RegMap> getWeeklyTopTen() {
        LocalDate weekAgo = LocalDate.now().minusDays(7);

        return regMaps.entrySet().stream()
                .filter(e -> !e.getValue().getRegisteredDate().isBefore(weekAgo.atStartOfDay()))
                .sorted((a, b) ->
                        Integer.compare(b.getValue().getDownloadCount(),
                                a.getValue().getDownloadCount()))
                .limit(10)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }

    public Map<Integer, RegMap> getHallOfFame() {
        int hallOfFameMask = TAG_GOLD_RIBBON | TAG_MEDAL | TAG_GLORY;

        return regMaps.entrySet().stream()
                .filter(e -> (e.getValue().tagMask & hallOfFameMask) != 0)
                .sorted((a, b) ->
                        Integer.compare(b.getValue().getDownloadCount(),
                                a.getValue().getDownloadCount()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }

    public Map<Integer, RegMap> getPopularMapsFallback() {
        LocalDate yesterday = LocalDate.now().minusDays(1);

        return regMaps.entrySet().stream()
                .filter(e -> e.getValue().getRegisteredDate().isEqual(yesterday.atStartOfDay()))
                .sorted((a, b) ->
                        Integer.compare(b.getValue().getDownloadCount(),
                                a.getValue().getDownloadCount()))
                .limit(10)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));
    }
}
