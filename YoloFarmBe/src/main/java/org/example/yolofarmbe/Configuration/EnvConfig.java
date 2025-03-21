package org.example.yolofarmbe.Configuration;
import io.github.cdimascio.dotenv.Dotenv;

public class EnvConfig {
    private static final Dotenv dotenv = Dotenv.load();

    public static String getApiUrl() {
        return dotenv.get("API_URL");
    }

    public static String getApiKey() {
        return dotenv.get("API_KEY");
    }
}

