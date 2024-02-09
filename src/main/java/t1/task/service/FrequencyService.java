package t1.task.service;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Сервис для подсчета частоты вхождений символов в тексте.
 */
@Service
public class FrequencyService {

    private static final String FREQUENCY_MAP_FORMAT = "\"%s\": %s";

    /**
     * Получить частоту вхождений символов в тексте.
     *
     * @param text Текст
     * @return Строка (пример: “a”: 5, “c”: 4, “b”: 1), в случае неудачи - {@link StringUtils#EMPTY}
     */
    public String getCharEntryFrequency(String text) {
        var freqMap = getCharsFrequencyMap(text);
        return getFrequencyMapFormattedString(freqMap);
    }

    /**
     * Получить частоту символов в строке.
     *
     * @param text Текст
     * @return {@link Map} где ключ - символ, значение - частота вхождения. Если строка пустая/отсутствует - пустой Map
     */
    public Map<Character, Long> getCharsFrequencyMap(String text) {
        if (StringUtils.isEmpty(text)) {
            return Collections.emptyMap();
        }
        return text.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    /**
     * Получить частоту символов в строке в виде форматированной строки.
     * Результат отсортирован по убыванию частоты вхождений.
     *
     * @param freqMap Частота вхождений
     * @return Строка (пример: “a”: 5, “c”: 4, “b”: 1). Если map пустой/отсутствует - {@link StringUtils#EMPTY}
     */
    public String getFrequencyMapFormattedString(Map<Character, Long> freqMap) {
        if (MapUtils.isEmpty(freqMap)) {
            return StringUtils.EMPTY;
        }
        return freqMap.entrySet().stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .map(entry -> String.format(FREQUENCY_MAP_FORMAT, entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(", "));
    }
}
