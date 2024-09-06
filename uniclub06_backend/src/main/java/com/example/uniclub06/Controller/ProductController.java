package com.example.uniclub06.Controller;

import com.example.uniclub06.Reponse.BaseReponse;
import com.example.uniclub06.Request.AddProductRequest;
import com.example.uniclub06.Service.FileService;
import com.example.uniclub06.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<?>addProduct(AddProductRequest addProductRequest){
        productService.addProduct(addProductRequest);
        return new ResponseEntity<>("hello add product", HttpStatus.OK);
    }

    @GetMapping("/{page}")
    public ResponseEntity<?>getProduct(@PathVariable int page){
        BaseReponse baseReponse = new BaseReponse();
        baseReponse.setStatusCode(200);
        baseReponse.setMessage("success");
        baseReponse.setData(productService.getAllProducts(page));
        return new ResponseEntity<>(baseReponse, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<?>getDetailProduct(@PathVariable int id){
        BaseReponse baseReponse = new BaseReponse();
        baseReponse.setStatusCode(200);
        baseReponse.setMessage("success");
        baseReponse.setData(productService.getProduct(id));
        return new ResponseEntity<>(baseReponse, HttpStatus.OK);
    }
}
