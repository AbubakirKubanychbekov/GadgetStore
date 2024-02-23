package boss.dto.response;

import boss.enums.Category;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ProductResponse {
   private Long id;

    private String name;

    private  BigDecimal price;

    private List<String> images;

    private String characteristic;

    private boolean isFavorite;

    private String madeIn;

    private Category categoryName;

    private String brandName;

    public ProductResponse(Long id, String name, BigDecimal price, String characteristic, boolean isFavorite, String madeIn, Category categoryName, String brandName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.characteristic = characteristic;
        this.isFavorite = isFavorite;
        this.madeIn = madeIn;
        this.categoryName = categoryName;
        this.brandName = brandName;
    }

    public ProductResponse(Long id, String name, BigDecimal price, List<String> images, String characteristic, boolean isFavorite, String madeIn, Category categoryName, String brandName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.images = images;
        this.characteristic = characteristic;
        this.isFavorite = isFavorite;
        this.madeIn = madeIn;
        this.categoryName = categoryName;
        this.brandName = brandName;
    }
}