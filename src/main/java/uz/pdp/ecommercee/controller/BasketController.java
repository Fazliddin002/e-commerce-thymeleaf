package uz.pdp.ecommercee.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.ecommercee.dto.BasketProduct;
import uz.pdp.ecommercee.entity.Order;
import uz.pdp.ecommercee.entity.OrderProduct;
import uz.pdp.ecommercee.entity.Product;
import uz.pdp.ecommercee.repo.OrderProductRepo;
import uz.pdp.ecommercee.repo.OrderRepo;
import uz.pdp.ecommercee.repo.ProductRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/basket")
public class BasketController {
    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;
    private final OrderProductRepo orderProductRepo;

    @PostMapping
    public String addBasket(@RequestParam UUID productId, HttpSession session) {
        Product product = productRepo.findById(productId).orElseThrow(() -> new RuntimeException("Product not found"));
        Object object = session.getAttribute("basket");
        if (object == null) {
            BasketProduct basketProduct = new BasketProduct(
                    product,
                    1
            );
            List<BasketProduct> basketProducts = new ArrayList<>();
            basketProducts.add(basketProduct);
            session.setAttribute("basket", basketProducts);
        } else {
            List<BasketProduct> basketProducts = (List<BasketProduct>) object;
            BasketProduct basketProduct = new BasketProduct(
                    product,
                    1
            );
            basketProducts.add(basketProduct);
            session.setAttribute("basket", basketProducts);

        }
        return "redirect:/";
    }

    @SuppressWarnings("unchecked")
    @PostMapping("/delete")
    public String deleteBasket(@RequestParam UUID productId, HttpSession session) {
        deleteFromBasket(productId, session);
        return "redirect:/";
    }

    private static void deleteFromBasket(UUID productId, HttpSession session) {
        List<BasketProduct> basketProducts = (List<BasketProduct>) session.getAttribute("basket");
        List<BasketProduct> newBasketProducts = basketProducts.stream().filter(item -> !item.getProduct().getId().equals(productId)).toList();
        session.setAttribute("basket", new ArrayList<>(newBasketProducts));
    }

    @GetMapping
    public String showBasket(HttpSession session, Model model) {
        List<BasketProduct> basketProducts = Objects.requireNonNullElse((List<BasketProduct>) session.getAttribute("basket"), new ArrayList<>());
        if (basketProducts.isEmpty()) {
            return "redirect:/";
        }
        model.addAttribute("basketProducts", basketProducts);
        Integer total = basketProducts.stream().mapToInt(basketProduct -> basketProduct.getProduct().getPrice() * basketProduct.getAmount()).sum();
        model.addAttribute("totalAmount", total);
        return "basket";
    }

    @PostMapping("/amount")
    @SuppressWarnings("unchecked")
    public String updateBasketProductAmount(@RequestParam UUID productId, @RequestParam(required = false) UUID categoryId, String operation, HttpSession session) {
        List<BasketProduct> basketProducts = (List<BasketProduct>) session.getAttribute("basket");
        for (BasketProduct basketProduct : basketProducts) {
            if (basketProduct.getProduct().getId().equals(productId)) {
                if (operation.equals("++")) {
                    basketProduct.setAmount(basketProduct.getAmount() + 1);
                    session.setAttribute("basket", basketProducts);
                } else {
                    if (basketProduct.getAmount() == 1) {
                        deleteFromBasket(productId, session);
                    } else {
                        basketProduct.setAmount(basketProduct.getAmount() - 1);
                        session.setAttribute("basket", basketProducts);
                    }

                }
                return "redirect:/basket";
            }
        }
        String backPath = null;
        if (categoryId != null) {
            backPath = "/?categoryId=" + categoryId;
        } else {
            backPath = "/";
        }
        return "redirect:%s".formatted(backPath);
    }

    @Transactional
    @GetMapping("/checkout")
    public String checkoutBasket( HttpSession session) {
        List<BasketProduct> basketProducts = Objects.requireNonNullElse((List<BasketProduct>) session.getAttribute("basket"), new ArrayList<>());
        Order order = new Order();
        Order order1 = orderRepo.save(order);
        for (BasketProduct basketProduct : basketProducts) {
            OrderProduct orderProduct = new OrderProduct(
                    order1, basketProduct.getProduct(), basketProduct.getAmount()
            );
            orderProductRepo.save(orderProduct);
        }
        session.removeAttribute("basket");
        return "redirect:/";
    }
}
