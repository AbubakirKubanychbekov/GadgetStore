package boss.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "brands")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Brand extends BaseEntity{

    private String brandName;

    private String image;

    @OneToMany(mappedBy = "brand",cascade = CascadeType.ALL)
    private List<Product>products;

}
