package com.eventoapp.eventoapp.services;

import com.eventoapp.eventoapp.models.User;
import com.eventoapp.eventoapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    UserRepository userRepository;

    @Override
    public User create(User user){
        User existUser = userRepository.findByUsername(user.getUsername());

        if(existUser != null) {
            throw new Error ("Usuário já existe");
        }

        user.setPassword(passwordEncoder().encode(user.getPassword()));
        User createUser = userRepository.save(user);

        return null;
    }

    public User auth(User user){
        User credencial = userRepository.findByUsername(user.getUsername());

        if(credencial == null) {
            throw new Error ("Usuário inválido");
        }

        user.setPassword(passwordEncoder().encode(user.getPassword()));
        User createUser = userRepository.save(user);

        return null;
    }
}
