package boss.repo;


import boss.dto.response.ProductResponse;
import boss.entities.Product;
import boss.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    @Query("select new boss.dto.response.ProductResponse(p.id,p.name,p.price,p.characteristic,p.isFavorite,p.madeIn,p.category) from Product p")
    List<ProductResponse> getAllProducts();

//    Optional<ProductResponse> getProductById(Long id);
}
