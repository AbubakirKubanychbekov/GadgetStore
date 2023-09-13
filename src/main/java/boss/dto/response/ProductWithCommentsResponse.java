package boss.dto.response;

import boss.entities.Comment;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductWithCommentsResponse {
    private ProductResponse productResponse;
    private List<Comment> comments;


    public ProductWithCommentsResponse(ProductResponse productResponse, List<Comment> comments) {
        this.productResponse = productResponse;
        this.comments = comments;
    }
}
