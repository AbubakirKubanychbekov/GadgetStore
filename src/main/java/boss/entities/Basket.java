package boss.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "baskets")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Basket extends BaseEntity {

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Product>products;


    @OneToOne(cascade = CascadeType.ALL)
    private User user;


}
