package com.jayzebra.usermodule.adapter;

import com.jayzebra.usermodule.adapter.out.entity.UserEntity;
import com.jayzebra.usermodule.adapter.out.repository.UserRepository;
import com.jayzebra.usermodule.domain.port.out.UserRepositoryPort;


import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Repository
public class UserRepositoryAdapter implements UserRepositoryPort {

    private final UserRepository userRepository;

    public UserRepositoryAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserEntity> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> findById(String userId) {
        return userRepository.findById(userId);
    }

    @Override
    public UserEntity save(UserEntity user) {
        return userRepository.save(user);
    }
}
