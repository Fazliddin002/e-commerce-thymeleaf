E-Commerce Platform (Thymeleaf)

Bu loyiha Spring Boot va Thymeleaf asosida yaratilgan E-Commerce (Internet-do'kon) tizimidir. Loyiha mahsulotlar, foydalanuvchilar va buyurtmalar bilan ishlash imkoniyatini beradi.

Texnologiyalar

Loyiha quyidagi texnologiyalar asosida qurilgan:

Backend:

Java 17

Spring Boot

Spring Security

Spring Data JPA

PostgreSQL

Hibernate

Lombok

Frontend:

Thymeleaf

Bootstrap

HTML, CSS, JavaScript

O'rnatish va Ishga Tushirish

1. Loyiha kodini yuklash

git clone https://github.com/Fazliddin002/e-commerce-thymeleaf.git
cd e-commerce-thymeleaf

2. PostgreSQL bazasini sozlash

Loyihaning application.properties yoki application.yml faylida PostgreSQL konfiguratsiyasini o'zgartiring:

spring.datasource.url=jdbc:postgresql://localhost:5432/ecommerce_db
spring.datasource.username=your_username
spring.datasource.password=your_password

3. Loyihani ishga tushirish

mvn spring-boot:run

Agar mvn komandasi ishlamasa, Maven o‘rnatilganligini tekshiring.

Xususiyatlar

Foydalanuvchilar:

Ro‘yxatdan o‘tish va tizimga kirish

Admin va oddiy foydalanuvchi rollari

Mahsulotlar:

Mahsulot qo‘shish, tahrirlash va o‘chirish

Katalogni ko‘rish

Buyurtmalar:

Savatchaga mahsulot qo‘shish

Buyurtmani tasdiqlash

Foydalanish

Loyihani ishga tushirgandan so‘ng, http://localhost:8080/ sahifasiga tashrif buyuring.

Admin uchun:

Login: admin@example.com

Parol: admin123

Muammo va Takliflar

Agar sizda muammo yoki takliflar bo‘lsa, Issues bo‘limiga yozing.

Muallif

Fazliddin

Litsenziya

Bu loyiha ochiq kodli va bepul foydalanish uchun mo‘ljallangan.
