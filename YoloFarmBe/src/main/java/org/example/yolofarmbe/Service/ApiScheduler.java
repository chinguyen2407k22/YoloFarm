package org.example.yolofarmbe.Service;

import org.example.yolofarmbe.Configuration.EnvConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ApiScheduler {
    private static final Logger logger = LoggerFactory.getLogger(ApiScheduler.class);
    private final RestTemplate restTemplate = new RestTemplate();

    @Scheduled(fixedRate = 10000)
    public void fetchDataFromApi() {
        try {
            String apiUrl = EnvConfig.getApiUrl();
            String apiKey = EnvConfig.getApiKey();
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-AIO-Key", apiKey); 

            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, String.class);
            String data = response.getBody();

            logger.info("Received data: {}", data);
        } catch (Exception e) {
            logger.error("Error while fetching data from API: ", e);
        }
    }
}
