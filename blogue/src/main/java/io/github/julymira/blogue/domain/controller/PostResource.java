package io.github.julymira.blogue.domain.controller;

import io.github.julymira.blogue.domain.controller.dto.CreatePostRequest;
import io.github.julymira.blogue.domain.controller.dto.PostResponse;
import io.github.julymira.blogue.domain.model.Post;
import io.github.julymira.blogue.domain.model.User;
import io.github.julymira.blogue.domain.repository.PostRepository;
import io.github.julymira.blogue.domain.repository.UserRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.time.LocalDateTime;
import java.util.stream.Collectors;


@Path("/users/{userId}/posts")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PostResource {

    private UserRepository userRepository;
    private final PostRepository repository;

    @Inject
    public PostResource(
            UserRepository userRepository,
            PostRepository repository)
        {
        this.userRepository = userRepository;
            this.repository = repository;
        }

    @POST
    @Transactional
    public Response savePost(
            @PathParam("userId") Long userId, CreatePostRequest request){

        User user = userRepository.findById(userId);
        if(user == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Post post = new Post();
        post.setText(request.getText());
        post.setUser(user);
        post.setDateTime(LocalDateTime.now());

        repository.persist(post);

        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    public Response listPosts( @PathParam("userId") Long userId ){
        User user = userRepository.findById(userId);
        if(user == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        var query = repository.find(
                "user", Sort.by("dateTime", Sort.Direction.Descending), user);
        var list = query.list();

        var postResponseList = list.stream()
                .map(post -> PostResponse.fromEntity(post))
                .collect(Collectors.toList());

        return Response.ok(postResponseList).build();
    }


}
