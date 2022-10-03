package com.barzykin.demo.hqljoin;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ProductWithTypeDto {
    private String name;
    private String type;
    private int price;
}
