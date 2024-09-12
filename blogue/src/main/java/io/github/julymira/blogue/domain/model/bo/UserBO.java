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
import org.eclipse.microprofile.jwt.Claims;
import org.mindrot.jbcrypt.BCrypt;

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


    public Response login(UserLoginDTO userLoginDTO) {
        User user = userDAO.findByEmail(userLoginDTO.getEmail());
        if (user == null || !BCrypt.checkpw(userLoginDTO.getPassword(), user.getPassword())) {
            throw new NotAuthorizedException("email ou senha inválidos");
        }


         String token = Jwt.issuer("http://localhost:8080")
                        .subject("blogue")
                        .groups("admin")
                        .upn("jdoe@quarkus.io")
                        .claim(Claims.email, userLoginDTO.getEmail())
                        .expiresAt(System.currentTimeMillis() + 3600)
                        .sign();



        return Response.status(Response.Status.OK).entity(token).build();
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
