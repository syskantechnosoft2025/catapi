package com.example.catapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;

@Service
public class CatService {

    private static final String CAT_API_URL = "https://cataas.com/cat";

    private static final Logger logger = LoggerFactory.getLogger(CatService.class);

    private final RestTemplate restTemplate;

    public CatService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public byte[] getCatImage() {
        logger.info("Fetching cat image from cataas.com");
        try {
            ResponseEntity<byte[]> response = restTemplate.getForEntity(CAT_API_URL, byte[].class);
            byte[] image = response.getBody();

            if (!response.getStatusCode().is2xxSuccessful() || image == null || image.length == 0) {
                String msg = "Invalid response from cataas.com";
                logger.error("{} status={} length={}", msg, response.getStatusCode(), image == null ? 0 : image.length);
                throw new CatApiException(msg, HttpStatus.SERVICE_UNAVAILABLE);
            }

            logger.info("Successfully fetched cat image, size: {}", image.length);
            return image;
        } catch (RestClientException e) {
            logger.error("Error fetching cat image from {}", CAT_API_URL, e);
            throw new CatApiException("Unable to fetch cat image from external service", e, HttpStatus.SERVICE_UNAVAILABLE);
        }
    }
}