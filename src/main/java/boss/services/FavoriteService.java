package boss.services;

import boss.dto.request.FavoriteRequest;
import boss.dto.response.FavoriteResponse;
import boss.dto.simpleResponse.SimpleResponse;

import java.util.List;

public interface FavoriteService {
    List<FavoriteResponse> findAllFavorites();

    SimpleResponse save(Long productId, FavoriteRequest favoriteRequest);


    FavoriteResponse getFavoriteById(Long id);

    SimpleResponse deleteFavorite(Long id);
}
