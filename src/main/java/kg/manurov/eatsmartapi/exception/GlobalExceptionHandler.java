package kg.manurov.eatsmartapi.exception;

import kg.manurov.eatsmartapi.services.interfaces.ErrorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @Autowired
    private ErrorService errorService;
    @ExceptionHandler(NoSuchElementException.class)
    public ErrorResponse noSuchElementException(NoSuchElementException e){
        log.error(e.getMessage());
        return ErrorResponse.builder(e, HttpStatus.NOT_FOUND,e.getMessage()).build();
    }

    @ExceptionHandler(NullPointerException.class)
    public ErrorResponse nullPointerExceptions(NullPointerException e){
        log.error(e.getMessage());
        return ErrorResponse.builder(e, HttpStatus.NO_CONTENT,e.getMessage()).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseBody> validationHandler(MethodArgumentNotValidException ex){
        log.error(ex.getMessage());
        return new ResponseEntity<>(errorService.makeResponse(ex.getBindingResult()),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResponse alreadyExistsException(IllegalArgumentException ex){
        log.error(ex.getMessage());
        return ErrorResponse.builder(ex,HttpStatus.CONFLICT,ex.getMessage()).build();
    }
}

