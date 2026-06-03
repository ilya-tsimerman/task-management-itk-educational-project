package ilya.tsimerman.accountingservice.domain.handler;

import ilya.tsimerman.accountingservice.domain.data.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneral(Exception ex, HttpServletRequest request) {
        log.error("Внутренняя ошибка сервера: {}", ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(buildErrorResponse("Внутренняя ошибка сервера", ex.getMessage(), request));
    }

    private ErrorResponse buildErrorResponse(String title, String detail, HttpServletRequest request) {
        return new ErrorResponse(
                title,
                detail,
                String.format("%s %s", request.getMethod(), request.getRequestURI()),
                LocalDateTime.now()
        );
    }
}