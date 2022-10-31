package org.marathon.service;


import org.marathon.dto.request.StockRequestDto;
import org.marathon.dto.request.UpdateStockRequestDto;
import org.marathon.dto.response.StockProfileResponseDto;
import org.marathon.exception.ErrorType;
import org.marathon.exception.StockServiceException;
import org.marathon.mapper.IStockMapper;
import org.marathon.repository.Entity.Stock;
import org.marathon.repository.IStockRepository;
import org.marathon.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class StockService extends ServiceManager<Stock, Long> {

    private final IStockRepository stockRepository;

    public StockService(IStockRepository stockRepository) {
        super(stockRepository);
        this.stockRepository = stockRepository;
    }
public  Stock addStock(StockRequestDto dto){
       return stockRepository.save(IStockMapper.INSTANCE.toStock(dto));
}


    public Stock updateStock(UpdateStockRequestDto dto) {
        Optional<Stock> mystock = stockRepository.findOptionalByProductId(dto.getProductId());
        if(mystock.isPresent()){
            Stock oldStock = mystock.get();
            oldStock.setStockAmount(dto.getStockAmount());
            stockRepository.save(oldStock);
            return oldStock;
        }else throw new StockServiceException(ErrorType.PRODUCT_ID_NOT_FOUND);
    }

    public List<StockProfileResponseDto> findAllCointaining(String productname) {
        List<Stock> stockList=stockRepository.findByProductnameContainingIgnoreCase( productname);
        if(stockList.size()>0) return IStockMapper.INSTANCE.toStockProfileResponseDto(stockList);
        else return Collections.emptyList();
}
}


