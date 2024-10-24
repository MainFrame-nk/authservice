package main.frame.game.service;

//import main.frame.game.dto.UserDTO;
import main.frame.game.model.Role;
import main.frame.game.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY) // Использует in-memory базу данных
public class AuthServiceRegisterTest {

    @Autowired
    private AuthServiceImp authService;

//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private RoleRepository roleRepository;

//    @BeforeEach
//    void setUp() {
//        // Настройка тестовых данных, если необходимо
//        Role userRole = new Role(1, "ROLE_USER");
//        roleRepository.save(userRole);
//    }

//    @Test
//    @Transactional
//    void testRegisterUser_Success() {
//        UserDTO userDTO = new UserDTO("test@example.com", "password");
//
//        // Проверяем, что пользователя еще нет в базе
//        assertThat(userRepository.findByEmail(userDTO.getEmail())).isEmpty();
//
//        // Регистрируем пользователя
//        authService.registerUser(userDTO);
//
//        // Проверяем, что пользователь был успешно зарегистрирован
//        assertThat(userRepository.findByEmail(userDTO.getEmail())).isPresent();
//        User registeredUser = userRepository.findByEmail(userDTO.getEmail()).get();
//        assertThat(registeredUser.getEmail()).isEqualTo(userDTO.getEmail());
//        assertThat(registeredUser.getRoles()).isNotEmpty(); // Убедитесь, что роли назначены
//    }
//
//
//
////    @Test
////    void testRegisterUser_ExistingEmail() {
////        UserDTO userDTO = new UserDTO("test@example.com", "password");
////        Role userRole = new Role(1, "ROLE_USER"); // Создаём роль для теста
////
////        when(roleRepository.findRoleById(1)).thenReturn(Optional.of(userRole)); // Мокируем роль
////        when(userRepository.findByEmail(userDTO.getEmail())).thenReturn(Optional.of(new User())); // Мокируем существующего пользователя
////
////        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
////            authService.registerUser(userDTO);
////        });
////
////        assertEquals("Пользователь с таким email уже зарегистрирован!", exception.getMessage());
////    }
}
