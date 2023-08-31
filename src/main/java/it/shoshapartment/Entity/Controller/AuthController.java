package it.shoshapartment.Entity.Controller;

import it.shoshapartment.Entity.Entity.User;
import it.shoshapartment.Entity.Pyload.ApiResponse;
import it.shoshapartment.Entity.Pyload.AuthenticationRequest;
import it.shoshapartment.Entity.Pyload.AuthenticationResponse;
import it.shoshapartment.Entity.Pyload.RegisterDto;
import it.shoshapartment.Entity.Repository.AuthRepository;
import it.shoshapartment.Entity.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/api/auth")
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    private final AuthRepository authRepository;

    @GetMapping
    public HttpEntity<?> getAll(){
        List<User> all = authRepository.findAll();
        return ResponseEntity.ok(all);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getAdmin(@PathVariable UUID id){
         User user = authRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found user"));
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest request) {
        AuthenticationResponse login = authService.login(request);
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, login.token())
                .body(login);
    }

    @PostMapping("/add-admin")
    public ResponseEntity<?> addAdmin(@RequestBody RegisterDto registerDto){
        ApiResponse register = authService.register(registerDto);
        return ResponseEntity.status(register.success() ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(register);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteAdmin(@PathVariable UUID id){
        User admin = authRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("not found admin"));
        authRepository.delete(admin);
        return ResponseEntity.ok(new ApiResponse("admin olib tashlandi", true, 200));
    }

}
