package com.unauth.dto.request;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationDto {
	
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Long mobileNumber;
	private LocalDateTime createdAt;
	private String about;
	private String status;
    private String image;
}
