package io.github.minhaz1217.spring_barebone_login;

import io.github.minhaz1217.spring_barebone_login.Model.Role;
import io.github.minhaz1217.spring_barebone_login.Model.User;
import io.github.minhaz1217.spring_barebone_login.Repository.RoleRepository;
import io.github.minhaz1217.spring_barebone_login.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        //userRepository.deleteAll();
        //roleRepository.deleteAll();

        System.out.println("DB LOADED: USERS: " + userRepository.count() + " ROLES: " + roleRepository.count());

        //roleRepository.save(new Role("ADMIN"));
        //roleRepository.save(new Role("USER"));
        Role adminRole = roleRepository.findRoleByRole("ADMIN");
        if (adminRole == null) {
            Role newAdminRole = new Role();
            newAdminRole.setRole("ADMIN");
            roleRepository.save(newAdminRole);
        }

        Role userRole = roleRepository.findRoleByRole("USER");
        if (userRole == null) {
            Role newUserRole = new Role();
            newUserRole.setRole("USER");
            roleRepository.save(newUserRole);
        }

        if(userRepository.findUserByEmail("123@123") == null){
            userRepository.save(new User("123@123", new BCryptPasswordEncoder().encode("123@123"), "123@123" , true,  new HashSet<>(Arrays.asList(roleRepository.findRoleByRole("ADMIN") ) ) ));
        }
        if (userRepository.findUserByEmail("user@user.com") == null) {
            userRepository.save(new User("user@user.com", new BCryptPasswordEncoder().encode("user@user.com"), "user@user.com" , true,  new HashSet<>(Arrays.asList(roleRepository.findRoleByRole("USER") ) ) ));

        }
        System.out.println("DB LOADED: USERS: " + userRepository.count() + " ROLES: " + roleRepository.count());
    }
}
