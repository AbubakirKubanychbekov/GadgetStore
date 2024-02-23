package boss.dto.response;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class FavoriteResponse {
  private  Long id;
  private  String product;
  private  String user;


  public FavoriteResponse(Long id, String product, String user) {
    this.id = id;
    this.product = product;
    this.user = user;
  }

}
