package br.com.claudiocarige.estudojunitmockito.service.impl;

import br.com.claudiocarige.estudojunitmockito.domain.User;
import br.com.claudiocarige.estudojunitmockito.repository.UserRepository;
import br.com.claudiocarige.estudojunitmockito.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User findById(Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow();
    }

    @Override
    public List<User> findAll() {
        return null;
    }

}
