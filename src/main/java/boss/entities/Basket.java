package boss.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "baskets")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Basket extends BaseEntity {

    @ManyToMany
    private List<Product>products;

    @OneToOne
    private User user;
}
