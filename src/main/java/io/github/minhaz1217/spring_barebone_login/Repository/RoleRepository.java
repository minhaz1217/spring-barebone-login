package io.github.minhaz1217.spring_barebone_login.Repository;

import io.github.minhaz1217.spring_barebone_login.Model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String>{
    Role findByRole(String role);
}
