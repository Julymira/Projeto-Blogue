//package io.github.julymira.blogue.domain.controller;
//
//import io.github.julymira.blogue.domain.model.bo.UserBO;
//import io.github.julymira.blogue.domain.model.dto.UserLoginDTO;
//import jakarta.inject.Inject;
//import jakarta.transaction.Transactional;
//import jakarta.ws.rs.*;
//import jakarta.ws.rs.core.MediaType;
//import jakarta.ws.rs.core.Response;
//
//@Path("/pathLoginController")
//@Transactional
//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//public class LoginResource {
//
//    @Inject
//    UserBO userBO;
//
//    @Path("/Login")
//    @POST
//    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//    public Response LoginUser(
//            @FormParam("email") String email,
//            @FormParam("senha") String senha) {
//
//        UserLoginDTO userLoginDTO = new UserLoginDTO(email, senha);
//        userBO.login(userLoginDTO);
//
//        return Response.ok("Usuário salvo com sucesso!").build();
//    }
//
//}
