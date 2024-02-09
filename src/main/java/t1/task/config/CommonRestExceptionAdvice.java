package t1.task.config;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import t1.task.config.dto.ErrorMessage;

import java.util.ArrayList;
import java.util.List;

/**
 * Общий обработчик исключений.
 */
@ControllerAdvice
public class CommonRestExceptionAdvice {

    /**
     * Обработчик ошибок связанных с валидацией входных параметров.
     *
     * @param ex      Исключение
     * @param request Данные о запросе
     */
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<ErrorMessage> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request) {
        List<String> constraints = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            constraints.add(violation.getRootBeanClass().getName() + " " +
                    violation.getPropertyPath() + ": " + violation.getMessage());
        }

        ErrorMessage err = ErrorMessage.createDefaultWeb(request, ex)
                .setHttpStatus(HttpStatus.BAD_REQUEST)
                .setErrorMessages(constraints);

        return new ResponseEntity<>(err, err.getHttpStatus());
    }
}
