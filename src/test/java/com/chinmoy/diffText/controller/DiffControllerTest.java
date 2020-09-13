package com.chinmoy.diffText.controller;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.chinmoy.diffText.model.DiffResult;
import com.chinmoy.diffText.model.RequestInput;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class DiffControllerTest {
	
	private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private MockMvc mvc;
    
    @Test
    public void addBothSidesAndEqualTest() throws Exception {
        final String diffId = UUID.randomUUID().toString();

        final String jsonAsString = objectMapper.writeValueAsString(RequestInput.builder()
                .message("d2UgYXJlIHdhZXM=")
                .build());

        mvc.perform(MockMvcRequestBuilders
                .post("/diff/{id}/left", diffId)
                .content(jsonAsString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mvc.perform(MockMvcRequestBuilders
                .post("/diff/{id}/right", diffId)
                .content(jsonAsString)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders
                .get("/diff/{id}", diffId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(DiffResult.EQUALS.toString())));
    }
    
    @Test
    public void addBothSidesAndUnEqualTest() throws Exception {
        final String diffId = UUID.randomUUID().toString();

        final String jsonAsStringLeft = objectMapper.writeValueAsString(RequestInput.builder()
                .message("YXJlIHdlIGdvb2Q=")
                .build());
        
        final String jsonAsStringRight = objectMapper.writeValueAsString(RequestInput.builder()
                .message("d2UgYXJlIG5vdCBnb29k")
                .build());

        mvc.perform(MockMvcRequestBuilders
                .post("/diff/{id}/left", diffId)
                .content(jsonAsStringLeft)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mvc.perform(MockMvcRequestBuilders
                .post("/diff/{id}/right", diffId)
                .content(jsonAsStringRight)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders
                .get("/diff/{id}", diffId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(DiffResult.NOT_EQUAL_SIZE.toString())));
    }
    
    @Test
    public void addBothSidesAndEqualSizeDiffContent() throws Exception {
        final String diffId = UUID.randomUUID().toString();

        final String jsonAsStringLeft = objectMapper.writeValueAsString(RequestInput.builder()
                .message("YXJlIHdlIGdvb2Q=")
                .build());
        
        final String jsonAsStringRight = objectMapper.writeValueAsString(RequestInput.builder()
                .message("d2UgYXJlIGdvb2Q=")
                .build());

        mvc.perform(MockMvcRequestBuilders
                .post("/diff/{id}/left", diffId)
                .content(jsonAsStringLeft)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mvc.perform(MockMvcRequestBuilders
                .post("/diff/{id}/right", diffId)
                .content(jsonAsStringRight)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mvc.perform(MockMvcRequestBuilders
                .get("/diff/{id}", diffId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(DiffResult.SAME_SIZE_BUT_DIFFNT_CONTENT.toString())));
    }

}
