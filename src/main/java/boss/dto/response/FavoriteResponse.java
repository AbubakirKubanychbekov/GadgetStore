package boss.dto.response;

import boss.entities.Product;
import boss.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
public record FavoriteResponse(
        Long id,
        Product product,
        User user
) {
    public FavoriteResponse(Long id, Product product, User user) {
        this.id = id;
        this.product = product;
        this.user = user;
    }
}
