package main.frame.game.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import main.frame.game.controller.AuthController;
import main.frame.game.dto.request.LoginRequest;
//import main.frame.game.dto.UserDTO;
import main.frame.game.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(AuthController.class)
public class AuthControllerTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    public void testRegisterUser() throws Exception {
//        mockMvc.perform(post("/auth/register")
//                        .contentType("application/json")
//                        .content("{\"email\": \"test@example.com\", \"password\": \"password\"}"))
//                .andExpect(status().isOk());
//    }
}