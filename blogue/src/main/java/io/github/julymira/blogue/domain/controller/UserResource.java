package io.github.julymira.blogue.domain.controller;

import io.github.julymira.blogue.domain.model.bo.UserBO;
import io.github.julymira.blogue.domain.model.dto.UserRegisterDTO;
import io.github.julymira.blogue.domain.model.entity.User;
import io.github.julymira.blogue.domain.repository.UserRepository;

import jakarta.inject.Inject;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.hibernate.sql.Template;

import java.util.List;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private final UserRepository repository;
    private final Validator validator;

    @Inject
    UserBO userBO;
    @Inject
    Template login;

    @Inject
    public UserResource(UserRepository repository, Validator validator){

        this.repository = repository;
        this.validator = validator;
    }

    @Path("/save")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response saveUser(
            @FormParam("nome") String name,
            @FormParam("email") String email,
            @FormParam("senha") String senha) {

        UserRegisterDTO userRegisterDTO = new UserRegisterDTO(name, email, senha);
        userBO.saveUser(userRegisterDTO);

        return Response.ok("Usuário salvo com sucesso!").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public  Response listAllUsers(){
        List<User> users = userBO.listAllUsers();
        return Response.ok(users).build();
    }

}
