package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHeaders;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Main {
    public static final String REMOTE_SERVICE_URI = "https://api.nasa.gov/planetary/apod?api_key=fo9CHDHyv3spAi5Y9Lu3FGfJH7dcy65b7Y2zK5Ek";
    public static final String DOWNLOAD_FOLDER = "G:\\";
    public static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
        CloseableHttpClient client = HttpClientBuilder.create()
                .setUserAgent("My Nasa Test Service")
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)
                        .setSocketTimeout(30000)
                        .setRedirectsEnabled(false)
                        .build())
                .build();

        HttpGet request = new HttpGet(REMOTE_SERVICE_URI);

        CloseableHttpResponse response = client.execute(request);
        InputStream content = response.getEntity().getContent();
        Post post = mapper.readValue(content, new TypeReference<Post>() {
        });

        String imageUrl = post.getUrl();
        String fileName = imageUrl.substring(imageUrl.lastIndexOf('/') + 1);

        HttpGet imageRequest = new HttpGet(imageUrl);
        CloseableHttpResponse imageResponse = client.execute(imageRequest);

        InputStream imageContent = imageResponse.getEntity().getContent();
        byte[] imageData = imageContent.readAllBytes();

        String filePath = DOWNLOAD_FOLDER + fileName;
        Files.createDirectories(Paths.get(DOWNLOAD_FOLDER));
        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            outputStream.write(imageData);
        }

        System.out.println("Image downloaded to: " + filePath);

        Desktop.getDesktop().open(new File(filePath));
    }
}