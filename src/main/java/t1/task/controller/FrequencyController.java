package t1.task.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import t1.task.service.FrequencyService;

/**
 * Контроллер для работы с частотой вхождения символов.
 */
@Validated
@RestController
@RequestMapping("/freq")
@RequiredArgsConstructor
public class FrequencyController {

    private final FrequencyService frequencyService;

    /**
     * Подсчитать кол-во вхождений символов в тексте.
     *
     * @param text Строка
     * @return Строка (пример: “a”: 5, “c”: 4, “b”: 1)
     */
    @GetMapping(consumes = MediaType.TEXT_PLAIN_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String getCharsEntryFrequency(
            @RequestBody @Valid @Size(max = 10, message = "Your text contains too many symbols") String text) {
        return frequencyService.getCharEntryFrequency(text);
    }
}
