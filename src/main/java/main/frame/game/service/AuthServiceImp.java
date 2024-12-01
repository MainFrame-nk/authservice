package main.frame.game.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import main.frame.game.client.UserServiceClient;
import main.frame.game.dto.request.RegisterRequest;
import main.frame.game.utils.JwtUtil;
import main.frame.shared.dto.UserDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {
//    @PersistenceContext
//    private EntityManager entityManager;

    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final UserServiceClient userServiceClient;

    @Override
    public String registerUser(RegisterRequest registerRequest) {
        // Шаг 1: Направляем запрос в UserService через UserServiceClient для создания пользователя
        ResponseEntity<String> response = userServiceClient.createUser(registerRequest);

        if (response.getStatusCode() == HttpStatus.CREATED) {
          //  List<String> roles = List.of("ROLE_USER");
          //  String token = jwtUtil.generateToken("user@example.com", roles);
            // Шаг 2: Генерация JWT токена при успешной регистрации
            String jwt = jwtUtil.generateToken(registerRequest.getEmail(), registerRequest.getRoles());
            log.info("Новый пользователь зарегистрирован. Генерируется токен для email: {}", registerRequest.getEmail());
            return jwt;
        } else {
            log.error("Ошибка создания пользователя: {}", response.getBody());
            throw new IllegalArgumentException("Ошибка регистрации пользователя: " + response.getBody());
        }
    }


//    @Transactional
//    @Override
//    public void registerUser(UserDTO userDTO) {
//        String email = userDTO.getEmail();
//        log.debug("Поиск email пользователя: {}", email);
//
//        // Проверяем существование пользователя
//        if (userService.findByEmail(email).isPresent()) {
//            log.warn("Попытка регистрации пользователя с существующей почтой {}!", email);
//            throw new IllegalArgumentException("Пользователь с таким email уже зарегистрирован!");
//        }
//
//        log.debug("Оригинальный пароль: {}", userDTO.getPassword());
//        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
//
//        // Поиск роли с ID 1
//        Role userRole = entityManager.find(Role.class, 1);
//        if (userRole == null) {
//            throw new IllegalArgumentException("Роль с ID 1 (User) не найдена");
//        }
//        log.debug("Добавлена роль: {}", userRole.getName());
//
//        Set<Role> roles = new HashSet<>();
//        roles.add(userRole);
//
//        User newUser = User.builder()
//                .email(email)
//                .password(encodedPassword)
//                .username(userDTO.getUsername())
////                .dateOfCreated(userDTO.getDateOfCreated())
////                .phoneNumber(userDTO.getPhoneNumber())
//                .active(true)
//                .roles(roles)
//                //.roles(userDTO.getRoles().stream().map(x -> roleRepository.findByRole(x.getRole())).collect(Collectors.toSet()))
//                .build();
//
//        entityManager.persist(newUser);  // Сохраняем пользователя
//        log.info("Зарегистрирован новый пользователь с почтой: {}", email);
//        //newUser.setPassword(null);  // Убираем пароль для безопасности
//    }

    @Override
    public String loginUser(UserDTO userDTO) {
        // Запрашиваем пользователя по email через UserServiceClient
        ResponseEntity<UserDTO> userResponse = userServiceClient.findByEmail(userDTO.getEmail());

        if (userResponse.getStatusCode() != HttpStatus.OK || userResponse.getBody() == null) {
            throw new IllegalArgumentException("Ошибка! Неверный email или пароль!");
        }

        UserDTO user = userResponse.getBody();
        // Проверка пароля
        if (!passwordEncoder.matches(userDTO.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Ошибка! Неверный email или пароль!");
        }

        // Генерация JWT токена после успешной авторизации
        String token = jwtUtil.generateToken(user.getEmail(), user.getRoles());
        log.info("Generated token: {}", token);

        return token;
    }
}
