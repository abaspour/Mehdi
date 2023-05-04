package com.abbaspour.controller;

import com.abbaspour.model.BitMap;
import com.abbaspour.service.DecodeService;
import com.abbaspour.service.IntegerToWordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class ApiInt2WordTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private DecodeService decodeService;
    @MockBean
    private IntegerToWordService integerToWordService;

    @Test
    public void apiInt2WordCheck() throws Exception {

        // given - precondition or setup
        String language = "english";
        Long number = 12L;
        String output = "Twelve";

        given(integerToWordService.toEnglishWordConverter(number)).willReturn(output);

        // when -  action or the behaviour that we are going test
        ResultActions response = mockMvc.perform(get("/api/int-to-word/{language}/{number}", language,number));
        // to check the code is ok just uncomment next line
        // bitMap.setMachine_on(true);
        // then - verify the output
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.machine_on", is(output)));
    }
}