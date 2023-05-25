package br.com.claudiocarige.estudojunitmockito.resource.exceptions;

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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ResourceExceptionHandlerTest {

    public static final String NO_SUCH_ELEMENT = "No Such Element";
    public static final String END_POINT_NAO_SUPORTADO = "EndPoint Não Suportado! Favor revise sua requisição.";
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
                        new NoSuchElementException(NO_SUCH_ELEMENT),
                        new MockHttpServletRequest());
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(NO_SUCH_ELEMENT, response.getBody().getError());
        assertEquals(404, response.getBody().getStatus());
    }

    @Test
    void whenEndpointNotFoundExceptionThenReturnResponse() {
        ResponseEntity<StandardError> response = exceptionHandler
                .endpointNotFound(
                        new HttpRequestMethodNotSupportedException(END_POINT_NAO_SUPORTADO),
                        new MockHttpServletRequest());
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.METHOD_NOT_ALLOWED, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandardError.class, response.getBody().getClass());
        assertEquals(END_POINT_NAO_SUPORTADO, response.getBody().getMessage());
        assertEquals(405, response.getBody().getStatus());
    }

    @Test
    void dataIntegratyViolationException() {

    }
}