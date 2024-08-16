package com.eventoapp.eventoapp.controllers;

import com.eventoapp.eventoapp.models.User;
import com.eventoapp.eventoapp.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/logar")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/cadastrar")
    public String create(@RequestBody User user){
        userService.create(user);
        return "redirect:/logar";
    }

    @PostMapping("/entrar")
    public String entrar(@RequestBody User user){
        userService.create(user);
        return "redirect:/eventos";
    }

    @GetMapping("")
    public String cadastro(){
        return "logar/index";
    }

}
