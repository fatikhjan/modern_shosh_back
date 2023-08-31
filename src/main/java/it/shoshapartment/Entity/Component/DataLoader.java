package it.shoshapartment.Entity.Component;

import it.shoshapartment.Entity.Entity.User;
import it.shoshapartment.Entity.Repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
@Configuration
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String init;

    private final PasswordEncoder passwordEncoder;
    private final AuthRepository authRepository;


    @Override
    public void run(String... args) throws Exception {
        if (init.equals("create-drop") || init.equals("create")) {
            authRepository.save(
                    User.builder()
                            .name("Shosh Apartment")
                            .email("shosh@gmail.com")
                            .password(passwordEncoder.encode("root123"))
                            .role("ADMIN")
                            .build()
            );
        }
    }
}
