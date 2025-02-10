package uz.pdp.ecommercee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.pdp.ecommercee.dto.CategoryReq;
import uz.pdp.ecommercee.entity.Category;
import uz.pdp.ecommercee.repo.CategoryRepo;

import java.util.Optional;
import java.util.UUID;

@RequestMapping("/category")
@Controller
@RequiredArgsConstructor
public class CategoryController {
   private final CategoryRepo categoryRepo;

    @PostMapping("create")
    public String addCategory(@RequestParam String name) {
        Category category = Category.builder().name(name).build();
        categoryRepo.save(category);
        return "redirect:/admin/category";
    }
   @PostMapping("/delete")
   public String deleteCategory(@RequestParam UUID id) {
        categoryRepo.deleteById(id);
        return "redirect:/admin/category";
   }

    @PostMapping("/update{id}")
    public String update(@ModelAttribute CategoryReq categoryReq, @PathVariable UUID id) {
        Category category = Category.builder()
                .id(id)
                .name(categoryReq.name())
                .build();
        categoryRepo.save(category);
        return "redirect:/admin/category";
    }
    @GetMapping("/update")
    public String update(@RequestParam UUID id, Model model) {
        Category category = categoryRepo.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        model.addAttribute("category",category);
        return "/admin/create-category";
    }

}
