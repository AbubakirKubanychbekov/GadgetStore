package boss.dto.request;

import boss.enums.Role;
import jakarta.validation.constraints.Email;

public record AuthRequest(
        @Email
        String email,
        String password) {
}
