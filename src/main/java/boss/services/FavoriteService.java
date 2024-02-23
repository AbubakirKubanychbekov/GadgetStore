package boss.services;

import boss.dto.response.FavoriteResponse;
import boss.dto.simpleResponse.SimpleResponse;

import java.util.List;

public interface FavoriteService {
    List<FavoriteResponse> findAllFavorites(Long id);

    SimpleResponse save(Long productId);


}
