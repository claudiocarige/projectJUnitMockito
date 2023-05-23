package br.com.claudiocarige.estudojunitmockito.resource.exceptions;

import br.com.claudiocarige.estudojunitmockito.service.exception.DataIntegratyViolationException;
import br.com.claudiocarige.estudojunitmockito.service.exception.NoSuchElementException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<StandardError> objectNotFound(NoSuchElementException ex, HttpServletRequest request ){
        StandardError erro = new StandardError(LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                "Objeto Não Encontrado!",
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<StandardError> endpointNotFound(HttpRequestMethodNotSupportedException ex, HttpServletRequest request ){
        StandardError erro = new StandardError(LocalDateTime.now(),
                HttpStatus.METHOD_NOT_ALLOWED.value(),
                ex.getMessage(),
                "EndPoint Não Suportado! Favor revise sua requisição.",
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(erro);
    }
    @ExceptionHandler(DataIntegratyViolationException.class)
    public ResponseEntity<StandardError> dataIntegratyViolationException(DataIntegratyViolationException ex, HttpServletRequest request ){
        StandardError erro = new StandardError(LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                "E-mail já cadastrado! Favor revise sua requisição.",
                request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}
