package com.ssafy.IMS.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CompanyRequest {

    @NotBlank
    private String name;

    private String result1;
    private String result2;
    private String result3;
    private String result4;
    private String result5;
    private String result6;
    private String result7;
    private String result8;
}
