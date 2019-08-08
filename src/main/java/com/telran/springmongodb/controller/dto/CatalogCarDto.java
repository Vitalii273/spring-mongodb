package com.telran.springmongodb.controller.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class CatalogCarDto {
    private String id;
    private String model;
    private List<String> colors;
    private BigDecimal price;
}
