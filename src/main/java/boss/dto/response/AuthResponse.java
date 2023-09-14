package boss.dto.response;

import boss.enums.Role;
import lombok.Builder;

@Builder
public record AuthResponse(String token,
                           String email,
                           Role role) {
}
