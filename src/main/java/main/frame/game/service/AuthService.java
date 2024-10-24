package main.frame.game.service;

import main.frame.game.dto.UserDTO;

public interface AuthService {
    void registerUser(UserDTO userDTO);
    String loginUser(UserDTO userDTO);
}
