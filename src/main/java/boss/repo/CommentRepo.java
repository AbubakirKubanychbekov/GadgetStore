package boss.repo;

import boss.dto.response.CommentResponse;
import boss.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepo extends JpaRepository<Comment,Long> {

    @Query("select new boss.dto.response.CommentResponse(c.id,c.comment) from Comment c")
    List<CommentResponse> findAllComments();

    Optional<CommentResponse> findCommentById(Long id);


    @Query("SELECT new boss.dto.response.CommentResponse(c.id, c.comment) FROM Comment c WHERE c.comment = :comment")
    Optional<Comment> findByComment(String comment);

    @Query("SELECT c FROM Comment c WHERE c.product.id = :productId")
    List<Comment> findAllByProductId(@Param("productId") Long id);
}
