package io.github.minhaz1217.spring_barebone_login;

import io.github.minhaz1217.spring_barebone_login.Model.Role;
import io.github.minhaz1217.spring_barebone_login.Model.User;
import io.github.minhaz1217.spring_barebone_login.Repository.RoleRepository;
import io.github.minhaz1217.spring_barebone_login.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;

@Component
public class DBSeeder implements CommandLineRunner{

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    DBSeeder(UserRepository userRepository, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }
    @Override
    public void run(String... args) throws Exception {
        userRepository.deleteAll();
        roleRepository.deleteAll();
        System.out.println("DB LOADED: USERS: " + userRepository.count() + " ROLES: " + roleRepository.count());

        roleRepository.save(new Role("ADMIN"));
        roleRepository.save(new Role("USER"));

        userRepository.save(new User("user", "user", "user@user.com" , true,  new HashSet<>(Arrays.asList(roleRepository.findRoleByRole("ADMIN") ) ) ));
        userRepository.save(new User("1", "1", "1" , true,  new HashSet<>(Arrays.asList(roleRepository.findRoleByRole("ADMIN") ) ) ));
        userRepository.save(new User("admin", "admin", "admin" , true,  new HashSet<>(Arrays.asList(roleRepository.findRoleByRole("ADMIN") ) ) ));
        System.out.println("DB LOADED: USERS: " + userRepository.count() + " ROLES: " + roleRepository.count());
    }
}
