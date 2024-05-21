package com.uplms.project.modules.user.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.uplms.project.exceptions.UserFoundException;
import com.uplms.project.modules.user.entity.UserEntity;
import com.uplms.project.modules.user.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserEntity createUser(UserEntity userEntity) throws Exception {
        this.userRepository.findByLoginOrEmail(userEntity.getLogin(), userEntity.getEmail()).ifPresent((user) -> {
            throw new UserFoundException();
        });

        userEntity.setRole(UserEntity.Role.USER);
        userEntity.setActive(UserEntity.Active.YES);

        String password = passwordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(password);
        return this.userRepository.save(userEntity);
    }
}
