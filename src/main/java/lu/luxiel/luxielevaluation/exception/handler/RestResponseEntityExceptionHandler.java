package lu.luxiel.luxielevaluation.exception.handler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import lu.luxiel.luxielevaluation.exception.EntityNotFoundException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = { EntityNotFoundException.class })
	protected ResponseEntity<Object> handleNotFound(EntityNotFoundException ex, WebRequest request) {
		Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Entity not found");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}

}
