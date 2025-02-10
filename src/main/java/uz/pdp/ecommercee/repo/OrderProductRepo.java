package uz.pdp.ecommercee.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.ecommercee.entity.Order;
import uz.pdp.ecommercee.entity.OrderProduct;

import java.util.List;
import java.util.UUID;

public interface OrderProductRepo extends JpaRepository<OrderProduct, UUID> {
    List<OrderProduct> findAllByOrderId(Integer orderId);

}
