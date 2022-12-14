import bazzy.org.DownloadService;
import bazzy.org.PhoneStoreItem;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            throw new RuntimeException("Path is not defined");
        }
        DownloadService service = new DownloadService();
        String url = args[0];

        Path downloadedFile;

        try {
            downloadedFile = service.downloadFile(url);
        } catch (Exception e) {
            throw new RuntimeException("Error with download file", e);
        }
        JsonMapper(downloadedFile);
//        List<String> data = service.readFromFile(downloadedFile);
//        FixNamesLength(data);
    }

    public static void JsonMapper(Path path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonFactory jf = new JsonFactory();
        JsonParser jp = jf.createParser(new File(String.valueOf(path)));
        PhoneStoreItem[] phoneStoreItems = objectMapper.readValue(jp, PhoneStoreItem[].class);
        for (PhoneStoreItem phone : phoneStoreItems) {
            System.out.println(phone);
        }
    }

    public static void FixNamesLength(List<String> data) throws IOException {
        Map<String, String> pairs = new HashMap<>();

        for (String str : data) {
            if ("".equals(str)) continue;
            String[] pair = str.split("=");
            if ("?".equals(pair[1])) {
                pairs.put(pair[0], String.valueOf(pair[0].length()));
            } else if (isNumber(pair[1])) {
                pairs.put(pair[0], pair[1]);
            } else {
                throw new RuntimeException("File contains invalid values");
            }
        }

        String filename = "dest.txt";
        File destPath = new File("src/" + File.separator + filename);
        try (FileWriter writer = new FileWriter(destPath)) {
            for (var entry : pairs.entrySet()) {
                writer.write(String.format("%s=%s\n", entry.getKey(), entry.getValue()));
            }
        }
    }

    public static boolean isNumber(String value) {
        for (int i = 0; i < value.length(); i++) {
            if (!Character.isDigit(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
