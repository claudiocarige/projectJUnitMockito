package br.com.claudiocarige.estudojunitmockito.resource.exceptions;

import br.com.claudiocarige.estudojunitmockito.service.exception.DataIntegrityViolationException;
import br.com.claudiocarige.estudojunitmockito.service.exception.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
class ResourceExceptionHandlerTest {

    public static final String MESSAGE_NO_SUCH_ELEMENT = "No Such Element";
    public static final String MESSAGE_HTTP_NOT_SUPPORTED = "EndPoint Não Suportado! Favor revise sua requisição.";
    public static final String MESSAGE_DATA_INTEGRITY = "E-mail já cadastrado! Favor revise sua requisição.";
    @InjectMocks
    private ResourceExceptionHandler exceptionHandler;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void WhenObjectNotFoundExceptionThenReturnResponseEntity() {
        ResponseEntity<StandardError> response = exceptionHandler
                .objectNotFound(
                        new NoSuchElementException(MESSAGE_NO_SUCH_ELEMENT),
                        new MockHttpServletRequest());
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(MESSAGE_NO_SUCH_ELEMENT, response.getBody().getMessage());
        assertEquals(404, response.getBody().getStatus());
        assertNotEquals("/users/2", response.getBody().getPath());
        assertNotEquals(LocalDateTime.now(), response.getBody().getTimestamp());
    }

    @Test
    void whenEndpointNotFoundExceptionThenReturnResponse() {
        ResponseEntity<StandardError> response = exceptionHandler
                .endpointNotFound(
                        new HttpRequestMethodNotSupportedException(MESSAGE_HTTP_NOT_SUPPORTED),
                        new MockHttpServletRequest());
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(MESSAGE_HTTP_NOT_SUPPORTED, response.getBody().getMessage());
        assertEquals(405, response.getBody().getStatus());
    }

    @Test
    void whenDataIntegrityViolationExceptionThenReturnResponse() {
        ResponseEntity<StandardError> response = exceptionHandler
                .dataIntegrityViolationException(
                        new DataIntegrityViolationException(MESSAGE_DATA_INTEGRITY),
                        new MockHttpServletRequest());
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(MESSAGE_DATA_INTEGRITY, response.getBody().getMessage());
        assertEquals(400, response.getBody().getStatus());
    }
}