package emlakburada.service;

import emlakburada.dto.AuthRequest;
import emlakburada.dto.AuthResponse;
import emlakburada.entity.User;
import emlakburada.entity.enums.UserType;
import emlakburada.repository.AuthRepository;
import emlakburada.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private AuthRepository authRepository;

    @Mock
    private JwtUtil jwtUtil;

    @BeforeEach
    void init() {

        Mockito
                .when(authRepository.findByEmail("test-email"))
                .thenReturn(prepareUser());
    }

    @Test
    void getToken() throws Exception {
        AuthResponse authResponse = authService.getToken(prepareRequest());

        assertEquals(jwtToken(), authResponse.getToken());
    }

    private AuthRequest prepareRequest() {
        return new AuthRequest("test-email", "test-password");
    }

    private User prepareUser() {
        User user = new User();
        user.setId(1);
        user.setUserType(UserType.INDIVIDUAL);
        user.setEmail("test-email");
        user.setPassword("test-password");
        return user;
    }

    private String jwtToken() {
        return jwtUtil.generateToken(prepareUser());
    }
}