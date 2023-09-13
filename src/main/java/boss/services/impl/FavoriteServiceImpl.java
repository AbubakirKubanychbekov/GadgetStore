package boss.services.impl;

import boss.dto.request.FavoriteRequest;
import boss.dto.response.FavoriteResponse;
import boss.dto.simpleResponse.SimpleResponse;
import boss.entities.Favorite;
import boss.entities.Product;
import boss.entities.User;
import boss.exception.NotFoundException;
import boss.repo.FavoriteRepo;
import boss.repo.ProductRepo;
import boss.repo.UserRepo;
import boss.services.FavoriteService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FavoriteServiceImpl implements FavoriteService {

    private final FavoriteRepo favoriteRepo;
    private final ProductRepo productRepo;
    private final UserRepo userRepo;

    @Override
    public List<FavoriteResponse> findAllFavorites() {
        return favoriteRepo.findAllFavorites();
    }


    @Override
    public SimpleResponse save(Long productId, FavoriteRequest favoriteRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email= authentication.getName();

        User user = userRepo.getUserByEmail(email).orElseThrow(() ->
                new NotFoundException("User with email: " + email + " not found"));
        Product product = productRepo.findById(productId).orElseThrow(() ->
                new NotFoundException("Product with id: " + productId + " not found"));
        Favorite favorite=favoriteRequest.build();
        favorite.setProduct(product);
        user.addFavorite(favorite);
        favoriteRepo.save(favorite);
        log.info("Favorite successfully saved");
        return SimpleResponse.builder()
                .httpStatus(HttpStatus.OK)
                .message("THIS PERSON LIKED THIS PRODUCT")
                .build();


    }

    @Override
    public FavoriteResponse getFavoriteById(Long id) {
        return favoriteRepo.findFavoriteById(id).orElseThrow(() ->
                new NotFoundException("Favorite with id: " + id + " not found"));

    }

//    @Override
//    public FavoriteResponse getFavoriteById(Long id) {
//        return favoriteRepo.getFavoriteById(id).orElseThrow(()->
//                new NotFoundException(String.format("Favorite with id: %s not found",id)));
//    }

}
