package com.example.uniclub06.Service;

import com.example.uniclub06.Request.AddProductRequest;
import com.example.uniclub06.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    void addProduct(AddProductRequest request);
    List<ProductDTO> getAllProducts(int page);
    ProductDTO getProduct(int id);
}
