package io.github.minhaz1217.spring_barebone_login.Service;


import io.github.minhaz1217.spring_barebone_login.Model.Role;
import io.github.minhaz1217.spring_barebone_login.Model.User;
import io.github.minhaz1217.spring_barebone_login.Repository.RoleRepository;
import io.github.minhaz1217.spring_barebone_login.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomUserDetailsService implements UserDetailsService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public User findUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

    public void saveUser(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        Role userRole = roleRepository.findRoleByRole("ADMIN");
        user.setRoles(new HashSet<>(Arrays.asList(userRole)));
        userRepository.save(user);

    }

    private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles){
        Set<GrantedAuthority> roles = new HashSet<>();
        userRoles.forEach((role)->{
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        });
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }
    private UserDetails buildUserFOrAuthentication(User user, List<GrantedAuthority> authorities){
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email);
        if(user != null){
            List<GrantedAuthority> authorities = getUserAuthority(user.getRoles());
            return buildUserFOrAuthentication(user, authorities);
        }else{
            throw new UsernameNotFoundException("User not found");
        }
    }
}
