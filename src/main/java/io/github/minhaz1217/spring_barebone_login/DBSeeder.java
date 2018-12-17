package io.github.minhaz1217.spring_barebone_login;

import io.github.minhaz1217.spring_barebone_login.Model.User;
import io.github.minhaz1217.spring_barebone_login.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBSeeder implements CommandLineRunner{

    private UserRepository userRepository;
    DBSeeder(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
        userRepository.save(new User("user", "user"));
        userRepository.save(new User("1", "1"));
        userRepository.save(new User("admin", "admin"));
    }
}
