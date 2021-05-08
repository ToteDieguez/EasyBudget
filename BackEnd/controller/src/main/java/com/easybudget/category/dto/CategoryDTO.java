package com.easybudget.category.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class CategoryDTO {

    private Long id;
    private String name;
    private String type;
    private Map<Long, String> subCategories;
}
