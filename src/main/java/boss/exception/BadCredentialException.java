package boss.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BadCredentialException extends RuntimeException{
    public BadCredentialException(String message) {
        super(message);
    }
}
