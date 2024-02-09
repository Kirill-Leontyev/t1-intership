package t1.task.config.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Формат сообщения об ошибке.
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class ErrorMessage {

    private HttpStatus httpStatus;

    private String errorException;

    private String timestamp;

    private String path;

    private List<String> errorMessages;

    public static ErrorMessage createDefaultWeb(WebRequest request) {
        return new ErrorMessage()
                .setPath(request.getDescription(false))
                .setTimestamp(LocalDateTime.now().toString());
    }

    public static ErrorMessage createDefaultWeb(WebRequest request, Exception ex) {
        return createDefaultWeb(request)
                .setErrorException(ex.getClass().getName());
    }
}
