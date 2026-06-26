package com.unauth.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "UNAUTHUSER", uniqueConstraints = @UniqueConstraint(columnNames = {"email","mobileNumber","username"}))
public class UnAuthUser {
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private Long mobileNumber;
	private LocalDateTime createdAt;
	private String about;
	private String status;
	@Lob
    private String image;

}
