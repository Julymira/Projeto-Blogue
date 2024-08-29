package io.github.julymira.blogue.domain.controller;


import io.github.julymira.blogue.domain.model.bo.PostBO;
import io.github.julymira.blogue.domain.model.dto.CreatePostRequest;
import io.github.julymira.blogue.domain.model.dto.PostResponse;
import io.github.julymira.blogue.domain.model.entity.Post;
import io.github.julymira.blogue.domain.model.entity.User;
import io.github.julymira.blogue.domain.model.dao.PostDAO;
import io.github.julymira.blogue.domain.repository.UserRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Path("/users/{userId}/posts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PostResource {

    @Inject
    UserRepository userRepository;
    @Inject
    PostBO postBO;

    @POST
    @Transactional
    public Response savePost(@PathParam("userId") Long userId, CreatePostRequest request) {
        User user = userRepository.findById(userId);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Post post = new Post();
        post.setText(request.getText());
        post.setImageUrl(request.getImageUrl());
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
    public Response listPosts(@PathParam("userId") Long userId) {
        User user = userRepository.findById(userId);
        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        List<Post> posts = postBO.getPostDAO().findByUser(user);
        List<PostResponse> postResponses = posts.stream()
                .map(PostResponse::fromEntity)
                .collect(Collectors.toList());

        return Response.ok(postResponses).build();
    }

}
