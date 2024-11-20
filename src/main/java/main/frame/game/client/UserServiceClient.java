package main.frame.game.client;

import main.frame.game.dto.request.RegisterRequest;
import main.frame.shared.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class UserServiceClient {

    private final WebClient webClient;

    public UserServiceClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://user-service").build(); // URL вашего UserService
    }

    public ResponseEntity<String> createUser(RegisterRequest registerRequest) {
        return webClient.post()
                .uri("/users/register")
                .bodyValue(registerRequest)
                .retrieve()
                .toEntity(String.class)
                .block();  // Блокируем, чтобы получить ответ от UserService
    }

    // Метод для поиска пользователя по email
    public ResponseEntity<UserDTO> findByEmail(String email) {
        return webClient.get()
                .uri("/users/email/{email}", email)
                .retrieve()
                .toEntity(UserDTO.class)
                .block();  // Ожидаем ответ от UserService
    }
}
