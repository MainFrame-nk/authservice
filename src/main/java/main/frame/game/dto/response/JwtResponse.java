package main.frame.game.dto.response;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class JwtResponse {
    private String token;
    public JwtResponse(String token) {
        this.token = token;
    }
}