package io.github.minhaz1217.spring_barebone_login.Repository;

import io.github.minhaz1217.spring_barebone_login.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String>{
    User findUserByUsername(String username);
    User findUserByUsernameAndPassword(String username, String password);
    User findByEmail(String email);
}
