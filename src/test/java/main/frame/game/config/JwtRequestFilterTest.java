package main.frame.game.config;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest
@AutoConfigureMockMvc
public class JwtRequestFilterTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private HttpServletRequest request;
//
//    @Mock
//    private HttpServletResponse response;
//
//    @Mock
//    private FilterChain filterChain;
//
//    @Mock
//    private UserDetails userDetails;
//
//    @InjectMocks
//    private JwtRequestFilter jwtRequestFilter;
//    @Mock
//    private UserDetailsService userDetailsService;
//
//    @Mock
//    private JwtUtil jwtUtil;
//
//    @BeforeEach
//    public void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//
//    @Test
//    public void testDoFilterInternal_WithValidToken() throws Exception {
////        // Подготовка тестового JWT и данных пользователя
////        String jwtToken = "test-jwt-token";
////        String email = "test@example.com";
////        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
////                email, "password", new ArrayList<>());
////
////        // Настраиваем моки
////        when(jwtUtil.extractEmail(jwtToken)).thenReturn(email);
////        when(jwtUtil.validateToken(jwtToken, email)).thenReturn(true);
////        when(userDetailsService.loadUserByUsername(email)).thenReturn(userDetails);
////
////        // Выполняем запрос с заголовком Authorization
////        mockMvc.perform(MockMvcRequestBuilders.get("/protected-endpoint")
////                        .header("Authorization", "Bearer " + jwtToken)
////                        .contentType(MediaType.APPLICATION_JSON))
////                .andExpect(status().isOk());
//        // Мокаем Authorization заголовок
//        when(request.getHeader("Authorization")).thenReturn("Bearer some-jwt-token");
//        when(jwtUtil.extractEmail(anyString())).thenReturn("test@example.com");
//        when(userDetailsService.loadUserByUsername(anyString())).thenReturn(userDetails);
//        when(jwtUtil.validateToken(anyString(), anyString())).thenReturn(true);
//
//        // Убедитесь, что исключение не выбрасывается
//        assertDoesNotThrow(() -> jwtRequestFilter.doFilterInternal(request, response, filterChain));
//
//        // Проверяем, что метод doFilter вызывается
//        verify(filterChain, times(1)).doFilter(request, response);
//    }
//
////        MockHttpServletRequest request = new MockHttpServletRequest();
////        request.addHeader("Authorization", "Bearer validToken");
////
////        MockHttpServletResponse response = new MockHttpServletResponse();
////        FilterChain filterChain = mock(FilterChain.class);
////
////        when(jwtUtil.extractEmail("validToken")).thenReturn("test@example.com");
////        when(jwtUtil.validateToken(anyString(), anyString())).thenReturn(true);
////
////        UserDetails userDetails = mock(UserDetails.class);
////        when(userDetailsService.loadUserByUsername("test@example.com")).thenReturn(userDetails);
////
////        jwtRequestFilter.doFilterInternal(request, response, filterChain);
////
////        verify(filterChain, times(1)).doFilter(request, response);
////        verify(jwtUtil, times(1)).validateToken(anyString(), anyString());
}