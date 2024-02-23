package boss.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "favorites")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Favorite extends BaseEntity{

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;


    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Product product;

}
