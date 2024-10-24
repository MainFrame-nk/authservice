package main.frame.game.config;

//import main.frame.game.dto.UserDTO;
import main.frame.game.model.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private UserRepository userRepository;  // Мокаем UserRepository
//
//    @MockBean
//    private BCryptPasswordEncoder passwordEncoder;  // Мокаем PasswordEncoder
//
//
//    @Test
//    void testPublicEndpoint_AuthRegister() throws Exception {
//        // Данные для теста
//        String registerJson = """
//        {
//            "email": "newuser@example.com",
//            "password": "password123"
//        }
//    """;
//
//        mockMvc.perform(post("/auth/register")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(registerJson))  // Отправляем данные в формате JSON
//                .andExpect(status().isOk());  // Ожидаем статус 200 OK
//    }
//
//    @Test
//    void testPublicEndpoint_AuthLogin() throws Exception {
//        // Создаем мок пользователя с закодированным паролем
//        String email = "test@example.com";
//        String rawPassword = "password";
//        String encodedPassword = passwordEncoder.encode(rawPassword);
//
//        User user = new User();
//        user.setEmail(email);
//        user.setPassword(encodedPassword);
//
//        // Мокаем вызовы репозитория и энкодера
//        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
//        when(passwordEncoder.matches(rawPassword, encodedPassword)).thenReturn(true);
//
//        // Тело запроса для логина
//        String loginRequest = "{ \"email\": \"test@example.com\", \"password\": \"password\" }";
//
//        // Делаем POST запрос на логин
//        mockMvc.perform(post("/auth/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(loginRequest))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.token").isNotEmpty());  // Ожидаем токен в ответе
//    }
//
////    @Test
////    @WithMockUser(roles = "ADMIN") // Создаем пользователя с ролью ADMIN
////    void testAdminEndpoint_AccessGranted() throws Exception {
////        // Проверяем, что эндпоинт для администраторов доступен
////        mockMvc.perform(post("/admin/some-endpoint") // Замените на ваш админский эндпоинт
////                        .contentType(MediaType.APPLICATION_JSON)
////                        .content("{\"key\":\"value\"}"))
////                .andExpect(status().isOk()); // Ожидаем 200 OK
////    }
////
////    @Test
////    void testAdminEndpoint_AccessDenied() throws Exception {
////        // Проверяем, что доступ к админскому эндпоинту без роли ADMIN запрещен
////        mockMvc.perform(post("/admin/some-endpoint") // Замените на ваш админский эндпоинт
////                        .contentType(MediaType.APPLICATION_JSON)
////                        .content("{\"key\":\"value\"}"))
////                .andExpect(status().isForbidden()); // Ожидаем 403 Forbidden
////    }
}