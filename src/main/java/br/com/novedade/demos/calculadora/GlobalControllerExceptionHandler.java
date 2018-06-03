package br.com.novedade.demos.calculadora;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    static final Logger logger = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);

    @Autowired
    private ErrorAttributes errorAttributes;

    @ExceptionHandler(value = {Throwable.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, Object> unknownException(WebRequest request) {
        
    	Map<String, Object>  erros = errorAttributes.getErrorAttributes(request, true);
    	
    	erros.put("status",HttpStatus.BAD_REQUEST);
    	erros.put("error",HttpStatus.BAD_REQUEST.toString());
        
        return erros;

    }
  
}
