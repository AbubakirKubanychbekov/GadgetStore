package boss.services;

import boss.dto.simpleResponse.SimpleResponse;

import java.util.List;

public interface BasketService {

    SimpleResponse saveBasket(List<Long> productId);

}
