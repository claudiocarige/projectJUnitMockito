package br.com.claudiocarige.estudojunitmockito.service;

import br.com.claudiocarige.estudojunitmockito.domain.User;
import br.com.claudiocarige.estudojunitmockito.domain.representation.UserRepresentation;

import java.util.List;

public interface UserService {

    User findById(Integer id);
    List<User> findAll();
    User insert(UserRepresentation userRepresentation);
    User update(UserRepresentation userRepresentation);
    void delete(Integer id);
}
