package br.com.kaikeventura.restmongodb.error;

import br.com.kaikeventura.restmongodb.error.exception.DocumentNumberAlreadyRegisteredException;
import br.com.kaikeventura.restmongodb.error.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Locale;

@RestControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler {

    private final MessageSource messageSource;
    private final static String NO_MESSAGE_AVAILABLE = "No error message available";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException exception,
            Locale locale
    ) {
        String errorCode = exception
                .getBindingResult()
                .getAllErrors()
                .stream()
                .map(ObjectError::getDefaultMessage)
                .findFirst()
                .get();

        return ResponseEntity.badRequest().body(buildApiError(errorCode, locale));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFoundException(
            UserNotFoundException exception,
            Locale locale
    ) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(buildApiError(exception.errorCode, locale));
    }

    @ExceptionHandler(DocumentNumberAlreadyRegisteredException.class)
    public ResponseEntity<ApiError> handleDocumentNumberAlreadyRegisteredException(
            DocumentNumberAlreadyRegisteredException exception,
            Locale locale
    ) {
        return ResponseEntity.badRequest().body(buildApiError(exception.errorCode, locale));
    }

    private ApiError buildApiError(String errorCode, Locale locale, String... args) {
        String errorMessage = "";
        try {
            errorMessage = messageSource.getMessage(errorCode, args, locale);
        }
        catch (NoSuchMessageException exception) {
            errorMessage = NO_MESSAGE_AVAILABLE;
        }

        return new ApiError(errorCode, errorMessage);
    }
}
