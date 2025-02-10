package uz.pdp.ecommercee.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.ecommercee.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductRepo extends JpaRepository<Product, UUID> {
    List<Product> findAllByCategoryId(UUID categoryId);
}
