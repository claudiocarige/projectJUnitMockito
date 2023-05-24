package br.com.claudiocarige.estudojunitmockito.service.impl;

import br.com.claudiocarige.estudojunitmockito.domain.User;
import br.com.claudiocarige.estudojunitmockito.domain.representation.UserRepresentation;
import br.com.claudiocarige.estudojunitmockito.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;


class UserServiceImplTest {

    public static final Integer ID       = 1;
    public static final String NAME      = "claudio";
    public static final String EMAIL     = "ccarige@gmail.com";
    public static final String PASSWORD  = "123";

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ModelMapper mapper;
    private User user;
    private UserRepresentation userRepresentation;
    private Optional<User> optionalUser;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startModels();
    }

    @Test
    void WhenFindByIdThenReturnAnUserInstance() {
        when(userRepository.findById(anyInt())).thenReturn(optionalUser);

        User user = userService.findById(ID);
        assertNotNull(user);
        assertEquals(User.class, user.getClass());
        assertEquals(ID, user.getId());
        assertEquals(NAME, user.getName());
        assertEquals(PASSWORD, user.getPassword());
        assertEquals(EMAIL, user.getEmail());
    }

    @Test
    void findAll() {
    }

    @Test
    void insert() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    public void startModels(){
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userRepresentation = new UserRepresentation(ID, NAME, EMAIL, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}