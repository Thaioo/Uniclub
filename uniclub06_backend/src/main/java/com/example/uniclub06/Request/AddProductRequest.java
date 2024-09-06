package com.example.uniclub06.Request;

import org.springframework.web.multipart.MultipartFile;

public record AddProductRequest (String name,
                                 String desc,
                                 String information,
                                 double price,
                                 int idBrand,
                                 int idColor,
                                 int idSize,
                                 MultipartFile file,
                                 int quantity,
                                 double priceSize) {
}
