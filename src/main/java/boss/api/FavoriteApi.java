package boss.api;

import boss.dto.request.FavoriteRequest;
import boss.dto.response.FavoriteResponse;
import boss.dto.simpleResponse.SimpleResponse;
import boss.services.FavoriteService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorite")
@RequiredArgsConstructor
@Tag(name = "FavoriteApi")
public class FavoriteApi {

    private final FavoriteService favoriteService;

    @GetMapping
    public List<FavoriteResponse> findAllFavorites(){
        return favoriteService.findAllFavorites();
    }

    @PostMapping("/{productId}")
    public SimpleResponse save(@PathVariable Long productId,
                               @RequestBody FavoriteRequest favoriteRequest){
        return favoriteService.save(productId,favoriteRequest);
    }

//    @GetMapping("/{id}")
//    public FavoriteResponse getFavoriteById(@PathVariable Long id){
//        return favoriteService.getFavoriteById(id);
//    }

}
