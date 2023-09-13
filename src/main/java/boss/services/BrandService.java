package boss.services;

import boss.dto.request.BrandRequest;
import boss.dto.response.BrandResponse;
import boss.dto.simpleResponse.SimpleResponse;

import java.util.List;

public interface BrandService {


    List<BrandResponse> findAllBrands();

    BrandResponse save(BrandRequest brandRequest);

    BrandResponse getBrandById(Long id);

    SimpleResponse updateBrand(Long id, BrandRequest brandRequest);

    SimpleResponse deleteBrand(Long id);
}
