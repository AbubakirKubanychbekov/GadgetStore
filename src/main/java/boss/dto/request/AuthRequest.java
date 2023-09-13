package boss.dto.request;

import boss.validation.EmailValidation;

public record AuthRequest(
        @EmailValidation
        String email,
        String password) {
}
