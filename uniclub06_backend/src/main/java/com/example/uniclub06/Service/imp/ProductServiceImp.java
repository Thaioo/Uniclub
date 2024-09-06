package com.example.uniclub06.Service.imp;

import com.example.uniclub06.Entity.*;
import com.example.uniclub06.Repository.ColorRepository;
import com.example.uniclub06.Repository.ProductRepository;
import com.example.uniclub06.Repository.SizeRepository;
import com.example.uniclub06.Repository.VariantRepository;
import com.example.uniclub06.Request.AddProductRequest;
import com.example.uniclub06.Service.FileService;
import com.example.uniclub06.Service.ProductService;
import com.example.uniclub06.dto.ColorDTO;
import com.example.uniclub06.dto.ProductDTO;
import com.example.uniclub06.dto.SizeDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private VariantRepository variantRepository;

    @Autowired
    private FileService fileService;

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Transactional
    @Override
    public void addProduct(AddProductRequest request) {
        ProductEntity product = new ProductEntity();
        product.setName(request.name());
        product.setDesc(request.desc());
        product.setInfo(request.information());
        product.setPrice(request.price());

        BrandEntity brand = new BrandEntity();
        brand.setId(request.idBrand());
        product.setBrand(brand);
       ProductEntity productInserted= productRepository.save(product);
       VariantEntity variant = new VariantEntity();
       variant.setProduct(productInserted);
        ColorEntity color = new ColorEntity();
        color.setId(request.idColor());
        variant.setColor(color);
        SizeEntity size = new SizeEntity();
        size.setId(request.idSize());
        variant.setSize(size);
        variant.setPrice(request.priceSize());
        variant.setQuantity(request.quantity());
        variant.setImages(request.file().getOriginalFilename());
        variantRepository.save(variant);
        fileService.savefile(request.file());
    }

    @Override
    public List<ProductDTO> getAllProducts(int numpage) {

        Pageable page = PageRequest.of(numpage, 4);

       return    productRepository.findAll(page).stream().map(item ->{
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(item.getId());
            productDTO.setName(item.getName());
            productDTO.setPrice(item.getPrice());
            if (item.getVariants().size() > 0) {
                productDTO.setLink("http://localhost:8080/file/" + item.getVariants().getFirst().getImages());
            }
            else {
                productDTO.setLink(" ");
            }
            return productDTO;
        }).toList();
    }

    @Override
    public ProductDTO getProduct(int id) {
        Optional<ProductEntity> product = productRepository.findById(id);
    return    product.stream().map(productEntity -> {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setId(productEntity.getId());
            productDTO.setName(productEntity.getName());
            productDTO.setCategories(productEntity.getProductCategories().stream().map(productCategory ->
                    productCategory.getCategory().getName()).toList());



            productDTO.setSizes(sizeRepository.findAll().stream().map(sizeEntity -> {
                SizeDTO sizeDTO = new SizeDTO();
                sizeDTO.setId(sizeEntity.getId());
                sizeDTO.setName(sizeEntity.getName());
                return sizeDTO;

            }).toList());

            productDTO.setColors(colorRepository.findAll().stream().map(colorEntity -> {
                ColorDTO colorDTO = new ColorDTO();
                colorDTO.setId(colorEntity.getId());
                colorDTO.setName(colorEntity.getName());
                return colorDTO;
            }).toList());

//            productDTO.setSizes(productEntity.getVariants().stream().map(variantEntity ->{
//                SizeDTO sizeDTO = new SizeDTO();
//                sizeDTO.setId(variantEntity.getSize().getId());
//                sizeDTO.setName(variantEntity.getSize().getName());
//                return sizeDTO;
//            }).toList());

            productDTO.setPriceColorSize(productEntity.getVariants().stream().map(variantEntity -> {
                ColorDTO colorDTO = new ColorDTO();
                colorDTO.setImages(variantEntity.getImages());
                colorDTO.setName(variantEntity.getColor().getName());
                colorDTO.setSizes(productEntity.getVariants().stream().map(variantEntity1 -> {
                    SizeDTO sizeDTO = new SizeDTO();
                    sizeDTO.setId(variantEntity1.getSize().getId());
                    sizeDTO.setName(variantEntity1.getSize().getName());
                    sizeDTO.setQuantity(variantEntity1.getQuantity());
                    sizeDTO.setPrice(variantEntity1.getPrice());
                    return sizeDTO;
                }).toList());
                return colorDTO;
            }).toList());


            return productDTO;
        }).findFirst().orElseThrow(()-> new RuntimeException("can't find data"));
    }
}
