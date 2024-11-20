package main.frame.game.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.frame.game.utils.JwtUtil;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import main.frame.game.service.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Enumeration;

public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    public JwtRequestFilter(JwtUtil jwtUtil, UserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void doFilterInternal(@Nullable HttpServletRequest request,
                                 @Nullable HttpServletResponse response,
                                 @Nullable FilterChain chain)
            throws ServletException, IOException {
        // Проверка на null перед выполнением
        if (request == null || response == null || chain == null) {
            //chain.doFilter(request, response); // Безопасно продолжить
            System.out.println("Запрос на фильтр пустой!");
            return; // Можно обработать как угодно, например, просто вернуть
        }

        // Разрешить маршруты /auth/login и /auth/register без проверки токена
        String path = request.getRequestURI();
        if (path.equals("/auth/login") || path.equals("/auth/register")) {
            chain.doFilter(request, response);
            return;
        }

        System.out.println("Фильтр вызван для URI: " + request.getRequestURI());
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String header = headerNames.nextElement();
                System.out.println("Header: " + header + " -> " + request.getHeader(header));
            }
        }

        //final String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String authorizationHeader = request.getHeader("Authorization");

        String email = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            email = jwtUtil.extractEmail(jwt);
            System.out.println("JWT: " + jwt);  // Добавь логирование
            System.out.println("Email из токена: " + email);
        } else {
            System.out.println("Authorization header не найден или неправильного формата");
        }

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(email);
            System.out.println("UserDetails загружен для: " + email);
            if (jwtUtil.validateToken(jwt, userDetails.getUsername())) {
                System.out.println("Токен валиден для пользователя: " + email);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            } else {
                System.out.println("Токен недействителен для пользователя: " + email);
            }
        }
        chain.doFilter(request, response);
    }
}