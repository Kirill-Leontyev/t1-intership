package t1.task.service;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Тестирование {@link FrequencyService} - проверка все ли работает так, как указано в документации.
 */
@ExtendWith(MockitoExtension.class)
public class FrequencyServiceTest {

    private final static String TWO_SYMBOLS_TEXT = "ab";

    @Spy
    private FrequencyService frequencyService;

    @Test
    public void ifTextIsNullShouldReturnEmptyMap() {
        var result = frequencyService.getCharsFrequencyMap(null);
        assertEquals(Collections.EMPTY_MAP, result);
    }

    @Test
    public void ifMapIfNullShouldReturnEmptyString() {
        var result = frequencyService.getFrequencyMapFormattedString(null);
        assertEquals(StringUtils.EMPTY, result);
    }

    @Test
    public void textWithTwoSymbolsShouldReturnMapWithTwoKeys() {
        var result = frequencyService.getCharsFrequencyMap(TWO_SYMBOLS_TEXT);
        assertFalse(result.isEmpty());
        assertEquals(result.size(), 2);
    }

}
