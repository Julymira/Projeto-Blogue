package io.github.julymira.blogue.domain.controller;

import io.github.julymira.blogue.domain.model.bo.UserBO;
import io.github.julymira.blogue.domain.model.dto.ResponseError;
import io.github.julymira.blogue.domain.model.dto.UserLoginDTO;
import io.github.julymira.blogue.domain.model.dto.UserRegisterDTO;
import io.github.julymira.blogue.domain.model.entity.User;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

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
    @Produces(MediaType.APPLICATION_JSON)
    public  Response listAllUsers(){
        List<User> users = userBO.listAllUsers();
        return Response.ok(users).build();
    }


}
