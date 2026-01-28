package com.jayzebra.usermodule.adapter.in.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayzebra.usermodule.adapter.in.UserAssetController;
import com.jayzebra.usermodule.domain.dto.UserAssetUploadResponseDto;
import com.jayzebra.usermodule.domain.port.in.UserAssetUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.multipart.MultipartFile;

// --- CHANGE #1: Import the new annotation ---
import org.springframework.security.test.context.support.WithMockUser;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserAssetController.class)
class UserAssetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserAssetUseCase userAssetUseCase;

    @Test
    @WithMockUser // --- CHANGE #2: Run this test as an authenticated user ---
    void uploadUserImage_withValidFile_returns200Ok() throws Exception {
        // Arrange
        String userId = "user-123";
        String imageUrl = "https://example.com/images/user-123.png";

        MockMultipartFile mockFile = new MockMultipartFile("file", "profile.png", MediaType.IMAGE_PNG_VALUE, "fake-image-bytes".getBytes());
        UserAssetUploadResponseDto expectedResponse = new UserAssetUploadResponseDto(userId, imageUrl);

        when(userAssetUseCase.uploadUserImage(eq(userId), any(MultipartFile.class))).thenReturn(expectedResponse);

        // Act & Assert
        mockMvc.perform(multipart(HttpMethod.PUT, "/users/{userId}/image", userId)
                        .file(mockFile)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(userId))
                .andExpect(jsonPath("$.newProfileImageUrl").value(imageUrl));
    }

    @Test
    @WithMockUser // --- CHANGE #3: Also run this test as an authenticated user ---
    void uploadUserImage_withEmptyFile_returns400BadRequest() throws Exception {
        // Arrange
        String userId = "user-123";
        MockMultipartFile emptyFile = new MockMultipartFile("file", "empty.txt", MediaType.TEXT_PLAIN_VALUE, new byte[0]);

        // Act & Assert
        mockMvc.perform(multipart(HttpMethod.PUT, "/users/{userId}/image", userId)
                        .file(emptyFile)
                        .contentType(MediaType.MULTIPART_FORM_DATA)
                        .with(csrf()))
                .andExpect(status().isBadRequest());

        verify(userAssetUseCase, never()).uploadUserImage(anyString(), any(MultipartFile.class));
    }
}
