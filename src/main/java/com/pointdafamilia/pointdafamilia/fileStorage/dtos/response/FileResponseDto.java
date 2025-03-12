package com.pointdafamilia.pointdafamilia.fileStorage.dtos.response;

import com.pointdafamilia.pointdafamilia.fileStorage.enums.FileType;

public record FileResponseDto(
    Long id,
    String url,
    FileType fileType
) {
} 
