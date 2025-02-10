package uz.pdp.ecommercee.controller;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.ecommercee.entity.Attachment;
import uz.pdp.ecommercee.entity.Product;
import uz.pdp.ecommercee.repo.AttachmentRepo;
import uz.pdp.ecommercee.repo.ProductRepo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

@RequestMapping("/file")
@Controller
@RequiredArgsConstructor
public class AttachmentController {
    private final AttachmentRepo attachmentRepo;
    private final ProductRepo productRepo;


    @Transactional
    @GetMapping("/product/{id}")
    public void returnProductImage(@PathVariable UUID id, HttpServletResponse response) throws IOException {
        Product product = productRepo.findById(id).orElseThrow(() -> new FileNotFoundException(id.toString()));
        Attachment attachment = product.getAttachment();
        response.getOutputStream().write(attachment.getContent());
    }

}
