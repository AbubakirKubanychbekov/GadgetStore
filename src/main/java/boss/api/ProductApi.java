package boss.api;

import boss.dto.request.ProductRequest;
import boss.dto.response.ProductResponse;
import boss.dto.simpleResponse.SimpleResponse;
import boss.enums.Category;
import boss.services.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/AscOrDescCategory")
    @PermitAll
    @Operation(summary = "Asc or Desc for Product",description = "Write in descending order")
    public List<ProductResponse> findAllProducts(@RequestParam String ascOrDesc,
                                                 @RequestParam Category category) {
        return productService.getAllProducts(ascOrDesc,category);
    }


    @GetMapping("/findAll")
    @PermitAll
    @Operation(summary = "Get All",description = "Get All favorite info")
    public List<ProductResponse> findAll(){
        return productService.findAllProducts();
    }



    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{brandId}")
    @Operation(summary = "Save",description = "To save  fill all the fields!")
    public SimpleResponse save(@PathVariable Long brandId,
                               @RequestBody ProductRequest productRequest) {
        return productService.save(productRequest, brandId);
    }


    @GetMapping("/{id}")
    @PermitAll
    @Operation(summary = "Get product by ID",description = "To get by ID fill ID!")
    public ProductResponse getById(@PathVariable Long id) {
        return productService.getProductById(id);
    }


    @Secured("ADMIN")
    @PutMapping("/{id}")
    @Operation(summary = "Update",description = "To update fill all the fields!")
    public SimpleResponse update(@PathVariable Long id, @RequestBody ProductRequest productRequest) {
        return productService.update(id, productRequest);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Deleted",description = "To delete  fill all the fields!")
    public SimpleResponse deleteProduct(@PathVariable Long id){
        return productService.deleteProduct(id);
    }

}
