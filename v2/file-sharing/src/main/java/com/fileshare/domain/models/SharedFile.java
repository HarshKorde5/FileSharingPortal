package com.fileshare.domain.models;

import java.util.Objects;
import java.util.UUID;

public record SharedFile(UUID fileId, String fileName, long fileSize, String fileExtension) {
    
    public SharedFile {

        Objects.requireNonNull(fileId, "File ID cannot be null.");

        if (fileName == null || fileName.trim().isEmpty()) {
            throw new IllegalArgumentException("File name cannot be null or empty.");
        }

        if (fileSize < 0) {
            throw new IllegalArgumentException("File size cannot be negative.");
        }

        if (fileExtension == null || fileExtension.trim().isEmpty()) {
            throw new IllegalArgumentException("File extension cannot be null or empty.");
        }

        fileName = fileName.trim();
        fileExtension = fileExtension.trim();
    }
}