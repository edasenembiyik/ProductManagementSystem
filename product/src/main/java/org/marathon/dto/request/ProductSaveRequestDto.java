package org.marathon.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductSaveRequestDto {

    @NotBlank
    @Size(min=2,max=30)
    private String productname;
    @Size(min=2,max=30)
    private  String brand;
    @NotBlank
    private String productCode;
}
