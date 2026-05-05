package com.example.catapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(CatController.class)
public class CatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CatService catService;

    @Test
    public void testGetCat() throws Exception {
        byte[] mockImage = "mock image data".getBytes();
        when(catService.getCatImage()).thenReturn(mockImage);

        mockMvc.perform(get("/api/cat"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("image/jpeg"));
    }

    @Test
    public void testGetCatReturnsErrorResponseWhenServiceFails() throws Exception {
        when(catService.getCatImage()).thenThrow(new CatApiException("Unable to fetch cat image from external service", HttpStatus.SERVICE_UNAVAILABLE));

        mockMvc.perform(get("/api/cat"))
                .andExpect(status().isServiceUnavailable())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(HttpStatus.SERVICE_UNAVAILABLE.value()))
                .andExpect(jsonPath("$.error").value(HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase()))
                .andExpect(jsonPath("$.message").value("Unable to fetch cat image from external service"));
    }
}