package main.frame.game.dto;

import lombok.*;
import main.frame.game.model.Role;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Component
public class RoleDTO {
    private Integer id;
    private String name;

//    public static RoleDTO toRoleDto(Role role) {
//        return RoleDTO.builder()
//                .id(role.getId())
//                .name(role.getName())
//                .build();
//    }
}
