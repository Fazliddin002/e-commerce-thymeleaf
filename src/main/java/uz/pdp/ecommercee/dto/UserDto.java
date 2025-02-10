package uz.pdp.ecommercee.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserDto(
        String username,
        @NotBlank(message = "Parol bo‘sh bo‘lishi mumkin emas")
        @Size(min = 8, message = "Parol kamida 8 ta belgidan iborat bo‘lishi kerak")
        String password
) {}
