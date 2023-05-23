package br.com.claudiocarige.estudojunitmockito.repository;

import br.com.claudiocarige.estudojunitmockito.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
