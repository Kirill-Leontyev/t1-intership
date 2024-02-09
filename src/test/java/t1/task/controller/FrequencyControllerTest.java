package t1.task.controller;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import t1.task.service.FrequencyService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Тестирование {@link FrequencyController} - проверка валидации и т.д.
 */
@WebMvcTest(controllers = FrequencyController.class)
@ExtendWith(SpringExtension.class)
public class FrequencyControllerTest {

    private final static String TEXT_VALID_AMOUNT_OF_SYMBOLS = StringUtils.repeat("a", 10);
    private final static String TEXT_WITH_INVALID_AMOUNT_OF_SYMBOLS = StringUtils.repeat("a", 11);
    private final static String OK_ANSWER = "OK";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FrequencyService frequencyService;

    @Test
    void ifSymbolsAmountInvalidReturnBadRequestStatus() throws Exception {
        mockMvc.perform(
                        get("/freq")
                                .contentType(MediaType.TEXT_PLAIN)
                                .content(TEXT_WITH_INVALID_AMOUNT_OF_SYMBOLS))
                .andExpect(status().isBadRequest());
    }

    @Test
    void ifSymbolsAmountValidReturnOK() throws Exception{
        Mockito.when(frequencyService.getCharEntryFrequency(TEXT_VALID_AMOUNT_OF_SYMBOLS))
                .thenReturn(OK_ANSWER);

        MvcResult result = mockMvc.perform(
                        get("/freq")
                                .contentType(MediaType.TEXT_PLAIN)
                                .content(TEXT_VALID_AMOUNT_OF_SYMBOLS))
                .andExpect(status().is2xxSuccessful()).andReturn();

        assertNotNull(result.getResponse());
        assertEquals(OK_ANSWER, result.getResponse().getContentAsString());
    }
}
