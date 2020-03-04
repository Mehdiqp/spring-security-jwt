package com.company.security.jwt.service;

import com.company.security.jwt.exception.UserNameAlreadyExistException;
import com.company.security.jwt.model.RegisterUser;
import com.company.security.jwt.model.Role;
import com.company.security.jwt.model.User;
import com.company.security.jwt.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {

    //felan inject mikonim
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public void saveUser(RegisterUser user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UserNameAlreadyExistException(user.getUsername());
        }
        User storeUser = new User();
        storeUser.setUsername(user.getUsername());
        storeUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        storeUser.setRoles(Collections.singletonList(new Role("USER")));
        userRepository.save(storeUser);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public void deleteByUsername(String username) {
        userRepository.deleteByUsername(username);
    }

    public Page<User> findAll(int pageSize, int pageNumber) {
        return userRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }
}
