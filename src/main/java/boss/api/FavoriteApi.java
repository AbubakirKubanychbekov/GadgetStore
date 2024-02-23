package boss.api;

import boss.dto.request.FavoriteRequest;
import boss.dto.response.FavoriteResponse;
import boss.dto.simpleResponse.SimpleResponse;
import boss.services.FavoriteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/favorite")
@RequiredArgsConstructor
@Tag(name = "FavoriteApi")
public class FavoriteApi {

    private final FavoriteService favoriteService;

    @PermitAll
    @Operation(summary = "Get All",description = "Get All favorite info")
    @GetMapping("/{id}")
    public List<FavoriteResponse> findAllFavorites(@PathVariable Long id){
        return favoriteService.findAllFavorites(id);
    }

    @Secured({"ADMIN","USER"})
    @PostMapping("/{productId}")
    @Operation(summary = "Save",description = "To save  fill all the fields!")
    public SimpleResponse save(@PathVariable Long productId){
        return favoriteService.save(productId);
    }


}
