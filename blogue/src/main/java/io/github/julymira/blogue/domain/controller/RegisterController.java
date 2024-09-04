package io.github.julymira.blogue.domain.controller;

import io.github.julymira.blogue.domain.model.bo.UserBO;
import io.github.julymira.blogue.domain.model.dto.UserLoginDTO;
import io.github.julymira.blogue.domain.model.dto.UserRegisterDTO;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/LoginController")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RegisterController {

    @Inject
    UserBO userBO;
    @Inject
    Template register;
    @Path("RegisterPage")
    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance getView() {
        return register.instance();  // Passando dados para o template
    }

    @Path("/FormRegister")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response RegisterUser(
            @FormParam("nome") String nome,
            @FormParam("email") String email,
            @FormParam("senha") String senha) {

        UserRegisterDTO userRegisterDTO = new UserRegisterDTO(nome,email, senha);
        userBO.saveUser(userRegisterDTO);

        return Response.ok("Usuário salvo com sucesso!").build();
    }
}
