package io.github.julymira.blogue.domain.model.bo;


import io.github.julymira.blogue.domain.model.dao.UserDAO;
import io.github.julymira.blogue.domain.model.dto.UserLoginDTO;
import io.github.julymira.blogue.domain.model.entity.User;
import io.github.julymira.blogue.domain.model.dto.UserRegisterDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import io.smallrye.jwt.build.Jwt;
import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;
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

         String token = Jwt.issuer("blogue")
                        .subject("blogue")
                         .groups(new HashSet<>(Arrays.asList("admin", "writer")))
                        .expiresAt(System.currentTimeMillis() + 3600)
                        .sign();

        /*

        String token = Jwt.issuer("blogue")
                        .subject("blogue")
                        .claim("userId", userId)  // Adiciona o userId como uma claim
                        .expiresAt(System.currentTimeMillis() + 3600)
                        .sign();

         */

        //NewCookie jwtCookie = new NewCookie("jwt-token", token, "/", null, "JWT Token", 3600, false, true);

        //return Response.ok("Logado com sucesso!").cookie(jwtCookie).build();

        return token;
    }

    public Response logout(){

        NewCookie jwtCookie = new NewCookie("jwt-token", "", "/", null, "JWT Token",
                0, false, true);

        return Response.ok("Logout Efetuado").cookie(jwtCookie).build();
    }


    public List<User> listAllUsers() {
        return userDAO.listAll();
    }


}
