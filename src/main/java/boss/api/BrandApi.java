package boss.api;

import boss.dto.request.BrandRequest;
import boss.dto.response.BrandResponse;
import boss.dto.simpleResponse.SimpleResponse;
import boss.services.BrandService;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brand")
@RequiredArgsConstructor
@Tag(name = "BrandApi")
public class BrandApi {

    private final BrandService brandService;

    @GetMapping
    public List<BrandResponse> findAllBrand(){
        return brandService.findAllBrands();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public BrandResponse save(@RequestBody BrandRequest brandRequest){
        return brandService.save(brandRequest);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Пример операции", response = BrandResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешный запрос", response = BrandResponse.class),
            @ApiResponse(code = 400, message = "Некорректный запрос", response = BrandResponse.class),
            @ApiResponse(code = 404, message = "Ресурс не найден", response = BrandResponse.class)})
    public BrandResponse getBrandById(@PathVariable Long id){
        return brandService.getBrandById(id);
    }

    @Secured("ADMIN")
    @PutMapping("/{id}")
    public SimpleResponse updateBrand(@PathVariable Long id,
                                      @RequestBody BrandRequest brandRequest){
        return brandService.updateBrand(id,brandRequest);
    }


    @Secured("ADMIN")
    @DeleteMapping("/{id}")
    public SimpleResponse deleteBrand(@PathVariable Long id){
        return brandService.deleteBrand(id);
    }

}
