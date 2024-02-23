package boss.services.impl;

import boss.dto.request.ProductRequest;
import boss.dto.response.ProductResponse;
import boss.dto.simpleResponse.SimpleResponse;
import boss.entities.Brand;
import boss.entities.Product;
import boss.enums.Category;
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
    public List<ProductResponse> getAllProducts(String ascOrDesc, Category category) {
        if (ascOrDesc.equalsIgnoreCase("asc") || ascOrDesc.equalsIgnoreCase("desc")) {
            if (ascOrDesc.equalsIgnoreCase("asc")) {
                log.info("first method run");
                List<ProductResponse> allByCategoryAndPriceAsc = productRepo.findAllByCategoryAndPriceAsc(category);
                for (ProductResponse p : allByCategoryAndPriceAsc) {
                    p.setImages(productRepo.getImages(p.getId()));

                }
                return allByCategoryAndPriceAsc;
            } else if (ascOrDesc.equalsIgnoreCase("desc")) {
                log.info("second method run");
                List<ProductResponse> allByCategoryAndPriceDesc = productRepo.findAllByCategoryAndPriceDesc(category);
                for (ProductResponse p : allByCategoryAndPriceDesc) {
                    p.setImages(productRepo.getImages(p.getId()));
                }
                return allByCategoryAndPriceDesc;
            }
        } else {
            throw new NotFoundException("Input wrong correctly(asc,desc)");
        }
        return null;
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
        ProductResponse productResponse = productRepo.getProductById(id).orElseThrow(() ->
                new NotFoundException("Product with id: " + id + " not found"));

        System.out.println("productResponse.getId() = " + productResponse.getId());
        List<String> images = productRepo.getImages(id);
        images.forEach(System.out::println);
        productResponse.setImages(images);
        return productResponse;
    }


    @Override
    public List<ProductResponse> findAllProducts() {
        List<ProductResponse> allProducts = productRepo.findAllProducts();
        for (ProductResponse prod : allProducts) {
            prod.setImages(productRepo.getImages(prod.getId()));
        }
        return allProducts;
    }

    @Override
    public SimpleResponse deleteProduct(Long id) {
       if (!productRepo.existsById(id)){
           throw new NotFoundException("Product with id: "+id+ "not found");
       }
       productRepo.deleteById(id);
       log.info("Product is deleted");
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("Product with id: "+id+" is D e l e t e d")
                .build();
    }


}