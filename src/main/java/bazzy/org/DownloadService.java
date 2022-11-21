package bazzy.org;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class DownloadService {
    HttpClient client = HttpClient.newBuilder().build();


    public Path downloadFile(String sourceUrl) throws IOException, InterruptedException, URISyntaxException {
        HttpRequest httpRequest = HttpRequest
                .newBuilder()
                .uri(new URI(sourceUrl))
                .GET()
                .build();
        String filename = sourceUrl.substring(sourceUrl.lastIndexOf('/') + 1);

        HttpResponse<InputStream> response = client
                .send(httpRequest, responseInfo ->
                        HttpResponse.BodySubscribers.ofInputStream());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Error with download");
        }

        Path targetPath = new File("src/main/resources/" + File.separator + filename).toPath();
        Files.deleteIfExists(targetPath);
        Files.copy(response.body(), targetPath);

        return targetPath;
    }

    public List<String> readFromFile(Path path) throws IOException {

        return Files.readAllLines(path);

    }
}
