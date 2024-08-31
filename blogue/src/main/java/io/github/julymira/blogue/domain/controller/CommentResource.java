package io.github.julymira.blogue.domain.controller;

import io.github.julymira.blogue.domain.model.bo.CommentBO;
import io.github.julymira.blogue.domain.model.dao.PostDAO;
import io.github.julymira.blogue.domain.model.dao.UserDAO;
import io.github.julymira.blogue.domain.model.dto.CommentResponse;
import io.github.julymira.blogue.domain.model.dto.CreateCommentRequest;
import io.github.julymira.blogue.domain.model.entity.Comment;
import io.github.julymira.blogue.domain.model.entity.Post;
import io.github.julymira.blogue.domain.model.entity.User;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.stream.Collectors;

@Path("/Comment")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {

    @Inject
    CommentBO commentBO;

    @Inject
    PostDAO postDAO;

    @Inject
    UserDAO userDAO;

    @POST
    @Path("/{postId}/{userId}/AddComment")
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response saveComment(
            @PathParam("postId") Long postId,
            @PathParam("userId") Long userId,
            CreateCommentRequest request
    ){
        User user = userDAO.findById(userId);
        Post post = postDAO.findById(postId);

        if (user == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        if (post == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        Comment comment = new Comment();

        comment.setComment_text(request.getComment_text());
        comment.setPost(post);
        comment.setUser(user);

        try {
            commentBO.saveComment(comment);
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }

        return Response.status(Response.Status.CREATED).build();

    }


    @GET
    @Path("/{postId}/comments")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listComments(@PathParam("postId") Long postId) {
        Post post = postDAO.findById(postId);
        if (post == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        List<Comment> comments = commentBO.getCommentDAO().findByPost(post);
        List<CommentResponse> commentResponses = comments.stream()
                .map(CommentResponse::fromEntity)
                .collect(Collectors.toList());

        return Response.ok(commentResponses).build();
    }





}
