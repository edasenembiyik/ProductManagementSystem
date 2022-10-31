package org.marathon.controller;


import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.marathon.dto.request.ProductSaveRequestDto;
import org.marathon.dto.response.ProductResponseDto;
import org.marathon.mapper.IProductMapper;
import org.marathon.repository.entity.Product;
import org.marathon.repository.enums.ProductCategory;
import org.marathon.service.ProductService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.marathon.constant.ApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(PRODUCT)
public class ProductController {

    private final ProductService productService;

    @PostMapping(SAVE)
    @Operation(summary = "Yeni ürün oluşturan ve stoğa kaydeden method")
    public ResponseEntity<ProductResponseDto> saveProduct(@RequestBody ProductSaveRequestDto dto, @RequestParam ProductCategory productCategory) {
        return ResponseEntity.ok(IProductMapper.INSTANCE.toProductResponseDto(productService.saveProduct(dto,productCategory)));
    }

    @GetMapping(GETALLPRODUCT)
    @Operation(summary = "Tüm kayıtlı ürünleri getiren method")
    public ResponseEntity<List<ProductResponseDto>> findAll(){

        return ResponseEntity.ok(IProductMapper.INSTANCE.toProductResponseDto(productService.findAll()));

    }

}
