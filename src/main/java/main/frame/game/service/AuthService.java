package main.frame.game.service;

import main.frame.game.dto.request.RegisterRequest;
import main.frame.shared.dto.UserDTO;

public interface AuthService {
    String registerUser(RegisterRequest registerRequest);
    String loginUser(UserDTO userDTO);
}
