package com.abbaspour.controller;

import com.abbaspour.service.DecodeServiceInterface;
import com.abbaspour.service.IntegerToWordService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class Int2WordControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DecodeServiceInterface decodeServiceInterface;

    @MockBean
    private IntegerToWordService integerToWordService;

    @Test
    public void regularNumber() throws Exception {
        String language = "english";
        String number = "12";
        String expectedValue = "Twelve";
        given(integerToWordService.toEnglishWordConverter((long) Integer.parseInt(number))).willReturn(expectedValue);
        

        ResultActions response = mockMvc.perform(get("/api/int-to-word/{language}/{number}", language,number));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(expectedValue));
    }

    @Test
    public void edgeCaseMax() throws Exception {
        String language = "english";
        String number = "2147483647";
        String expectedValue = "Two billion one hundred forty seven million four hundred eighty" +
                " three thousand six hundred and forty seven";
        given(integerToWordService.toEnglishWordConverter((long) Integer.parseInt(number))).willReturn(expectedValue);


        ResultActions response = mockMvc.perform(get("/api/int-to-word/{language}/{number}", language,number));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(expectedValue));
    }

    @Test
    public void edgeCaseMin() throws Exception {
        String language = "english";
        String number = "-2147483648";
        String expectedValue = "Negative two billion one hundred forty seven million four hundred eighty " +
                "three thousand six hundred and forty eight";
        given(integerToWordService.toEnglishWordConverter((long) Integer.parseInt(number))).willReturn(expectedValue);


        ResultActions response = mockMvc.perform(get("/api/int-to-word/{language}/{number}", language,number));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(expectedValue));
    }


    @Test
    public void edgeCaseWithSpace() throws Exception {
        String language = "english";
        String number = "-2 147 483 648";
        String expectedValue = "Negative two billion one hundred forty seven million four hundred eighty " +
                "three thousand six hundred and forty eight";
        given(integerToWordService.toEnglishWordConverter(-2147483648L)).willReturn(expectedValue);


        ResultActions response = mockMvc.perform(get("/api/int-to-word/{language}/{number}", language,number));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().string(expectedValue));
    }

    @Test
    public void edgeCaseErrorMax() throws Exception {
        String language = "english";
        String number = "2147483648";
        String expectedValue = "over MAX_VALUE.";

        ResultActions response = mockMvc.perform(get("/api/int-to-word/{language}/{number}", language,number));

        response.andExpect(status().is(400))
                .andDo(print())
                .andExpect(content().string(expectedValue));
    }

    @Test
    public void edgeCaseErrorMin() throws Exception {
        String language = "english";
        String number = "-2147483649";
        String expectedValue = "less than MIN_VALUE.";

        ResultActions response = mockMvc.perform(get("/api/int-to-word/{language}/{number}", language,number));

        response.andExpect(status().is(400))
                .andDo(print())
                .andExpect(content().string(expectedValue));
    }

    @Test
    public void errorHandlingInvalidNumber() throws Exception {
        String language = "english";
        String number = "-85a";
        String expectedValue = "not an integer number or length more than 10 digits";


        ResultActions response = mockMvc.perform(get("/api/int-to-word/{language}/{number}", language,number));

        response.andExpect(status().is(400))
                .andDo(print())
                .andExpect(content().string(expectedValue));
    }

    @Test
    public void errorHandlingTooLow() throws Exception {
        String language = "english";
        String number = "-9999999999";
        String expectedValue = "less than MIN_VALUE.";


        ResultActions response = mockMvc.perform(get("/api/int-to-word/{language}/{number}", language,number));

        response.andExpect(status().is(400))
                .andDo(print())
                .andExpect(content().string(expectedValue));
    }

    @Test
    public void errorHandlingTooLarge() throws Exception {
        String language = "english";
        String number = "9999999999";
        String expectedValue = "over MAX_VALUE.";


        ResultActions response = mockMvc.perform(get("/api/int-to-word/{language}/{number}", language,number));

        response.andExpect(status().is(400))
                .andDo(print())
                .andExpect(content().string(expectedValue));
    }
}