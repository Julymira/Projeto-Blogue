package io.github.julymira.blogue.domain.controller;

import io.github.julymira.blogue.domain.model.bo.UserBO;
import io.github.julymira.blogue.domain.model.dto.ResponseError;
import io.github.julymira.blogue.domain.model.dto.UserLoginDTO;
import io.github.julymira.blogue.domain.model.entity.User;
import io.github.julymira.blogue.domain.repository.UserRepository;
import io.github.julymira.blogue.domain.model.dto.UserRegisterDTO;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Set;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    //usando repositório panache temporariamente para fim de testes
    private final UserRepository repository;
    private final Validator validator;

    @Inject
    public UserResource(UserRepository repository, Validator validator){

        this.repository = repository;
        this.validator = validator;
    }

    @Inject
    UserBO userBO;


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

    @Path("/Login")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response LoginUser(
            @FormParam("email") String email,
            @FormParam("senha") String senha) {

        UserLoginDTO userLoginDTO = new UserLoginDTO(email, senha);
        userBO.login(userLoginDTO);

        return Response.ok("Usuário salvo com sucesso!").build();
    }


    //usando repositório panache temporariamente para fim de testes
    @GET
    public  Response listAllUsers(){
        PanacheQuery<User> query = repository.findAll();
        return Response.ok(query.list()).build();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    //  /users/id
    public Response deleteUser( @PathParam("id") Long id){
        User user = repository.findById(id);

        if(user != null){
            repository.delete(user);
            return Response.noContent().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response updateUser( @PathParam("id") Long id, UserRegisterDTO userData){
        User user = repository.findById(id);

        if(user != null){
            user.setName(userData.getName());
            user.setEmail(userData.getEmail());
            user.setPassword(userData.getPassword());
            return Response.noContent().build();
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

}
