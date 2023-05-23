package br.com.claudiocarige.estudojunitmockito.service.exception;

public class NoSuchElementException extends RuntimeException{
    public NoSuchElementException(String message) {
        super(message);
    }
}
