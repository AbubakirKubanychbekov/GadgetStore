package boss.entities;

import boss.enums.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product extends BaseEntity{

    private String name;

    private BigDecimal price;

    @ElementCollection
    private List<String>images;

    private String characteristic;

    private boolean isFavorite;

    private String madeIn;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    private Brand brand;

    @ManyToMany(mappedBy = "products", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Basket>baskets;

    @OneToMany(mappedBy = "product",
    fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Comment> comments;


    @OneToMany(mappedBy = "product",
    fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Favorite> favorites;



}
