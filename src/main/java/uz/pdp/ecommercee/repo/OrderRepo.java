package uz.pdp.ecommercee.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.ecommercee.entity.Order;

public interface OrderRepo extends JpaRepository<Order, Integer> {
}
