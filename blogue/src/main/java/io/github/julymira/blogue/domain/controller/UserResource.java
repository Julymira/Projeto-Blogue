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

    private final UserRepository repository;
    private final Validator validator;

    @Inject
    public UserResource(UserRepository repository, Validator validator){

        this.repository = repository;
        this.validator = validator;
    }

    @Inject
    UserBO userBO;


    @POST
    @Path("/register")
    @Transactional
    public Response createUser(UserRegisterDTO userRegisterDTO){
        userBO.saveUser(userRegisterDTO);
        return Response.ok().build();
    }


    @POST
    @Path("/login")
    public Response login(UserLoginDTO userLoginDTO){
        String token = userBO.login(userLoginDTO);

        return Response.ok(token).build();
    }

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
