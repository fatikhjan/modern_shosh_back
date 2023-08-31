package it.shoshapartment.Entity.Service;

import it.shoshapartment.Entity.Entity.User;
import it.shoshapartment.Entity.Exceptions.DuplicateDataException;
import it.shoshapartment.Entity.Jwt.JWTUtil;
import it.shoshapartment.Entity.Pyload.ApiResponse;
import it.shoshapartment.Entity.Pyload.AuthenticationRequest;
import it.shoshapartment.Entity.Pyload.AuthenticationResponse;
import it.shoshapartment.Entity.Pyload.RegisterDto;
import it.shoshapartment.Entity.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthenticatedReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JWTUtil jwtUtil;

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.username(),
                        authenticationRequest.password()
                )
        );
        User principal = (User) authentication.getPrincipal();
        authRepository.save(principal);
        String token = jwtUtil.issueToken(principal.getUsername(), principal.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        return new AuthenticationResponse(token, principal);
    }

    public ApiResponse register(RegisterDto registerDto) {
        if (authRepository.existsUserByEmail(registerDto.email())){
                throw new   DuplicateDataException("такой адрес электронной почты имеется в базе данных");
        }
        User build = User.builder()
                .name(registerDto.name())
                .email(registerDto.email())
                .password(passwordEncoder.encode(registerDto.password()))
                .role("ADMIN")
                .build();
        authRepository.save(build);
        return new ApiResponse("Админ сохранен", true, 200);
    }
}
