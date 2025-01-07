package io.api.produtos.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import io.api.produtos.exception.ProdutoNotFoundException;
import jakarta.validation.ConstraintViolationException;



// HttpMessageNotReadableException
// DataIntegrityViolationException
// HandlerMethodValidationException
//

@RestControllerAdvice
public class ApplicationExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException exception){
		Map<String, String> errorMap = new HashMap<>();
		
		exception.getBindingResult().getFieldErrors().forEach(error -> {
			errorMap.put(error.getField(), error.getDefaultMessage());
		});
		
		return errorMap;
	}
	
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Map<String, String> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception){
        Map<String, String> errors = new HashMap<>();

        errors.put("HttpMessageNotReadableException",exception.getMessage());

        return errors;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ProdutoNotFoundException.class)
    public Map<String, String> handleProductNotFoundException(ProdutoNotFoundException exception){
        Map<String, String> error = new HashMap<>();
        error.put("ProdutoNotFoundException", "Produto not found!");
        return error;
    }
    

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, String> handleConstraintViolationException(ConstraintViolationException exception) {
        Map<String, String> errors = new HashMap<>();

        exception.getConstraintViolations().forEach(violation -> {
            String erro = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            errors.put(erro, message);
        });

        return errors;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Map<String, String> handleDataIntegrity(DataIntegrityViolationException exception){

        Map<String, String> error = new HashMap<>();

        error.put("DataIntegrityViolationException", "Unique index or primary key violation, 'codigo' must be unique");

        return error;

    }
 
	
}
