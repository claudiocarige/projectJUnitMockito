package br.com.claudiocarige.estudojunitmockito.service.impl;


import br.com.claudiocarige.estudojunitmockito.domain.User;
import br.com.claudiocarige.estudojunitmockito.domain.representation.UserRepresentation;
import br.com.claudiocarige.estudojunitmockito.repository.UserRepository;
import br.com.claudiocarige.estudojunitmockito.service.UserService;
import br.com.claudiocarige.estudojunitmockito.service.exception.DataIntegrityViolationException;
import br.com.claudiocarige.estudojunitmockito.service.exception.NoSuchElementException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper mapper;


    @Override
    public User findById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new NoSuchElementException("No Such Element"));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
    @Override
    public User insert(UserRepresentation userRepresentation) {
        userRepresentation.setId(null);
        findByEmail(userRepresentation);
        return userRepository.save(mapper.map(userRepresentation, User.class));
    }

    @Override
    public User update(UserRepresentation userRepresentation) {
        findByEmail(userRepresentation);
        return userRepository.save(mapper.map(userRepresentation, User.class));
    }

    @Override
    public void delete(Integer id) {
        findById(id);
        userRepository.deleteById(id);
    }

    private void findByEmail(UserRepresentation userRepresentation) {
        Optional<User> user = userRepository.findByEmail(userRepresentation.getEmail());
        if (user.isPresent() && !user.get().getId().equals(userRepresentation.getId())) {
            throw new DataIntegrityViolationException("E-mail já cadastrado! Favor revise sua requisição.");
        }
    }

}
