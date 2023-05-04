package com.abbaspour.controller;

import com.abbaspour.model.BitMap;
import com.abbaspour.service.DecodeService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest
public class DecodeControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DecodeService decodeService;
    @MockBean
    private Int2WordController int2WordController;

    @Test
    public void okCall() throws Exception {
        String hexString = "0x0000";
        BitMap bitMap = new BitMap(
                false,
                false,
                false,
                false,
                0,
                false,
                false
        );

        given(decodeService.decode(hexString)).willReturn(bitMap);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/api/decode/{hexString}", hexString));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.machine_on", is(bitMap.getMachine_on())));
    }

    @Test
    public void errorHandling() throws Exception{
        String hexString = "0x99Z1";

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/api/decode/{hexString}", hexString));

        response.andExpect(status().is(400))
                .andDo(print())
                .andExpect(content().string("Not an acceptable hexadecimal. try like 0x123f"));
    }
}