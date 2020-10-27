package com.ssafy.IMS.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfile {
	private Long id;
	private String name;
	private String email;
}
