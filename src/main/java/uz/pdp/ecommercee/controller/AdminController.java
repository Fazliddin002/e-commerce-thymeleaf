package uz.pdp.ecommercee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.ecommercee.repo.CategoryRepo;
import uz.pdp.ecommercee.repo.ProductRepo;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final CategoryRepo categoryRepo;
    private final ProductRepo productRepo;


    @GetMapping("/category")
    public String category(Model model) {
        model.addAttribute("categories", categoryRepo.findAll());
        return "admin/category";
    }

    @GetMapping("/category/create")
    public String categoryCreate(Model model) {
//        model.addAttribute("categories", categoryRepo.findAll()); Jarayonda kerak bo'lmasa o'chiraman
        return "admin/create-category";
    }

    @GetMapping("/product")
    public String product(Model model) {
        model.addAttribute("products", productRepo.findAll());
        return "admin/product";
    }
//
//    @GetMapping("/product/create")
//    public String productCreate(Model model) {
//        model.addAttribute("categories", categoryRepo.findAll());
//        return "admin/create-product";
//    }  // jarayonda kerak  bo'masa ochiraman
    
}
