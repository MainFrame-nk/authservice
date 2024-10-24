package main.frame.game.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import main.frame.game.utils.JwtUtil;
import main.frame.game.dto.UserDTO;
import main.frame.game.model.Role;
import main.frame.game.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {
    @PersistenceContext
    private EntityManager entityManager;

    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserService userService;

    @Transactional
    @Override
    public void registerUser(UserDTO userDTO) {
        String email = userDTO.getEmail();
        log.debug("Поиск email пользователя: {}", email);

        // Проверяем существование пользователя
        if (userService.findByEmail(email).isPresent()) {
            log.warn("Попытка регистрации пользователя с существующей почтой {}!", email);
            throw new IllegalArgumentException("Пользователь с таким email уже зарегистрирован!");
        }

        log.debug("Оригинальный пароль: {}", userDTO.getPassword());
        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());

        // Поиск роли с ID 1
        Role userRole = entityManager.find(Role.class, 1);
        if (userRole == null) {
            throw new IllegalArgumentException("Роль с ID 1 (User) не найдена");
        }
        log.debug("Добавлена роль: {}", userRole.getName());

        Set<Role> roles = new HashSet<>();
        roles.add(userRole);

        User newUser = User.builder()
                .email(email)
                .password(encodedPassword)
                .username(userDTO.getUsername())
//                .dateOfCreated(userDTO.getDateOfCreated())
//                .phoneNumber(userDTO.getPhoneNumber())
                .active(true)
                .roles(roles)
                //.roles(userDTO.getRoles().stream().map(x -> roleRepository.findByRole(x.getRole())).collect(Collectors.toSet()))
                .build();

        entityManager.persist(newUser);  // Сохраняем пользователя
        log.info("Зарегистрирован новый пользователь с почтой: {}", email);
        //newUser.setPassword(null);  // Убираем пароль для безопасности
    }

    @Override
    public String loginUser(UserDTO userDTO) {
        Optional<User> userOptional = userService.findByEmail(userDTO.getEmail()); // Используем UserService

        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("Ошибка! Неверный email или пароль!");
        }

        User user = userOptional.get();
        if (!passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Ошибка! Неверный email или пароль!");
        }
        String token = jwtUtil.generateToken(user.getEmail());
        log.info("Generated token: {}", token);  // Добавь логирование токена для проверки

        return token;
    }
}
