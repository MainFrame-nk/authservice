package main.frame.game.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String email;
    private String username;
    private String password;
    private Set<RoleDTO> roles; // Здесь Set из RoleDTO вместо сущностей Role


//    private Long id;
//    private String email;
//
//    private String username;
//    private String phoneNumber;
//    private boolean active;
//    private Set<RoleDTO> roles = new HashSet<>();
//    private LocalDateTime dateOfCreated;

    public UserDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
//    private Long id;
//    private String email;
//    private String password;
//    private String username;
//    private String phoneNumber;
//    private boolean active;
//    private Set<RoleDTO> roles = new HashSet<>();
//    private LocalDateTime dateOfCreated;
//
//    public UserDTO(String email, String password) {
//        this.email = email;
//        this.password = password;
//    }

//    public static UserDTO toUserDTO(User user) {
//        return UserDTO.builder()
//                .id(user.getId())
//                .email(user.getEmail())
//                .username(user.getUsername())
//                .phoneNumber(user.getPhoneNumber())
//                .active(user.isActive())
//                .dateOfCreated(user.getDateOfCreated())
//                .roles(user.getRoles().stream().map(Role::toRoleDto).collect(Collectors.toSet()))
//                .build();
//    }
}
