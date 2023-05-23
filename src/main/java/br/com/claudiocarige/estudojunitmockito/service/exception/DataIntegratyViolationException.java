package br.com.claudiocarige.estudojunitmockito.service.exception;

public class DataIntegratyViolationException extends RuntimeException{
    public DataIntegratyViolationException(String message) {
        super(message);
    }
}
