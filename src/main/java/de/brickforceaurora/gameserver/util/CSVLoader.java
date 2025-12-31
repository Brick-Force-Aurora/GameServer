package de.brickforceaurora.gameserver.util;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * Server-side CSV loader compatible with the original C# implementation.
 */
public class CSVLoader {

    private static final String SECRET_KEY = "Password";
    private static final char CRYPT = 'E';

    private boolean loaded;
    private List<String[]> rows;
    private int noCols;

    public CSVLoader() {
        this.loaded = false;
        this.rows = null;
    }

    public CSVLoader(List<String[]> data) {
        this.rows = data;
        this.loaded = true;
        this.noCols = 0;
        for (String[] row : rows) {
            noCols = Math.max(noCols, row.length);
        }
    }

    /* ===================== GETTERS ===================== */

    public int getCols() {
        return noCols;
    }

    public int getRows() {
        return rows == null ? 0 : rows.size();
    }

    /* ===================== READ VALUES ===================== */

    public long readLong(int col, int row, long def) {
        if (!valid(col, row)) return def;
        return Long.parseLong(rows.get(row)[col]);
    }

    public int readInt(int col, int row, int def) {
        if (!valid(col, row)) return def;
        return Integer.parseInt(rows.get(row)[col]);
    }

    public boolean readBool(int col, int row, boolean def) {
        if (!valid(col, row)) return def;
        return Boolean.parseBoolean(rows.get(row)[col]);
    }

    public float readFloat(int col, int row, float def) {
        if (!valid(col, row)) return def;
        return Float.parseFloat(rows.get(row)[col]);
    }

    public String readString(int col, int row, String def) {
        if (!valid(col, row)) return def;
        return rows.get(row)[col];
    }

    private boolean valid(int col, int row) {
        return loaded && rows != null && row < rows.size() && col < noCols;
    }

    /* ===================== GENERIC ACCESS ===================== */

    @SuppressWarnings("unchecked")
    public <T> T getValue(String findRow, int col) {
        for (String[] r : rows) {
            if (r.length > 0 && r[0].equals(findRow)) {
                if (r.length > col) {
                    return (T) convert(r[col]);
                }
            }
        }
        System.out.println("CSVLoader.getValue: row or column missing: " + findRow);
        return null;
    }

    private Object convert(String s) {
        return s;
    }

    /* ===================== LOAD (TEXT) ===================== */

    public boolean load(String pathName) {
        try (BufferedReader reader =
                     Files.newBufferedReader(Path.of(pathName), StandardCharsets.US_ASCII)) {

            rows = new ArrayList<>();
            noCols = 0;
            Set<Integer> validCols = new HashSet<>();

            String line;
            int rowIndex = 0;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] split = line.split(",");

                if (rowIndex++ == 0) {
                    for (int i = 0; i < split.length; i++) {
                        if (!split[i].startsWith("*") && !split[i].isEmpty()) {
                            validCols.add(i);
                            noCols++;
                        }
                    }
                    if (noCols == 0)
                        throw new RuntimeException("No valid columns");
                } else {
                    String[] row = new String[noCols];
                    int idx = 0;
                    for (int i = 0; i < split.length; i++) {
                        if (validCols.contains(i)) {
                            row[idx++] = split[i].replace('"', ' ').trim();
                        }
                    }
                    rows.add(row);
                }
            }
            loaded = true;
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /* ===================== COOKED LOAD ===================== */

    public boolean securedLoad(String pathName) {
        Path file = Path.of(pathName + ".cooked");
        try (DataInputStream in = new DataInputStream(Files.newInputStream(file))) {
            securedLoadFromStream(in);
            loaded = true;
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private void securedLoadFromStream(DataInputStream in) throws IOException {
        int rowCount = in.readInt();
        rows = new ArrayList<>();

        for (int i = 0; i < rowCount; i++) {
            int cols = in.readInt();
            String[] row = new String[cols];

            for (int j = 0; j < cols; j++) {
                int len = in.readInt();
                if (len > 0) {
                    char[] chars = new char[len];
                    for (int k = 0; k < len; k++) {
                        chars[k] = (char) (in.readChar() ^ CRYPT);
                    }
                    row[j] = new String(chars);
                } else {
                    row[j] = "";
                }
            }
            rows.add(row);
            noCols = cols;
        }
    }

    /* ===================== COOKED SAVE ===================== */

    public boolean securedSave(String pathName) {
        if (!loaded || rows == null) {
            System.out.println("CSV not loaded");
            return false;
        }

        Path file = Path.of(pathName + ".cooked");

        try (DataOutputStream out = new DataOutputStream(Files.newOutputStream(file))) {
            out.writeInt(rows.size());
            for (String[] row : rows) {
                out.writeInt(row.length);
                for (String s : row) {
                    char[] chars = s.toCharArray();
                    out.writeInt(chars.length);
                    for (char c : chars) {
                        out.writeChar(c ^ CRYPT);
                    }
                }
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /* ===================== ENCRYPT / DECRYPT ===================== */

    public void encryptFile(String input, String output, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(), "DES"));

        try (InputStream in = Files.newInputStream(Path.of(input));
             OutputStream out = new CipherOutputStream(Files.newOutputStream(Path.of(output)), cipher)) {
            in.transferTo(out);
        }
    }

    public void decryptFile(String input, String output, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(), "DES"));

        try (InputStream in = new CipherInputStream(Files.newInputStream(Path.of(input)), cipher);
             OutputStream out = Files.newOutputStream(Path.of(output))) {
            in.transferTo(out);
        }
    }
}
