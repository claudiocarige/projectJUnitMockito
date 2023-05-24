package br.com.claudiocarige.estudojunitmockito.resource;

import br.com.claudiocarige.estudojunitmockito.domain.User;
import br.com.claudiocarige.estudojunitmockito.domain.representation.UserRepresentation;
import br.com.claudiocarige.estudojunitmockito.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserResourceTest {


    public static final Integer ID       = 1;
    public static final String NAME      = "claudio";
    public static final String EMAIL     = "ccarige@gmail.com";
    public static final String PASSWORD  = "123";
    public static final String NO_SUCH_ELEMENT = "No such element!";
    @InjectMocks
    private UserResource userResource;

    @Mock
    private UserService userService;
    @Mock
    private ModelMapper mapper;

    private User user;
    private UserRepresentation userRepresentation;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startModels();
    }

    @Test
    void findById() {
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

    }
}