package io.github.julymira.blogue.domain.controller;

import io.github.julymira.blogue.domain.model.bo.UserBO;
import io.github.julymira.blogue.domain.model.dto.UserLoginDTO;
import io.github.julymira.blogue.domain.model.dto.UserRegisterDTO;
import io.github.julymira.blogue.domain.model.entity.User;

import jakarta.annotation.security.RolesAllowed;
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
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response createUser(@FormParam("name") String name,
                               @FormParam("email") String email,
                               @FormParam("password") String password) {
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setName(name);
        userRegisterDTO.setEmail(email);
        userRegisterDTO.setPassword(password);

        userBO.saveUser(userRegisterDTO);


        return Response.ok().build();
    }


    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("admins")
    public Response login(UserLoginDTO userLoginDTO){

        return userBO.login(userLoginDTO);

       }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public  Response listAllUsers(){
        List<User> users = userBO.listAllUsers();
        return Response.ok(users).build();
    }


}
