package boss.services;

import boss.dto.request.ProductRequest;
import boss.dto.response.ProductResponse;
import boss.dto.response.ProductWithCommentsResponse;
import boss.dto.simpleResponse.SimpleResponse;

import java.util.List;

public interface ProductService {
    List<ProductResponse> getAllProducts();

    SimpleResponse save(ProductRequest productRequest, Long brandId);

    SimpleResponse update(Long id, ProductRequest productRequest);

    ProductResponse getProductById(Long id);

    ProductWithCommentsResponse getProductByIdAndComments(Long prod);
}
