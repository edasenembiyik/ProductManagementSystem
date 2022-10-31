package org.marathon.manager;

import org.marathon.dto.request.StockRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.marathon.constant.ApiUrls.ADDSTOCK;

@FeignClient(url="http://localhost:8091/api/v1/stock" ,name="stock-service-application", decode404 = true)
public interface IStockManager {
    @PostMapping(ADDSTOCK)
    public ResponseEntity<Boolean> createStock(@RequestBody StockRequestDto dto);
}
