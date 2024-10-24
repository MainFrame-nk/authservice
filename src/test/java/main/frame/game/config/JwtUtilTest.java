package main.frame.game.config;

import org.springframework.boot.test.context.SpringBootTest;

/* testGenerateToken: Проверяет, что токен был успешно сгенерирован и не является пустым.
        testExtractEmail: Генерирует токен и проверяет, что извлечённый email совпадает с исходным.
        testExtractExpiration: Генерирует токен и проверяет, что дата истечения действия токена находится в будущем.
        testValidateToken_Success: Проверяет, что сгенерированный токен корректно валидируется с правильным email.
        testValidateToken_InvalidEmail: Проверяет, что токен не валидируется с неправильным email.
        testValidateToken_ExpiredToken: Имитация истечения срока действия токена и проверка, что он становится недействительным.
*/
@SpringBootTest
public class JwtUtilTest {

//    @Autowired
//    private JwtUtil jwtUtil;
//
//    @Mock
//    private Clock clock;
//
//    @Test
//    void testGenerateToken() {
//        String email = "test@example.com";
//        String token = jwtUtil.generateToken(email);
//
//        assertNotNull(token);
//        assertFalse(token.isEmpty());
//    }
//
//    @Test
//    void testExtractEmail() {
//        String email = "test@example.com";
//        String token = jwtUtil.generateToken(email);
//
//        String extractedEmail = jwtUtil.extractEmail(token);
//
//        assertEquals(email, extractedEmail);
//    }
//
//    @Test
//    void testExtractExpiration() {
//        String email = "test@example.com";
//        String token = jwtUtil.generateToken(email);
//
//        Date expirationDate = jwtUtil.extractExpiration(token);
//        assertNotNull(expirationDate);
//        assertTrue(expirationDate.after(new Date())); // Проверка, что токен ещё не истёк
//    }
//
//    @Test
//    void testValidateToken_Success() {
//        String email = "test@example.com";
//        String token = jwtUtil.generateToken(email);
//
//        boolean isValid = jwtUtil.validateToken(token, email);
//
//        assertTrue(isValid);
//    }
//
//    @Test
//    void testValidateToken_InvalidEmail() {
//        String email = "test@example.com";
//        String token = jwtUtil.generateToken(email);
//        String invalidEmail = "invalid@example.com";
//
//        boolean isValid = jwtUtil.validateToken(token, invalidEmail);
//
//        assertFalse(isValid);
//    }
}