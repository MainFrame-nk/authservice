package main.frame.game.service;

import main.frame.game.dto.UserDTO;
import main.frame.game.model.User;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<UserDTO> getById(Long id);
    void deleteUser(Long id);
    List<UserDTO> getAllUsers();
  //  UserDTO updateUser(UserDTO userDTO, Long id);
    Optional<User> findByEmail(String email);
 //   void userBan(Long id);
    //void changeUserRoles(Long id, Map<String, String> roles);
    Optional<User> getUserByPrincipal(Principal principal);
}
