package io.github.minhaz1217.spring_barebone_login.Model;


import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;

@Document(collection = "User")
public class User {
    String id;
    String username;
    String password;

    private User(){}
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
