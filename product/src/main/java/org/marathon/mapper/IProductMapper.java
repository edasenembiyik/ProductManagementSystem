package org.marathon.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.marathon.dto.request.ProductSaveRequestDto;
import org.marathon.dto.request.StockRequestDto;
import org.marathon.dto.response.ProductResponseDto;
import org.marathon.repository.entity.Product;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IProductMapper {
    IProductMapper INSTANCE = Mappers.getMapper(IProductMapper.class);

    Product toProduct(final ProductSaveRequestDto dto);

    StockRequestDto toStockAddRequestDto(final Product product);

    List<ProductResponseDto> toProductResponseDto(final List<Product> product);

    ProductResponseDto toProductResponseDto(final Product product);


}
