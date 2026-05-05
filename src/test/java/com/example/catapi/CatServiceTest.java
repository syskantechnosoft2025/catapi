package com.example.catapi;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CatServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CatService catService;

    @Test
    public void testGetCatImage() {
        byte[] mockImage = "mock image".getBytes();
        when(restTemplate.getForEntity(any(String.class), eq(byte[].class)))
                .thenReturn(new ResponseEntity<>(mockImage, HttpStatus.OK));

        byte[] result = catService.getCatImage();

        assertNotNull(result);
    }

    @Test
    public void testGetCatImageThrowsWhenBodyMissing() {
        when(restTemplate.getForEntity(any(String.class), eq(byte[].class)))
                .thenReturn(new ResponseEntity<>(null, HttpStatus.OK));

        CatApiException exception = assertThrows(CatApiException.class, () -> catService.getCatImage());
        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, exception.getStatus());
    }

    @Test
    public void testGetCatImageThrowsOnRestClientException() {
        when(restTemplate.getForEntity(any(String.class), eq(byte[].class)))
                .thenThrow(new org.springframework.web.client.RestClientException("Service unavailable"));

        CatApiException exception = assertThrows(CatApiException.class, () -> catService.getCatImage());
        assertEquals(HttpStatus.SERVICE_UNAVAILABLE, exception.getStatus());
    }
}