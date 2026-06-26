package com.unauth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unauth.dto.request.RegistrationDto;
import com.unauth.dto.response.GeneralResponse;
import com.unauth.entity.UnAuthUser;
import com.unauth.exception.AdminException;
import com.unauth.exception.UnAuthUserException;
import com.unauth.service.UnAuthUserService;
import com.unauth.util.Request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/unauth")
public class UnauthController {
	
	@Autowired
	UnAuthUserService service;
	
	@Value("${unauth.internal.secret}")
    private String expectedSecret;
	
	@PostMapping("/addUnAuthUser")
	public ResponseEntity<GeneralResponse> addUnAuthUser(@RequestHeader("X-Internal-Secret") String secretKey, @RequestBody Request<RegistrationDto> req, HttpServletRequest request, HttpServletResponse response) throws UnAuthUserException{
		GeneralResponse res = new GeneralResponse();
		if(!secretKey.equalsIgnoreCase(expectedSecret)) {
			return new ResponseEntity<>(res.unauthenticated(),HttpStatus.UNAUTHORIZED);		
		}
		
			try {
				return ResponseEntity.ok(res.success(service.addUnAuthUser(req.getRequestBody()),req.getRequestHeader()));
			} catch (Exception e) {
				return new ResponseEntity<>(res.failure(e.getMessage()),HttpStatus.BAD_REQUEST);
			}
	}
	
	@PostMapping("/getUnAuthUserById/{id}")
	public ResponseEntity<GeneralResponse> getUnAuthUserById(
			@RequestHeader("X-Internal-Secret") String secretKey, 
			@PathVariable Long id,
			@RequestBody Request<?> req) {
		GeneralResponse res = new GeneralResponse();
		if (!secretKey.equalsIgnoreCase(expectedSecret)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res.unauthenticated());
		}

		try {
			return ResponseEntity.ok(res.success(service.getUnAuthUserById(id), req.getRequestHeader()));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(res.failure(e.getMessage()));
		}
	}
	
	@PostMapping("/getUnAuthUserByEmail/{mail}")
	public ResponseEntity<GeneralResponse> getUnAuthUserByEmail(
			@RequestHeader("X-Internal-Secret") String secretKey, 
			@PathVariable String mail,
			@RequestBody Request<?> req) {
		GeneralResponse res = new GeneralResponse();
		if (!secretKey.equalsIgnoreCase(expectedSecret)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res.unauthenticated());
		}

		try {
			return ResponseEntity.ok(res.success(service.getUnAuthUserByEmail(mail), req.getRequestHeader()));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(res.failure(e.getMessage()));
		}
	}

	@PostMapping("/getUnAuthUserByStatus/{status}")
	public ResponseEntity<GeneralResponse> getUnauAuthUserByStatus(
			@RequestHeader("X-Internal-Secret") String secretKey, 
			@PathVariable String status,
			@RequestBody Request<?> req) {
		GeneralResponse res = new GeneralResponse();
		if (!secretKey.equalsIgnoreCase(expectedSecret)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res.unauthenticated());
		}

		try {
			return ResponseEntity.ok(res.success(service.getUnauAuthUserByStatus(status), req.getRequestHeader()));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(res.failure(e.getMessage()));
		}
	}

	@PostMapping("/deleteUnAuthUserById/{id}")
	public ResponseEntity<GeneralResponse> deleteUnAuthUserById(
			@RequestHeader("X-Internal-Secret") String secretKey, 
			@PathVariable Long id,
			@RequestBody Request<?> req) {
		GeneralResponse res = new GeneralResponse();
		if (!secretKey.equalsIgnoreCase(expectedSecret)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res.unauthenticated());
		}

		try {
			return ResponseEntity.ok(res.success(service.deleteUnAuthUserById(id), req.getRequestHeader()));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(res.failure(e.getMessage()));
		}
	}

	@PostMapping("/rejectUserById/{id}")
	public ResponseEntity<GeneralResponse> rejectUserById(
			@RequestHeader("X-Internal-Secret") String secretKey, 
			@PathVariable Long id,
			@RequestBody Request<?> req) {
		GeneralResponse res = new GeneralResponse();
		if (!secretKey.equalsIgnoreCase(expectedSecret)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res.unauthenticated());
		}

		try {
			return ResponseEntity.ok(res.success(service.rejectUserById(id), req.getRequestHeader()));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(res.failure(e.getMessage()));
		}
	}

	@PutMapping("/updateUnauthUser/{id}")
	public ResponseEntity<GeneralResponse> updateUnauthUserById(
			@RequestHeader("X-Internal-Secret") String secretKey, 
			@PathVariable Long id, 
			@RequestBody Request<RegistrationDto> req) {
		GeneralResponse res = new GeneralResponse();
		if (!secretKey.equalsIgnoreCase(expectedSecret)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res.unauthenticated());
		}

		try {
			return ResponseEntity.ok(res.success(service.updateUserById(id, req.getRequestBody()), req.getRequestHeader()));
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(res.failure(e.getMessage()));
		}
	}
}
