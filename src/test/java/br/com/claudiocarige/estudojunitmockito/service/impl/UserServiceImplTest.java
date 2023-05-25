package br.com.claudiocarige.estudojunitmockito.service.impl;

import br.com.claudiocarige.estudojunitmockito.domain.User;
import br.com.claudiocarige.estudojunitmockito.domain.representation.UserRepresentation;
import br.com.claudiocarige.estudojunitmockito.repository.UserRepository;
import br.com.claudiocarige.estudojunitmockito.service.exception.DataIntegrityViolationException;
import br.com.claudiocarige.estudojunitmockito.service.exception.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;


@SpringBootTest
class UserServiceImplTest {

    public static final Integer ID       = 1;
    public static final String NAME      = "claudio";
    public static final String EMAIL     = "ccarige@gmail.com";
    public static final String PASSWORD  = "123";
    public static final String NO_SUCH_ELEMENT = "No such element!";
    public static final int INDEX = 0;
    public static final String E_MAIL_JA_CADASTRADO_NA_BASE_DE_DADOS = "E-mail já cadastrado na base de dados!";
    public static final String NO_SUCH_ELEMENT1 = "No such element!";

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper mapper;
    private User user;
    private UserRepresentation userRepresentation;
    private Optional<User> optionalUser;

    private List<User> list;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startModels();
    }

    @Test
    void WhenFindByIdThenReturnAnUserInstance() {
        when(userRepository.findById(anyInt())).thenReturn(optionalUser);

        User response = userService.findById(ID);
        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(PASSWORD, response.getPassword());
        assertEquals(EMAIL, response.getEmail());
    }
    @Test
    void WhenFindByIdThenReturnAnNoSuchElementException(){
        when(userRepository.findById(anyInt())).thenThrow(new NoSuchElementException(NO_SUCH_ELEMENT));

        try {
            userService.findById(ID);
        }catch (Exception ex){
            assertEquals(NoSuchElementException.class, ex.getClass());
            assertEquals(NO_SUCH_ELEMENT, ex.getMessage());
        }
    }

    @Test
    void WhenFindAllThenReturnAnListUsers(){
        when(userRepository.findAll()).thenReturn(List.of(user,new User(2,"Maria","maria@gmail.com", "123")));

        List<User> response = userService.findAll();
        assertNotNull(response);
        assertEquals(2, response.size());
        assertEquals(User.class, response.get(INDEX).getClass());

        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getName());
        assertEquals(EMAIL, response.get(INDEX).getEmail());
        assertEquals(PASSWORD, response.get(INDEX).getPassword());
    }

    @Test
    void WhenInsertThenReturnSuccess() {
        when(userRepository.save(any())).thenReturn(user);

        User response = userService.insert(userRepresentation);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID,response.getId());
        assertEquals(NAME,response.getName());
        assertEquals(EMAIL,response.getEmail());
        assertEquals(PASSWORD,response.getPassword());
    }

    @Test
    void WhenInsertThenReturnAnDataIntegrityViolationException() {
        when(userRepository.findByEmail(anyString())).thenReturn(optionalUser);

        try {
            optionalUser.get().setId(2);
            userService.insert(userRepresentation);
        }catch (Exception ex){
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
            assertEquals(E_MAIL_JA_CADASTRADO_NA_BASE_DE_DADOS, ex.getMessage());
        }
    }

    @Test
    void WhenUpdateThenReturnSuccess() {
        when(userRepository.save(any())).thenReturn(user);

        User response = userService.update(userRepresentation);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID,response.getId());
        assertEquals(NAME,response.getName());
        assertEquals(EMAIL,response.getEmail());
        assertEquals(PASSWORD,response.getPassword());
    }

    @Test
    void WhenUpdateThenReturnAnDataIntegrityViolationException() {
        when(userRepository.findByEmail(anyString())).thenReturn(optionalUser);

        try {
            optionalUser.get().setId(2);
            userService.update(userRepresentation);
        }catch (Exception ex){
            assertEquals(DataIntegrityViolationException.class, ex.getClass());
            assertEquals(E_MAIL_JA_CADASTRADO_NA_BASE_DE_DADOS, ex.getMessage());
        }
    }
    @Test
    void deleteWithSuccess() {
        when(userRepository.findById(anyInt())).thenReturn(optionalUser);

        doNothing().when(userRepository).deleteById(anyInt());
        userService.delete(ID);
        verify(userRepository, times(1)).deleteById(anyInt());
    }

    @Test
    void deleteWithNoSuchElementException(){
        when(userRepository.findById(anyInt())).thenThrow(new NoSuchElementException(NO_SUCH_ELEMENT1));

        try {
            userService.delete(ID);
        }catch (Exception ex){
            assertEquals(NoSuchElementException.class, ex.getClass());
            assertEquals(NO_SUCH_ELEMENT1, ex.getMessage());
        }
    }

    public void startModels(){
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userRepresentation = new UserRepresentation(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}