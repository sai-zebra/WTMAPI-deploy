package com.jayzebra.usermodule.domain.service;

import com.jayzebra.usermodule.adapter.out.entity.UserEntity;
import com.jayzebra.usermodule.domain.dto.UserDto;
import com.jayzebra.usermodule.domain.port.in.UserUseCase;
import com.jayzebra.usermodule.domain.port.out.UserRepositoryPort;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService implements UserUseCase {

    private final UserRepositoryPort userRepositoryPort;
    private final ModelMapper modelMapper;
    public UserService(UserRepositoryPort userRepositoryPort, ModelMapper modelMapper) {
        this.userRepositoryPort = userRepositoryPort;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<UserDto> listUsers() {
        return userRepositoryPort.findAll().stream()
                .map(userEntity -> modelMapper.map(userEntity, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> getUserById(String userId) {
        return userRepositoryPort.findById(userId)
                .map(userEntity -> modelMapper.map(userEntity, UserDto.class));
    }

    @Override
    public void uploadUserImage(String userId, MultipartFile imageFile) {
        // 1. Find the user entity
        UserEntity user = userRepositoryPort.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        // 2. In a real application, you would upload the file to a cloud storage
        //    service (like AWS S3, Google Cloud Storage) and get back a URL.
        //    For this example, we'll just generate a dummy path.
        String fileExtension = getFileExtension(imageFile.getOriginalFilename());
        String newFileName = UUID.randomUUID() + "." + fileExtension;
        String imageUrl = "/images/users/" + newFileName; // Example URL

        // 3. Update the user's profile image URL
        user.setProfileImageUrl(imageUrl);

        // 4. Save the updated user entity
        userRepositoryPort.save(user);
    }

    private String getFileExtension(String fileName) {
        if (fileName == null || fileName.lastIndexOf('.') == -1) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf('.') + 1);
    }
}
