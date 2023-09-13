package boss.api;

import boss.dto.request.ProductRequest;
import boss.dto.response.ProductResponse;
import boss.dto.response.ProductWithCommentsResponse;
import boss.dto.simpleResponse.SimpleResponse;
import boss.services.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Tag(name = "ProductApi")
public class ProductApi {

    private final ProductService productService;

    @GetMapping("/getAll")
    @PermitAll
    public List<ProductResponse> findAllProducts() {
        return productService.getAllProducts();
    }


//    @GetMapping("/getAll")
//    public ResponseEntity<List<ProductResponse>> getAllProducts() {
//        System.out.println("hello");
//        List<ProductResponse> products = productService.getAllProducts();
//        System.out.println("hello2");
//        return new ResponseEntity<>(products, HttpStatus.OK);
//    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{brandId}")
    public SimpleResponse save(@PathVariable Long brandId,
                               @RequestBody ProductRequest productRequest) {
        return productService.save(productRequest, brandId);
    }


    @GetMapping("/{id}")
    @Secured("USER")
    public ProductResponse getById(@PathVariable Long id) {
        return productService.getProductById(id);
    }


    @Secured("ADMIN")
    @PutMapping("/{id}")
    public SimpleResponse update(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        return productService.update(id, productRequest);
    }


    @GetMapping("/details/{productId}")
    public ResponseEntity<ProductWithCommentsResponse> getProductById(@PathVariable Long productId) {
        ProductWithCommentsResponse response = productService.getProductByIdAndComments(productId);

        if (response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
