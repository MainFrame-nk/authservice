package main.frame.game.service;

import main.frame.game.client.UserServiceClient;
//import main.frame.game.model.Role;
//import main.frame.game.model.User;
import main.frame.shared.dto.RoleDTO;
import main.frame.shared.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final UserServiceClient userServiceClient;

    public UserDetailsService(UserServiceClient userServiceClient) {
        this.userServiceClient = userServiceClient;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDTO userDTO = userServiceClient.findByEmail(email).getBody();
        if (userDTO == null) {
            throw new UsernameNotFoundException("Пользователь не найден!");
        }

        return new org.springframework.security.core.userdetails.User(
                userDTO.getEmail(),
                userDTO.getPassword(),
                mapRoleAuthority(userDTO.getRoles())
        );
    }
    private Collection<? extends GrantedAuthority> mapRoleAuthority(Collection<RoleDTO> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}