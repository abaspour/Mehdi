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
    public void zero() throws Exception {
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
    public void machineCarl() throws Exception{
        String hexString = "0xBBF1";
        BitMap bitMap = new BitMap(
                true,
                false,
                false,
                false,
                191,
                false,
                true
        );

        given(decodeService.decode(hexString)).willReturn(bitMap);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/api/decode/{hexString}", hexString));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.machine_on", is(bitMap.getMachine_on())));
    }

    @Test
    public void machineBeansCarl() throws Exception{
        String hexString = "0x33A3";
        BitMap bitMap = new BitMap(
                true,
                true,
                false,
                false,
                58,
                false,
                true
        );

        given(decodeService.decode(hexString)).willReturn(bitMap);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/api/decode/{hexString}", hexString));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.machine_on", is(bitMap.getMachine_on())));
    }

    @Test
    public void machineCarlNumber() throws Exception{
        String hexString = "0x99C1";
        BitMap bitMap = new BitMap(
                true,
                false,
                false,
                false,
                156,
                false,
                true
        );

        given(decodeService.decode(hexString)).willReturn(bitMap);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/api/decode/{hexString}", hexString));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.machine_on", is(bitMap.getMachine_on())));
    }


    @Test
    public void machine() throws Exception{
        String hexString = "0x0101";
        BitMap bitMap = new BitMap(
                true,
                false,
                false,
                false,
                16,
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
    public void allTrueZero() throws Exception{
        String hexString = "0xF00f";
        BitMap bitMap = new BitMap(
                true,
                true,
                true,
                true,
                0,
                true,
                true
        );

        given(decodeService.decode(hexString)).willReturn(bitMap);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/api/decode/{hexString}", hexString));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.machine_on", is(bitMap.getMachine_on())));
    }
    @Test
    public void allFalse() throws Exception{
        String hexString = "0x0ff0";
        BitMap bitMap = new BitMap(
                false,
                false,
                false,
                false,
                255,
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