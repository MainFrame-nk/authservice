package main.frame.game.service;

import main.frame.game.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthServiceLoginTest {

    @Autowired
    private AuthService authService;

//    @MockBean
//    private UserRepository userRepository;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private BCryptPasswordEncoder passwordEncoder; // Добавляем мок для PasswordEncoder


//    @Test
//    void testLoginUser_Success() {
//        // Данные для теста
//        String email = "test@example.com";
//        String password = "correctpassword";
//        User user = new User();
//        user.setEmail(email);
//        user.setPassword(password);
//
//        // Создаем объект UserDTO
//        UserDTO userDTO = new UserDTO(email, password);
//
//        // Мокаем возвращаемые значения
//        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
//        when(passwordEncoder.matches(password, user.getPassword())).thenReturn(true);
//        when(jwtUtil.generateToken(user.getEmail())).thenReturn("generatedToken");
//
//        // Выполняем метод
//        String token = authService.loginUser(userDTO);
//
//        // Проверяем результаты
//        assertEquals("generatedToken", token);
//        verify(userRepository, times(1)).findByEmail(email);
//        verify(passwordEncoder, times(1)).matches(password, user.getPassword());
//        verify(jwtUtil, times(1)).generateToken(user.getEmail());
//    }
//
//    @Test
//    void testLoginUser_NonExistentEmail() {
//        // Данные для теста
//        String email = "nonexistent@example.com";
//        String password = "password";
//        UserDTO userDTO = new UserDTO(email, password);
//
//        // Мокаем, что пользователь не найден
//        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());
//
//        // Проверка, что выбрасывается исключение
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> authService.loginUser(userDTO));
//        assertEquals("Ошибка! Неверный email или пароль!", exception.getMessage());
//    }
//
//    @Test
//    void testLoginUser_InvalidPassword() {
//        // Данные для теста
//        String email = "test@example.com";
//        String password = "wrongpassword";
//        User user = new User();
//        user.setEmail(email);
//        user.setPassword("correctpassword");
//        UserDTO userDTO = new UserDTO(email, password);
//
//        // Мокаем возвращаемые значения
//        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
//        when(passwordEncoder.matches(password, user.getPassword())).thenReturn(false);
//
//        // Проверка, что выбрасывается исключение
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> authService.loginUser(userDTO));
//        assertEquals("Ошибка! Неверный email или пароль!", exception.getMessage());
//    }
//
//    @Test
//    void testCheckPassword_Success() {
//        // Данные для теста
//        String rawPassword = "correctpassword";
//        String encodedPassword = "encodedPassword";
//
//        // Мокаем поведение метода matches
//        when(passwordEncoder.matches(rawPassword, encodedPassword)).thenReturn(true);
//
//        // Проверяем, что метод возвращает true
//        assertTrue(authService.checkPassword(rawPassword, encodedPassword));
//        verify(passwordEncoder, times(1)).matches(rawPassword, encodedPassword);
//    }
//
//    @Test
//    void testCheckPassword_Failure() {
//        // Данные для теста
//        String rawPassword = "wrongpassword";
//        String encodedPassword = "encodedPassword";
//
//        // Мокаем поведение метода matches
//        when(passwordEncoder.matches(rawPassword, encodedPassword)).thenReturn(false);
//
//        // Проверяем, что метод возвращает false
//        assertFalse(authService.checkPassword(rawPassword, encodedPassword));
//        verify(passwordEncoder, times(1)).matches(rawPassword, encodedPassword);
//    }
}
