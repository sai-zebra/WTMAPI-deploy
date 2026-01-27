package com.jayzebra.usermodule.domain.service;

import com.jayzebra.usermodule.adapter.out.entity.UserEntity;
import com.jayzebra.usermodule.domain.dto.UserAssetUploadResponseDto;
import com.jayzebra.usermodule.domain.port.in.UserAssetUseCase;
import com.jayzebra.usermodule.domain.port.out.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserAssetService implements UserAssetUseCase {

    // Dependency on another module's port for cross-module communication
    private final UserRepositoryPort userRepositoryPort;

    @Override
    public UserAssetUploadResponseDto uploadUserImage(String userId, MultipartFile imageFile) {
        // 1. Find the user by calling the other module's repository port
        UserEntity user = userRepositoryPort.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        // 2. Logic to store the file (e.g., to cloud storage) and get a URL
        String imageUrl = storeFileAndGetUrl(imageFile);

        // 3. Update the user entity object
        user.setProfileImageUrl(imageUrl);

        // 4. Save the updated user entity using the other module's repository port
        userRepositoryPort.save(user);

        // 5. Return a DTO with the new URL
        return new UserAssetUploadResponseDto(userId, imageUrl);
    }

    private String storeFileAndGetUrl(MultipartFile file) {
        // TODO: Implement actual file storage logic (e.g., to AWS S3, local disk).
        // This is placeholder logic.
        if (file.isEmpty()) {
            throw new RuntimeException("Cannot upload an empty file.");
        }
        String fileExtension = getFileExtension(file.getOriginalFilename());
        String newFileName = UUID.randomUUID().toString() + "." + fileExtension;
        return "/assets/user-images/" + newFileName;
    }

    private String getFileExtension(String fileName) {
        if (fileName == null || fileName.lastIndexOf('.') == -1) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf('.') + 1);
    }
}
