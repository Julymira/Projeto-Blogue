package io.github.julymira.blogue.domain.controller;


import io.github.julymira.blogue.domain.model.bo.PostBO;
import io.github.julymira.blogue.domain.model.dao.UserDAO;
import io.github.julymira.blogue.domain.model.dto.CreatePostRequest;
import io.github.julymira.blogue.domain.model.dto.PostResponse;
import io.github.julymira.blogue.domain.model.entity.Post;
import io.github.julymira.blogue.domain.model.entity.User;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PostResource {

    @Inject
    PostBO postBO;

    @Inject
    UserDAO userDAO;

    @POST
    @Path("/{userId}/AddPosts")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response savePost(@PathParam("userId") Long userId, CreatePostRequest request) {
        User user = userDAO.findById(userId);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Post post = new Post();
        post.setImageUrl(request.getImageUrl());
        post.setTitle(request.getTitle());
        post.setText(request.getText());
        post.setUser(user);
        post.setDateTime(LocalDateTime.now());

        try {
            postBO.savePost(post);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/AllPosts")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAllPosts() {

        List<Post> posts = postBO.getPostDAO().listAllP();
        List<PostResponse> postResponses = posts.stream()
                .map(PostResponse::fromEntity)
                .collect(Collectors.toList());

        return Response.ok(postResponses).build();

    }

    /*
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll(@QueryParam("titulo") String tituloFiltro) {
        List<Post> postF = postBO.listAll(tituloFiltro);
        return Response.ok(postF).build();
    }
     */

    @GET
    @Path("/filter")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listAll(@QueryParam("search") String search) {
        List<Post> posts = postBO.listAll(search);
        return Response.ok(posts).build();
    }

    @GET
    @Path("/{userId}/posts")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listPosts(@PathParam("userId") Long userId) {
        User user = userDAO.findById(userId);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        List<Post> posts = postBO.getPostDAO().findByUser(user);
        List<PostResponse> postResponses = posts.stream()
                .map(PostResponse::fromEntity)
                .collect(Collectors.toList());

        return Response.ok(postResponses).build();
    }


    @GET
    @Path("/posts/{postId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPostById(@PathParam("postId") Long postId) {
        Post post = postBO.getPostDAO().findById(postId);
        if (post == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(PostResponse.fromEntity(post)).build();
    }


}
