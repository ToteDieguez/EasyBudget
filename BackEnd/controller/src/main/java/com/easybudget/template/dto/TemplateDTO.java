package com.easybudget.template.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class TemplateDTO {

    private Long templateID;
    private String name;
    private Map<Long, String> categories;

}
