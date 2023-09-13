package boss.dto.request;

import boss.entities.Favorite;
import lombok.Builder;

@Builder
public record FavoriteRequest(
        Long id

) {

    public Favorite build() {
        Favorite favorite= new Favorite();
        favorite.setId(this.id);
        return favorite;
    }
}
