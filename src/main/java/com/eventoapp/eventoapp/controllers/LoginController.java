package com.eventoapp.eventoapp.controllers;

import com.eventoapp.eventoapp.models.Usuario;
import com.eventoapp.eventoapp.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(Usuario usuario) {
        return "/login/index";
    }

    @PostMapping("/logar")
    public String postLogin() {
        return "redirect:/";
    }
}

