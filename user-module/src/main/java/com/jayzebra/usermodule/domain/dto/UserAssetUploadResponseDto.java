package com.jayzebra.usermodule.domain.dto;

import lombok.Data;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserAssetUploadResponseDto {
    private String userId;
    private String newProfileImageUrl;
}

