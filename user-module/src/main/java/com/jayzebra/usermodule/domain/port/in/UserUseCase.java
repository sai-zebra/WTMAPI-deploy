package com.jayzebra.usermodule.domain.port.in;

import com.jayzebra.usermodule.domain.dto.UserDto;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.Optional;

public interface UserUseCase {
    List<UserDto> listUsers();
    Optional<UserDto> getUserById(String userId);
    void uploadUserImage(String userId, MultipartFile imageFile);
}
