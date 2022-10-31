package org.marathon.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockRequestDto {
    private  Long productId;
    private int stockAmount;
    private String productname;

}
