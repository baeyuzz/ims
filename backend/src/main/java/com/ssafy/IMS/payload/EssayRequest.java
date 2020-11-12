package com.ssafy.IMS.payload;

import com.ssafy.IMS.model.User;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EssayRequest {

    @NotBlank
    private String email;

    @NotBlank
    private String content;

    private String result1;
    private String result2;
    private String result3;
    private String result4;
    private String result5;
    private String result6;
    private String result7;
    private String result8;

}
