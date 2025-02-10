package uz.pdp.ecommercee.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.ecommercee.entity.User;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
