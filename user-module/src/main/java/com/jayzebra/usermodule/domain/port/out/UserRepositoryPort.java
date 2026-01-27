package com.jayzebra.usermodule.domain.port.out;

import com.jayzebra.usermodule.adapter.out.entity.UserEntity;
import org.springframework.modulith.NamedInterface;

import java.util.List;
import java.util.Optional;

@NamedInterface
public interface UserRepositoryPort {
    List<UserEntity> findAll();
    Optional<UserEntity> findById(String userId);
    UserEntity save(UserEntity user);
}

