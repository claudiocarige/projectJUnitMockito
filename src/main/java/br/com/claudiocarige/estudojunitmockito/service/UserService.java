package br.com.claudiocarige.estudojunitmockito.service;

import br.com.claudiocarige.estudojunitmockito.domain.User;

import java.util.List;

public interface UserService {

    User findById(Integer id);

    List<User> findAll();
}
