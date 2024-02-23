package boss.repo;


import boss.dto.response.ProductResponse;
import boss.entities.Product;
import boss.enums.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
   @Query("select new boss.dto.response.ProductResponse(p.id,p.name,p.price,p.characteristic,p.isFavorite,p.madeIn,p.category, p.brand.brandName) from Product p where p.id = :id")
    Optional<ProductResponse> getProductById(Long id);

   @Query("SELECT NEW boss.dto.response.ProductResponse(p.id,p.name,p.price,p.characteristic,p.isFavorite,p.madeIn,p.category,p.brand.brandName) FROM Product p WHERE p.category = ?1 ORDER BY p.price asc ")
    List<ProductResponse> findAllByCategoryAndPriceAsc(Category category);

    @Query("SELECT NEW boss.dto.response.ProductResponse(p.id,p.name,p.price,p.characteristic,p.isFavorite,p.madeIn,p.category,p.brand.brandName) FROM Product p WHERE p.category = ?1 ORDER BY p.price desc ")
    List<ProductResponse> findAllByCategoryAndPriceDesc(Category category);




    @Query("select p.images from Product p where p.id=?1")
    List<String> getImages(Long id);



    @Query("select new boss.dto.response.ProductResponse(p.id,p.name,p.price,p.characteristic,p.isFavorite,p.madeIn,p.category, p.brand.brandName) from Product p")
    List<ProductResponse> findAllProducts();

}
