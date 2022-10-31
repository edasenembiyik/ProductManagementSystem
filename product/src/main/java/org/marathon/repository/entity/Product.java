package org.marathon.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.marathon.repository.enums.ProductCategory;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity

public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String productname;
    private  String brand;
    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;
    @Column(unique = true,nullable = false)
    private String productCode;



}
