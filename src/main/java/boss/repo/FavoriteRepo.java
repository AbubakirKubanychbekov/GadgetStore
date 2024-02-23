package boss.repo;

import boss.dto.response.FavoriteResponse;
import boss.entities.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteRepo extends JpaRepository<Favorite,Long> {

    @Query("select new boss.dto.response.FavoriteResponse(f.id,p.name,u.firstName) from Favorite f join f.product p join f.user u where p.id =:id")
    List<FavoriteResponse> findAllFavorites(@Param("id") Long id);
}
