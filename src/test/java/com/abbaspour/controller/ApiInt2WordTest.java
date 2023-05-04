package com.abbaspour.controller;

import com.abbaspour.model.BitMap;
import com.abbaspour.service.DecodeService;
import com.abbaspour.service.IntegerToWordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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
    private Int2WordController int2WordController;
    @MockBean
    private IntegerToWordService integerToWordService;

    @Test
    public void apiInt2WordCheck() throws Exception {

        // given - precondition or setup
        String language = "english";
        Long number = 12L;
        String output = "Twelve";

        given(integerToWordService.toEnglishWordConverter(number)).willReturn(output);
        MvcResult mvcResult = mockMvc.perform(get("/api/int-to-word/{language}/{number}", language,number))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        String responseBody = response.getContentAsString();
        assertThat(responseBody).isEqualTo("Expected response string");

        // when -  action or the behaviour that we are going test
//        ResultActions response = mockMvc.perform(get("/api/int-to-word/{language}/{number}", language,number));
//        // to check the code is ok just uncomment next line
//        // bitMap.setMachine_on(true);
//        // then - verify the output
//        response.andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(jsonPath("$.machine_on", is(output)));
    }
}