package com.springprojects.forumdemo.controllers;

import com.springprojects.forumdemo.model.user.LoginCredentials;
import com.springprojects.forumdemo.model.user.User;
import com.springprojects.forumdemo.model.user.Validate;
import com.springprojects.forumdemo.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
public class UserController {

    @Autowired
    private UserRepository repo;
    @Autowired
    private HttpSession session;

    private final Logger logger = LoggerFactory.getLogger(UserController.class);


    @PostMapping("/registerUser")
    public void registerUser(User user, HttpServletResponse response) throws IOException {
        if (!Validate.validateUser(user)) {
            response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Invalid credentials");
            logger.error("Could not validate user credentials");
        } else {
            if (!repo.registerUser(user)) {
                response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error in database, try again later");
                logger.error("Could not create user in DB");
            }
        }
    }

    @GetMapping("/loginUser")
    public boolean login(LoginCredentials credentials) {
        if (repo.checkCredentials(credentials)) {
            session.setAttribute("LogIn", credentials.getUsername());
            return true;
        } else {
            return false;
        }
    }

    @GetMapping("/getSession")
    public boolean getSession() {
        return session.getAttribute("LogIn") != null;
    }

    @GetMapping("/logOut")
    public void logout() {
        session.removeAttribute("LogIn");
    }

    @GetMapping("/sessionUserName")
    public String getSessionUserName() {
        return (String) session.getAttribute("LogIn");
    }

    @GetMapping("/usernameAvailable")
    public boolean isUsernameAvailable(String username) {
        return repo.isUsernameAvailable(username);
    }


}
