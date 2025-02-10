package uz.pdp.ecommercee.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.ecommercee.dto.ProductReq;
import uz.pdp.ecommercee.entity.Attachment;
import uz.pdp.ecommercee.entity.Category;
import uz.pdp.ecommercee.entity.Product;
import uz.pdp.ecommercee.repo.AttachmentRepo;
import uz.pdp.ecommercee.repo.CategoryRepo;
import uz.pdp.ecommercee.repo.ProductRepo;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RequestMapping("/product")
@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductRepo productRepo;
    private final CategoryRepo categoryRepo;
    private final AttachmentRepo attachmentRepo;

    @PostMapping
    public String addProduct(@ModelAttribute ProductReq productReq, @RequestParam MultipartFile file) throws IOException {
        Category category = categoryRepo.findById(productReq.categoryId()).orElseThrow(() -> new RuntimeException("Category not found"));
        Attachment attachment = attachmentRepo.save(new Attachment(file.getBytes()));
        Product product = Product.builder()
                .category(category)
                .name(productReq.name())
                .price(productReq.price())
                .attachment(attachment)
                .build();
        productRepo.save(product);
        return "redirect:/admin/product";
    }
    ////

    @GetMapping("/update")
    public String updateProduct(@RequestParam UUID id, Model model) {
        List<Category> categories = categoryRepo.findAll();
        Product product = productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        return "/admin/create-product";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable UUID id,
                                @ModelAttribute ProductReq productReq,
                                @RequestParam MultipartFile file) throws IOException {

        // 1️⃣ Eski mahsulotni bazadan topamiz
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // 2️⃣ Kategoriya mavjudligini tekshiramiz
        Category category = categoryRepo.findById(productReq.categoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // 3️⃣ Agar fayl bo‘lsa, Attachment ni yangilaymiz
        if (!file.isEmpty()) {
            Attachment attachment = new Attachment();
            attachment.setContent(file.getBytes());
            attachment = attachmentRepo.save(attachment); // BIRINCHI SAQLAYMIZ!

            product.setAttachment(attachment); // Keyin bog‘laymiz
        }

        // 4️⃣ Boshqa maydonlarni yangilaymiz
        product.setName(productReq.name());
        product.setPrice(productReq.price());
        product.setCategory(category);

        // 5️⃣ Mahsulotni bazaga saqlaymiz
        productRepo.save(product);

        return "redirect:/admin/product";
    }

    ///

    @GetMapping("/delete")
    public String deleteProduct(@RequestParam UUID id) {
        productRepo.deleteById(id);
        return "redirect:/admin/product";
    }

}
