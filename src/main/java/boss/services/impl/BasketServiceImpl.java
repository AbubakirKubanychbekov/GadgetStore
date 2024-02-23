package boss.services.impl;

import boss.dto.simpleResponse.SimpleResponse;
import boss.entities.Basket;
import boss.entities.Product;
import boss.entities.User;
import boss.exception.BadRequestException;
import boss.exception.NotFoundException;
import boss.repo.BasketRepo;
import boss.repo.ProductRepo;
import boss.repo.UserRepo;
import boss.services.BasketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BasketServiceImpl implements BasketService {

    private final UserRepo userRepo;
    private final BasketRepo basketRepo;
    private final ProductRepo productRepo;

    @Override
    public SimpleResponse saveBasket(List<Long> productId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepo.getUserByEmail(email).orElseThrow(() -> new NotFoundException("User with email: %s not found".formatted(email)));
        List<Product> products = new ArrayList<>();
        for (Long l : productId) {
            products.add(productRepo.findById(l).orElseThrow(
                    () -> new BadRequestException("User with id: %s not found".formatted(l))
            ));
        }
        Basket basket = Basket
                .builder()
                .products(products)
                .user(user)
                .build();
        basketRepo.save(basket);
        log.info("Basket successfully S a v e d");
        return SimpleResponse
                .builder()
                .message("Products successfully saved")
                .httpStatus(HttpStatus.OK)
                .build();
    }

}
