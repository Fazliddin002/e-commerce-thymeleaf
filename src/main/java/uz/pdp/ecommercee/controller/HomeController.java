package uz.pdp.ecommercee.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.pdp.ecommercee.dto.BasketProduct;
import uz.pdp.ecommercee.entity.Category;
import uz.pdp.ecommercee.entity.Product;
import uz.pdp.ecommercee.entity.User;
import uz.pdp.ecommercee.repo.CategoryRepo;
import uz.pdp.ecommercee.repo.ProductRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RequiredArgsConstructor
@Controller
public class HomeController {
    private final CategoryRepo categoryRepo;
    private final ProductRepo productRepo;

    @GetMapping("/")
    public String home(Model model, @RequestParam(required = false) UUID categoryId, HttpSession session, @AuthenticationPrincipal User user) {

        List<Product> products = null;
        if (categoryId != null) {
            products = productRepo.findAllByCategoryId(categoryId);
            model.addAttribute("categoryId", categoryId);
        } else {
            products = productRepo.findAll();
        }
        for (Product product : products) {
            if (hasInBasket(product, session)) {
                product.setHasInBasket(true);
            }
        }
        List<Category> categories = categoryRepo.findAll();
        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        Integer basketSize = Objects.requireNonNullElse((List) session.getAttribute("basket"), new ArrayList<>()).size();
        model.addAttribute("basketSize", basketSize);
        model.addAttribute("user", user);
        return "home";
    }

    @SuppressWarnings("unchecked")
    private boolean hasInBasket(Product product, HttpSession session) {
        List<BasketProduct> basketProducts = Objects.requireNonNullElse((List<BasketProduct>) session.getAttribute("basket"), new ArrayList<>());
        for (BasketProduct basketProduct : basketProducts) {
            if (basketProduct.getProduct().getId().equals(product.getId())) {
                return true;
            }
        }
        return false;

    }

}
