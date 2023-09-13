package boss.services.impl;

import boss.dto.request.ProductRequest;
import boss.dto.response.ProductResponse;
import boss.dto.response.ProductWithCommentsResponse;
import boss.dto.simpleResponse.SimpleResponse;
import boss.entities.Brand;
import boss.entities.Comment;
import boss.entities.Product;
import boss.exception.NotFoundException;
import boss.repo.BrandRepo;
import boss.repo.CommentRepo;
import boss.repo.ProductRepo;
import boss.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepo productRepo;
    private final BrandRepo brandRepo;
    private final CommentRepo commentRepo;

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepo.getAllProducts();
    }

    @Override
    public SimpleResponse save(ProductRequest productRequest, Long brandId) {
        Brand brand = brandRepo.findById(brandId).orElseThrow(() ->
                new NotFoundException("Brand with id: " + brandId + " not found"));

        Product product = new Product();
        product.setName(productRequest.name());
        product.setPrice(productRequest.price());
        product.setImages(productRequest.images());
        product.setCharacteristic(productRequest.characteristic());
        product.setFavorite(productRequest.isFavorite());
        product.setMadeIn(productRequest.madeIn());
        product.setCategory(productRequest.category());
        product.setBrand(brand);
        productRepo.save(product);
        log.info("Product successfully saved");
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Product successfully S a v e d")
                .build();
    }

    @Override
    public SimpleResponse update(Long id, ProductRequest productRequest) {
        Product product = productRepo.findById(id).orElseThrow(() ->
                new NotFoundException(String.format("Product with id: %s not found", id)));
        product.setName(productRequest.name());
        product.setPrice(productRequest.price());
        product.setImages(productRequest.images());
        product.setCharacteristic(productRequest.characteristic());
        product.setFavorite(true);
        product.setMadeIn(productRequest.madeIn());
        product.setCategory(productRequest.category());
        log.info("Product successfully updated");
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Product successfully updated")
                .build();
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product = productRepo.findById(id).orElseThrow(() ->
                new NotFoundException("Product with id: " + id + " not found"));
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setPrice(product.getPrice());
        productResponse.setImages(product.getImages());
        productResponse.setCharacteristic(product.getCharacteristic());
        productResponse.setFavorite(true);
        productResponse.setMadeIn(product.getMadeIn());
        productResponse.setCategory(product.getCategory());
        return productResponse;
    }

    @Override
    public ProductWithCommentsResponse getProductByIdAndComments(Long id) {

        Product product = productRepo.findById(id).orElseThrow(() ->
                new NotFoundException("Product with id: " + id + " not found"));

        // Создать объект ProductResponse и заполнить его данными о товаре
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setPrice(product.getPrice());
        productResponse.setImages(product.getImages());
        productResponse.setCharacteristic(product.getCharacteristic());
        productResponse.setFavorite(true); // Предположим, что это фиктивное значение для "избранного"
        productResponse.setMadeIn(product.getMadeIn());
        productResponse.setCategory(product.getCategory());

        // Получить все комментарии для данного продукта
        List<Comment> comments = commentRepo.findAllByProductId(id);

        // Создать объект ProductWithCommentsResponse и установить в него ProductResponse и список комментариев
        ProductWithCommentsResponse response = new ProductWithCommentsResponse(productResponse, comments);

        return response;

    }
}