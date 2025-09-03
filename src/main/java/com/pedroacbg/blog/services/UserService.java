package com.pedroacbg.blog.services;

import com.pedroacbg.blog.domain.model.User;
import com.pedroacbg.blog.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    public User getUserById(Long id){
        logger.info("Buscando usuário por id...");
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Nenhum usuário encontrado com este id " + id));
    }
}

