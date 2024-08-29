package io.github.julymira.blogue.domain.controller;

import io.github.julymira.blogue.domain.model.bo.UserBO;
import io.github.julymira.blogue.domain.model.dto.UserRegisterDTO;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/pathRegisterController")
@Transactional
@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
public class RegisterResource {

    @Inject
    UserBO userBO;

    @Path("/save")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response saveUser(
            @FormParam("name") String name,
            @FormParam("email") String email,
            @FormParam("senha") String senha) {

        UserRegisterDTO userRegisterDTO = new UserRegisterDTO(name, email, senha);
        userBO.saveUser(userRegisterDTO);

        return Response.ok("Usuário salvo com sucesso!").build();
    }

}
