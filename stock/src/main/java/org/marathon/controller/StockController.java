package org.marathon.controller;


import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.marathon.dto.request.StockRequestDto;
import org.marathon.dto.request.UpdateStockRequestDto;
import org.marathon.dto.response.StockProfileResponseDto;
import org.marathon.exception.ErrorType;
import org.marathon.exception.StockServiceException;
import org.marathon.mapper.IStockMapper;
import org.marathon.service.StockService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.marathon.constant.ApiUrls.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(STOCK)
public class StockController {

    private final StockService stockService;

    @PostMapping(ADDSTOCK)
    public ResponseEntity<Boolean> createStock(@RequestBody StockRequestDto dto) {
        try {
            stockService.addStock(dto);
            return ResponseEntity.ok(true);
        } catch (Exception e){
            throw new StockServiceException(ErrorType.STOCK_NOT_SAVE);
        }
    }


    @PostMapping(UPDATESTOCK)
    @Operation(summary = "Urun id si kullanarak stogu degistiren method")
    public ResponseEntity<StockProfileResponseDto> updateStock(@RequestBody UpdateStockRequestDto dto) {
        try {
            stockService.updateStock(dto);
            return ResponseEntity.ok(IStockMapper.INSTANCE.toStockProfileResponseDto(stockService.updateStock(dto)));
        } catch (Exception e){
            throw new StockServiceException(ErrorType.STOCK_NOT_SAVE);
        }
    }

    @GetMapping(GETALL)
    @Operation(summary = "Stoktaki tüm ürünlerin adını ve stok miktarını getiren method")
    public ResponseEntity<List<StockProfileResponseDto>> findAll(){

        return    ResponseEntity.ok(IStockMapper.INSTANCE.toStockProfileResponseDto(stockService.findAll()));
    }

    @GetMapping(FINDBYPRODUCTNAME)
    @Operation(summary = "Girilen Stringi ürün adlarında arayıp , bu stringi içeren ürünleri getiren method")
    public ResponseEntity<List<StockProfileResponseDto>>findAllCointaining(@RequestParam String productname){
        return  ResponseEntity.ok(stockService.findAllCointaining(productname));
    }
}
