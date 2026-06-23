package com.debasish.scanstockiq.dto;

import com.debasish.scanstockiq.entity.ReturnCondition;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReturnRequest {

    private String upcCode;

    private Integer quantity;

    private ReturnCondition returnCondition;

    private String remarks;

}

