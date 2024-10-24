package main.frame.game.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import main.frame.game.dto.response.JwtResponse;
import main.frame.game.dto.request.LoginRequest;
import main.frame.game.dto.request.RegisterRequest;
import main.frame.game.dto.UserDTO;
import main.frame.game.model.User;
import main.frame.game.service.AuthService;
import main.frame.game.service.UserService;
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
    private final UserService userService;

    private final JwtUtil jwtUtil;


    public AuthController(AuthService authService, UserService userService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest registerRequest) {
        try {
            authService.registerUser(new UserDTO(registerRequest.getEmail(), registerRequest.getPassword()));
            return ResponseEntity.ok("Поздравляем! Вы успешно зарегистрировались!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage() + "! Такой email уже используется!");
        }
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

    @GetMapping("/user")
    public ResponseEntity<UserDTO> getUserDetails(@AuthenticationPrincipal UserDetails userDetails) {
        System.out.println("UserDetails: " + userDetails); // Логируем полученные данные
        Optional<User> optionalUser = userService.findByEmail(userDetails.getUsername());
        if (optionalUser.isPresent()) {
            UserDTO userDTO = optionalUser.get().toUserDTO();
            return ResponseEntity.ok(userDTO);
        } else {
            System.out.println("User not found: " + userDetails.getUsername());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

//    @GetMapping("/user")
//    public ResponseEntity<UserDTO> getUserDetails(@RequestBody UserDetails userDetails) {
//        System.out.println("UserDetails: " + userDetails); // Логируем полученные данные
//        Optional<User> optionalUser = userService.findByEmail(userDetails.getUsername());
//        if (optionalUser.isPresent()) {
//            UserDTO userDTO = optionalUser.get().toUserDTO();
//            return ResponseEntity.ok(userDTO);
//        } else {
//            System.out.println("User not found: " + userDetails.getUsername());
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
//    }

    @GetMapping("/test")
    public ResponseEntity<?> someEndpoint(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        System.out.println("Authorization Header: " + authHeader);
        // Логика обработки
        return ResponseEntity.ok().build();
    }


//    @GetMapping("/user")
//    public ResponseEntity<UserDetails> getUser(@RequestParam String email) {
//      //  System.out.println("UserDetails: " + userDetails); // Логируем полученные данные
//        Optional<User> optionalUser = userService.findByEmail(email);
//        if (optionalUser.isPresent()) {
//            UserDTO userDTO = UserDTO.toUserDTO(optionalUser.get());
//            return ResponseEntity.ok(userDTO);
//        } else {
//       //     System.out.println("User not found: " + userDetails.getUsername());
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//        }
//    }
//@GetMapping("/user")
//public ResponseEntity<UserDTO> getUserDetails(@RequestHeader("Authorization") String token) {
//    if (token.startsWith("Bearer ")) {
//        token = token.substring(7);
//    } else {
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
//    }
//
//    String email = jwtUtil.extractEmail(token);
//    System.out.println("Email из токена: " + email);
//
//    Optional<User> optionalUser = userService.findByEmail(email);
//    if (optionalUser.isPresent()) {
//        UserDTO userDTO = UserDTO.toUserDTO(optionalUser.get());
//        return ResponseEntity.ok(userDTO);
//    } else {
//        System.out.println("User not found: " + email);
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//    }
//}

}