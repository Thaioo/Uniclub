package com.example.uniclub06.Service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    void savefile (MultipartFile file);
    Resource loadfile (String filename);
}
