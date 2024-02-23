package boss.services;

import boss.dto.request.AuthRequest;
import boss.dto.request.UserRequest;
import boss.dto.response.AuthResponse;
import boss.dto.response.UserResponse;
import boss.dto.simpleResponse.SimpleResponse;

public interface UserService {

    void init();

    SimpleResponse signUp(UserRequest userRequest);

    AuthResponse signIn(AuthRequest request);


}
