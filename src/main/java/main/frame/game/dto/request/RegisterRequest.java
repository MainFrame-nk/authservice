package main.frame.game.dto.request;


import lombok.Data;
import main.frame.shared.dto.RoleDTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
public class RegisterRequest {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;

    private String username;
    private String phoneNumber;
    private LocalDateTime dateOfCreated;
    private Set<RoleDTO> roles;
}