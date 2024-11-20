package main.frame.game.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import main.frame.game.client.UserServiceClient;
import main.frame.game.dto.response.JwtResponse;
import main.frame.game.dto.request.LoginRequest;
import main.frame.game.dto.request.RegisterRequest;
import main.frame.shared.dto.UserDTO;
import main.frame.game.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import main.frame.game.utils.JwtUtil;


import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> loginUser(@RequestBody LoginRequest request) {
        log.info("Попытка входа в аккаунт, почта: {}", request.getEmail());

        try {
            String token = authService.loginUser(new UserDTO(request.getEmail(), request.getPassword()));
            log.info("Успешная авторизация. Токен сгенерирован.");
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (IllegalArgumentException e) {
            log.error("Ошибка авторизации: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new JwtResponse("Ошибка! Неверный email или пароль!"));
        }
    }
    //@PreAuthorize("hasRole('ADMIN')")

    // Регистрируем пользователя, делегируем запрос в UserService
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        try {
            // Вызываем метод регистрации из AuthService, который создаст пользователя и вернет токен
            String jwt = authService.registerUser(registerRequest);
            return ResponseEntity.ok("User registered successfully. Token: " + jwt);
        } catch (IllegalArgumentException e) {
            log.error("Ошибка регистрации: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            log.error("Неизвестная ошибка: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ошибка регистрации пользователя. Попробуйте позже.");
        }
    }

//    @GetMapping("/test")
//    public ResponseEntity<?> someEndpoint(HttpServletRequest request) {
//        String authHeader = request.getHeader("Authorization");
//        System.out.println("Authorization Header: " + authHeader);
//        // Логика обработки
//        return ResponseEntity.ok().build();
//    }

}