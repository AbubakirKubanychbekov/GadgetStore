package boss.services;

import boss.dto.request.CommentRequest;
import boss.dto.response.CommentResponse;
import boss.dto.simpleResponse.SimpleResponse;

import java.util.List;

public interface CommentService {
    List<CommentResponse> findAllComments();

    CommentResponse save(CommentRequest commentRequest,Long productId);

    CommentResponse findCommentById(Long id);

//    SimpleResponse updateComment(String comment, CommentRequest commentRequest);


    SimpleResponse update(Long id, CommentRequest commentRequest);

    SimpleResponse deleteComment(Long id);
}
