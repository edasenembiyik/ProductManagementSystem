package org.marathon.service;


import org.marathon.dto.request.ProductSaveRequestDto;
import org.marathon.dto.request.StockRequestDto;
import org.marathon.exception.ErrorType;
import org.marathon.exception.ProductServiceException;
import org.marathon.manager.IStockManager;
import org.marathon.mapper.IProductMapper;
import org.marathon.repository.IProductRepository;
import org.marathon.repository.entity.Product;

import org.marathon.repository.enums.ProductCategory;
import org.marathon.utility.ServiceManager;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends ServiceManager<Product, Long> {

    private final IProductRepository productRepository;
    private final IStockManager stockManager;

    public ProductService(IProductRepository productRepository, IStockManager stockManager) {
        super(productRepository);
        this.productRepository = productRepository;
        this.stockManager = stockManager;
    }


    public Product saveProduct(ProductSaveRequestDto dto, ProductCategory productCategory) {
        Product product = IProductMapper.INSTANCE.toProduct(dto);

        try {
            product.setProductCategory(productCategory);
            save(product);
            stockManager.createStock(StockRequestDto.builder().productId(product.getId()).productname(product.getProductname()).build());

            return product;
        } catch (Exception e) {
            if (e instanceof DataIntegrityViolationException) {
                throw new ProductServiceException(ErrorType.NOT_UNIQUE_PRODUCT_CODE);
            }
            throw new ProductServiceException(ErrorType.PRODUCT_NOT_CREATED);
        }
    }
}
