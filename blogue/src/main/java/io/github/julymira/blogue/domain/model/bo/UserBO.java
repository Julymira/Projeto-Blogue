package io.github.julymira.blogue.domain.model.bo;


import io.vertx.ext.auth.impl.jose.JWT;

import io.github.julymira.blogue.domain.model.dao.UserDAO;
import io.github.julymira.blogue.domain.model.dto.UserLoginDTO;
import io.github.julymira.blogue.domain.model.entity.User;
import io.github.julymira.blogue.domain.model.dto.UserRegisterDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import io.smallrye.jwt.build.Jwt;
import jakarta.ws.rs.NotAuthorizedException;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@ApplicationScoped
public class UserBO {
    @Inject
    private UserDAO userDAO;

    public void saveUser(UserRegisterDTO userRegisterDTO) {
        if (userRegisterDTO == null || userRegisterDTO.getEmail() == null || userRegisterDTO.getEmail().isEmpty()) {
            throw new IllegalArgumentException("User or email cannot be null or empty");
        }

        String hashedPassword = BCrypt.hashpw(userRegisterDTO.getPassword(), BCrypt.gensalt());

        User user = new User();
        user.setName(userRegisterDTO.getName());
        user.setEmail(userRegisterDTO.getEmail());

        user.setPassword(hashedPassword); // Armazenar a senha criptografada

        System.out.println("Storing hashed password: " + hashedPassword);

        userDAO.persist(user);
    }


    public String login(UserLoginDTO userLoginDTO) {
        User user = userDAO.findByEmail(userLoginDTO.getEmail());
        if (user == null || !BCrypt.checkpw(userLoginDTO.getPassword(), user.getPassword())) {
            throw new NotAuthorizedException("email ou senha inválidos");
        }

        return Jwt.issuer("blogue")
                .subject("blogue")
                .groups(new HashSet<>(Arrays.asList("admin", "writer")))
                .expiresAt(System.currentTimeMillis() + 3600)
                .sign();
    }


    public List<User> listAllUsers() {
        return userDAO.listAll();
    }

    public boolean deleteUser(Long id) {
        User user = userDAO.findById(id);
        if (user != null) {
            userDAO.delete(user);
            return true;
        }
        return false;
    }

    public User updateUser(Long id, UserRegisterDTO userData) {
        User user = userDAO.findById(id);
        if (user != null) {
            user.setName(userData.getName());
            user.setEmail(userData.getEmail());
            if (userData.getPassword() != null && !userData.getPassword().isEmpty()) {
                user.setPassword(BCrypt.hashpw(userData.getPassword(), BCrypt.gensalt())); // Criptografar senha
            }
            userDAO.persist(user);
            return user;
        }
        return null;
    }

}
