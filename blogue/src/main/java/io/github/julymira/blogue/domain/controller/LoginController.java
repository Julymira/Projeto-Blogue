package io.github.julymira.blogue.domain.controller;

import io.github.julymira.blogue.domain.model.bo.UserBO;
import io.github.julymira.blogue.domain.model.dto.UserLoginDTO;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/LoginController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginController {

    @Inject
    UserBO userBO;
    @Inject
    Template login;
    @Path("LoginPage")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getView() {
        return login.instance();  // Passando dados para o template
    }

    @Path("/FormLogin")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response LoginUser(
            @FormParam("email") String email,
            @FormParam("senha") String senha) {

        UserLoginDTO userLoginDTO = new UserLoginDTO(email, senha);
        userBO.login(userLoginDTO);

        return Response.ok("Usuário salvo com sucesso!").build();
    }
}
