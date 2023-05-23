package br.com.claudiocarige.estudojunitmockito.config;

import br.com.claudiocarige.estudojunitmockito.domain.User;
import br.com.claudiocarige.estudojunitmockito.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("local")
@RequiredArgsConstructor
public class LocalConfig {

    private final UserRepository userRepository;
    @Bean
    public void startDB(){

        User user01 = new User(null, "Claudio", "ccarige@gmail.com", "123456");
        User user02 = new User(null, "Maria", "maria@gmail.com", "123456");
        User user03 = new User(null, "Paula", "paula@gmail.com", "123456");
        userRepository.saveAll(Arrays.asList(user01,user02,user03));
    }
}
