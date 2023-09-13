package boss.api;

import boss.dto.simpleResponse.SimpleResponse;
import boss.services.BasketService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/basket")
@RequiredArgsConstructor
@Tag(name = "BasketApi")
public class BasketApi {

    private final BasketService basketService;

    @PostMapping
    @PreAuthorize("hasAuthority('USER')")
    SimpleResponse saveBasket(@RequestParam List<Long> productIds) {
        return basketService.saveBasket(productIds);
    }
}
