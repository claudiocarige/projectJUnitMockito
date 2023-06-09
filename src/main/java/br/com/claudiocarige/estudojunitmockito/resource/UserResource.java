package br.com.claudiocarige.estudojunitmockito.resource;

import br.com.claudiocarige.estudojunitmockito.domain.representation.UserRepresentation;
import br.com.claudiocarige.estudojunitmockito.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    public static final String ID = "/{id}";
    @Autowired
    private UserService userService;
    @Autowired
    private ModelMapper mapper;

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
    @PostMapping
    public ResponseEntity<UserRepresentation> insert(@RequestBody UserRepresentation userRepresentation){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{id}")
                .buildAndExpand(userService.insert(userRepresentation).getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = ID)
    public ResponseEntity<UserRepresentation> update(@PathVariable Integer id,
                                                     @RequestBody UserRepresentation userRepresentation){
        userRepresentation.setId(id);
        return ResponseEntity.ok().body(mapper.map(userService.update(userRepresentation), UserRepresentation.class));
    }

    @DeleteMapping(value = ID)
    public ResponseEntity<UserRepresentation> delete(@PathVariable Integer id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
