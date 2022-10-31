package org.marathon.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.marathon.dto.request.StockRequestDto;
import org.marathon.dto.request.UpdateStockRequestDto;
import org.marathon.dto.response.StockProfileResponseDto;
import org.marathon.repository.Entity.Stock;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IStockMapper {
    IStockMapper INSTANCE = Mappers.getMapper(IStockMapper.class);

    Stock toStock(final StockRequestDto dto);
    Stock toStock(final UpdateStockRequestDto dto);

    List<StockProfileResponseDto> toStockProfileResponseDto(final List<Stock> stock);
    StockProfileResponseDto toStockProfileResponseDto(final Stock stock);


}