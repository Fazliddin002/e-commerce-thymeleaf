package uz.pdp.ecommercee.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.pdp.ecommercee.dto.UserDto;
import uz.pdp.ecommercee.entity.User;
import uz.pdp.ecommercee.repo.UserRepo;

@RequiredArgsConstructor
@Controller
@RequestMapping("/register")
public class RegisterController {

    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepo;

    @GetMapping
    public String register() {
        return "register";
    }

    @PostMapping
    public String registerUser(@Valid @ModelAttribute UserDto userDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
             model.addAttribute("error", "Parol kamida 8 ta belgi yoki raqamdan iborat bo'lishi kerak ! ");
            return "register";
        }
        User user=User.builder()
                .username(userDto.username())
                .password(passwordEncoder.encode(userDto.password()))
                .role("USER")
                .build();
        userRepo.save(user);
        return "redirect:/login";
    }


}