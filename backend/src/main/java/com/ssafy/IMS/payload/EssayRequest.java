package com.ssafy.IMS.payload;

import com.ssafy.IMS.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EssayRequest {

    @NotBlank
    String email;

    @NotBlank
    String content;

    String result1;
    String result2;
    String result3;
    String result4;
    String result5;
    String result6;
    String result7;
    String result8;

}
