package uz.pdp.ecommercee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login"; // login.html faylini qaytaradi
    }

    @GetMapping("logout")
    public String logout() {
        return "/login";
    }

}