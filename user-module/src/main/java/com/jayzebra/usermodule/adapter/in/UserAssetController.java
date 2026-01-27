package com.jayzebra.usermodule.adapter.in;

import com.jayzebra.usermodule.domain.dto.UserAssetUploadResponseDto;
import com.jayzebra.usermodule.domain.port.in.UserAssetUseCase;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users") // Maps to the same base path as the Users module
@Tag(name = "UserAssets", description = "Endpoints for managing user assets like profile images.")
@RequiredArgsConstructor
public class UserAssetController {

    private final UserAssetUseCase userAssetUseCase;

    @PutMapping("/{userId}/image")
    public ResponseEntity<UserAssetUploadResponseDto> uploadUserImage(
            @PathVariable String userId,
            @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        UserAssetUploadResponseDto responseDto = userAssetUseCase.uploadUserImage(userId, file);
        return ResponseEntity.ok(responseDto);
    }
}
