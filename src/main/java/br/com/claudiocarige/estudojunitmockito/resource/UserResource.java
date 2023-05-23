package br.com.claudiocarige.estudojunitmockito.resource;

import br.com.claudiocarige.estudojunitmockito.domain.representation.UserRepresentation;
import br.com.claudiocarige.estudojunitmockito.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor
public class UserResource {

    public static final String ID = "/{id}";
    private final UserService userService;
    private final ModelMapper mapper;

    @GetMapping(value = ID)
    public ResponseEntity<UserRepresentation> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(mapper.map(userService.findById(id), UserRepresentation.class));
    }

    @GetMapping
    public ResponseEntity<List<UserRepresentation>> findAll(){
        return ResponseEntity.ok().body(userService.findAll()
                .stream()
                .map(x -> mapper.map(x, UserRepresentation.class))
                .collect(Collectors.toList()));
    }
}
