package boss.services;

import boss.dto.request.ProductRequest;
import boss.dto.response.PaginationResponse;
import boss.dto.response.ProductResponse;
import boss.dto.simpleResponse.SimpleResponse;

import java.util.List;

public interface ProductService {

    List<ProductResponse> getAllProducts();

    SimpleResponse save(ProductRequest productRequest, Long brandId);

    SimpleResponse update(Long id, ProductRequest productRequest);

    ProductResponse getProductById(Long id);
    SimpleResponse deleteProduct(Long id);

    PaginationResponse getAllPagination(int currentPage, int pageSize);



}
