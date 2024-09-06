package com.example.uniclub06.Service.imp;

import com.example.uniclub06.Service.FileService;
import com.example.uniclub06.exception.FileNotFoundException;
import com.example.uniclub06.exception.SaveFileException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileServiceImp implements FileService {

    @Value("${root.path}")
    private String root;

    @Override
    public void savefile(MultipartFile file) {
        try {
            Path rootPath= Paths.get(root);
            if (!Files.exists(rootPath)){
                Files.createDirectory(rootPath);
            }
            Files.copy(file.getInputStream(),rootPath.resolve(file.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
        }catch (Exception e){
            throw new SaveFileException(e.getMessage());
        }
    }

    @Override
    public Resource loadfile(String filename) {
       try {
           Path rootPath= Paths.get(root);
           Path file = rootPath.resolve(filename);
           Resource resource = new UrlResource(file.toUri());
           if (resource.exists()){
               return resource;
           }else {
               throw new FileNotFoundException();
           }
       }catch (Exception e){
            throw new FileNotFoundException(e.getMessage());
       }
    }
}
