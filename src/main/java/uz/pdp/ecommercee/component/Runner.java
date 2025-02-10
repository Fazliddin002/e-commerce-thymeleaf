package uz.pdp.ecommercee.component;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.ecommercee.entity.User;
import uz.pdp.ecommercee.repo.UserRepo;

@RequiredArgsConstructor
@Component("componentRunner")


public class Runner implements CommandLineRunner {
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;
  @Value("${spring.jpa.hibernate.ddl-auto}")
    private String ddl;

    @Override
    public void run(String... args) throws Exception{

        User user = new User(11,"userAdmin",passwordEncoder.encode("root123"),"ADMIN");
        if (ddl.equals("create")){
            userRepo.save(user);
            for (int i = 1; i <=10 ; i++) {
                userRepo.save(new User(
                        i,
                        "user"+i,
                        passwordEncoder.encode("root"+i),
                        "ADMIN"
                ));
            }
        }
    }
}
