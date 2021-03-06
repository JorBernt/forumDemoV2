package com.springprojects.forumdemo.repositories;

import com.springprojects.forumdemo.model.user.LoginCredentials;
import com.springprojects.forumdemo.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.security.SecureRandom;

@Repository
public class UserRepository {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10, new SecureRandom());
    @Autowired
    private JdbcTemplate db;

    public boolean registerUser(User user) {
        String sql = "INSERT INTO Users VALUES(?,?,?)";
        String pass = passwordEncoder.encode(user.getPassword());
        try {
            db.update(sql, user.getUsername(), user.getEmail(), pass);
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public boolean checkCredentials(LoginCredentials credentials) {
        String sql = "Select password from Users where username = ?";
        try {
            String password = db.queryForObject(sql, String.class, credentials.getUsername());
            return passwordEncoder.matches(credentials.getPassword(), password);
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isUsernameAvailable(String username) {
        String sql = "SELECT COUNT(*) FROM Users where username = ?";
        try {
            return db.queryForObject(sql, Integer.class, username) == 0;
        } catch (Exception e) {
            return false;
        }

    }
}
