package uz.pdp.ecommercee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.ecommercee.entity.Order;
import uz.pdp.ecommercee.entity.OrderProduct;
import uz.pdp.ecommercee.repo.OrderProductRepo;
import uz.pdp.ecommercee.repo.OrderRepo;
import java.util.List;

@Controller
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepo orderRepo;
    private final OrderProductRepo orderProductRepo;

    @GetMapping
    public String orders(Model model) {
        List<Order> orders = orderRepo.findAll();
        model.addAttribute("orders", orders);
        return "orders";
    }

    @GetMapping("/info/{id}")
    public String orderInfo(@PathVariable Integer id, Model model) {
        List<OrderProduct> orderProducts = orderProductRepo.findAllByOrderId(id);
        model.addAttribute("orderProducts", orderProducts);
        return "info";

    }
}
