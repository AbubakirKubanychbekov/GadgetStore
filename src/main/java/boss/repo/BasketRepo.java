package boss.repo;

import boss.entities.Basket;
import boss.entities.Product;
import boss.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BasketRepo extends JpaRepository<Basket,Long> {

}
