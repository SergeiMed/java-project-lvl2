package hexlet.code.Differ;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Differ {

    private final File file1;
    private final File file2;

    public Differ(File firstFile, File secondfile2) {
        this.file1 = firstFile;
        this.file2 = secondfile2;
    }

    public final File getFile1() {
        return file1;
    }

    public final File getFile2() {
        return file2;
    }

    public final String generate() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> scoreByName = mapper.readValue(file1, Map.class);
        Map<String, Object> scoreByName1 = mapper.readValue(file2, Map.class);
        Map<String, Object> sortedMap = new TreeMap<>(scoreByName);
        Map<String, Object> sortedMap1 = new TreeMap<>(scoreByName1);
        StringBuilder builder = new StringBuilder("{\n");
        for (Map.Entry<String, Object> map : sortedMap.entrySet()) {
            if (!sortedMap1.containsKey(map.getKey())) {
                builder.append("- ").append(map.getKey()).append(": ").append(map.getValue()).append("\n");
            }
            if (sortedMap1.containsKey(map.getKey())) {
                if (!map.getValue().equals(sortedMap1.get(map.getKey()))) {
                    builder.append("- ").append(map.getKey()).append(": ")
                        .append(map.getValue()).append("\n");
                    builder.append("+ ").append(map.getKey()).append(": ")
                        .append(sortedMap1.get(map.getKey())).append("\n");
                } else if (sortedMap1.containsKey(map.getKey())
                    && map.getValue().equals(sortedMap1.get(map.getKey()))) {
                    builder.append("  ").append(map.getKey()).append(": ").append(map.getValue()).append("\n");
                }
            }
        }
        for (Map.Entry<String, Object> map1 : sortedMap1.entrySet()) {
            if (!sortedMap.containsKey(map1.getKey())) {
                builder.append("+ ").append(map1.getKey()).append(": ").append(map1.getValue()).append("\n");
            }
        }
        builder.append("}\n");
        String result = builder.toString();
        System.out.println(result);
        return result;
    }
}
