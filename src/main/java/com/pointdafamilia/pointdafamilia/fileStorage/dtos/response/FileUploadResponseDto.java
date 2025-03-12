package com.pointdafamilia.pointdafamilia.fileStorage.dtos.response;

import com.pointdafamilia.pointdafamilia.fileStorage.enums.FileType;

public record FileUploadResponseDto(
    String fileId,
    FileType fileType
) {
}
