package com.ssafy.IMS.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {
	private int id;
	private String name;
	private String email;
	private String company1;
	private String company2;
	private String company3;
}
