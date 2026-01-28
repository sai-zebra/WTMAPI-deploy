package com.jayzebra.usermodule.domain.service;


import com.jayzebra.usermodule.adapter.out.entity.UserEntity;
import com.jayzebra.usermodule.domain.dto.UserAssetUploadResponseDto;
import com.jayzebra.usermodule.domain.port.out.UserRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockMultipartFile;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // 1. Initializes Mockito
class UserAssetServiceTest {

    @Mock
    private UserRepositoryPort userRepositoryPort; // 2. Mock the external dependency

    @InjectMocks
    private UserAssetService userAssetService; // 3. Create an instance of the service and inject the mock

    @Test
    void uploadUserImage_withValidUserAndFile_shouldUpdateUserAndReturnDto() {
        // Arrange
        String userId = "user-123";
        UserEntity mockUser = new UserEntity(); // Create a mock user to be "found"
        mockUser.setId(userId);

        MockMultipartFile mockFile = new MockMultipartFile(
                "image", "profile.jpg", "image/jpeg", "some-image-bytes".getBytes()
        );

        // Define the behavior of the mocked repository
        when(userRepositoryPort.findById(userId)).thenReturn(Optional.of(mockUser));

        // Act
        UserAssetUploadResponseDto responseDto = userAssetService.uploadUserImage(userId, mockFile);

        // Assert & Verify
        // 4. Use an ArgumentCaptor to capture the UserEntity passed to the save method
        ArgumentCaptor<UserEntity> userEntityCaptor = ArgumentCaptor.forClass(UserEntity.class);
        verify(userRepositoryPort).save(userEntityCaptor.capture());

        UserEntity capturedUser = userEntityCaptor.getValue();

        // 5. Assert that the captured entity was updated correctly
        assertNotNull(capturedUser.getProfileImageUrl());
        assertTrue(capturedUser.getProfileImageUrl().contains("/assets/user-images/"));

        // 6. Assert that the returned DTO contains the correct data
        assertEquals(userId, responseDto.getUserId());
        assertEquals(capturedUser.getProfileImageUrl(), responseDto.getNewProfileImageUrl());
    }

    @Test
    void uploadUserImage_whenUserNotFound_shouldThrowException() {
        // Arrange
        String userId = "non-existent-user";
        MockMultipartFile mockFile = new MockMultipartFile("file", "test.jpg", "image/jpeg", "bytes".getBytes());

        // Mock the repository to return an empty Optional, simulating a user not found
        when(userRepositoryPort.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        // 7. Assert that the correct exception is thrown
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userAssetService.uploadUserImage(userId, mockFile);
        });

        assertEquals("User not found with id: " + userId, exception.getMessage());

        // Verify that the save method was never called
        verify(userRepositoryPort, never()).save(any(UserEntity.class));
    }

    @Test
    void uploadUserImage_withEmptyFile_shouldThrowException() {
        // Arrange
        String userId = "user-123";
        UserEntity mockUser = new UserEntity();
        mockUser.setId(userId);

        // Create an empty file
        MockMultipartFile emptyFile = new MockMultipartFile("image", "empty.jpg", "image/jpeg", new byte[0]);

        // The user must be found for the file check to be reached
        when(userRepositoryPort.findById(userId)).thenReturn(Optional.of(mockUser));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userAssetService.uploadUserImage(userId, emptyFile);
        });

        assertEquals("Cannot upload an empty file.", exception.getMessage());

        // Verify that the save method was never called
        verify(userRepositoryPort, never()).save(any(UserEntity.class));
    }
}
