package boss.services;

import boss.dto.request.ProductRequest;
import boss.dto.response.ProductResponse;
import boss.dto.simpleResponse.SimpleResponse;
import boss.entities.Product;
import boss.enums.Category;

import java.util.List;

public interface ProductService {

    List<ProductResponse> getAllProducts(String ascOrDesc, Category category);

    SimpleResponse save(ProductRequest productRequest, Long brandId);
    ProductResponse getProductById(Long id);

    SimpleResponse update(Long id, ProductRequest productRequest);


    SimpleResponse deleteProduct(Long id);

    List<ProductResponse> findAllProducts();

}


