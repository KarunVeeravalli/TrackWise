package com.unauth.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.unauth.dto.request.RegistrationDto;
import com.unauth.entity.UnAuthUser;
import com.unauth.exception.AdminException;
import com.unauth.exception.UnAuthUserException;
import com.unauth.repo.UnAuthUserRepo;
import com.unauth.util.RepoHelper;

@Service
public class UnAuthUserServiceImpl implements UnAuthUserService{
	
	@Autowired
	UnAuthUserRepo repository;
	
	@Autowired
	RepoHelper helper;
	
	@Override
	public UnAuthUser addUnAuthUser(RegistrationDto dto) throws UnAuthUserException {
		UnAuthUser user = new UnAuthUser();
//		user.setFirstName(dto.getFirstName());
//		user.setLastName(dto.getLastName());
//		user.setAadhaarNumber(dto.getAadhaarNumber());
//		user.setPanCardNumber(dto.getPanCardNumber());
//		user.setAadhaarCard(dto.getAadhaarCard());
//		user.setPanCard(dto.getPanCard());
//		user.setAbout(dto.getAbout());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
//		user.setImage(dto.getImage());
		user.setUsername(dto.getUsername());
		user.setMobileNumber(dto.getMobileNumber());
		user.setStatus("PENDING");
		user.setCreatedAt(LocalDateTime.now());
		return repository.save(user);
	}

	@Override
	public UnAuthUser getUnAuthUserById(Long id) throws UnAuthUserException {
		return repository.findById(id).orElseThrow(()->new UnAuthUserException("User is not found with id: "+id));
	}

	@Override
	public UnAuthUser getUnAuthUserByEmail(String mail) throws UnAuthUserException {
		return repository.findByEmail(mail);
	}

	@Override
	public List<UnAuthUser> getUnauAuthUserByStatus(String status) throws UnAuthUserException, AdminException {
		return repository.findAllByStatus(status);
	}

	@Override
	public UnAuthUser deleteUnAuthUserById(Long id) throws UnAuthUserException {
		UnAuthUser user = getUnAuthUserById(id);
		repository.deleteById(id);
		return user;
	}

	@Override
	public String rejectUserById(Long id) throws UnAuthUserException {
		UnAuthUser user = getUnAuthUserById(id);
		user.setStatus("REJECTED");
		repository.save(user);
		return "REJECTED";
	}

	@Override
	public UnAuthUser updateUserById(Long id, RegistrationDto dto) throws UnAuthUserException {
		UnAuthUser oldUnAuthUser = getUnAuthUserById(id);
		BeanUtils.copyProperties(dto, oldUnAuthUser, helper.getNullPropertyNames(dto));
		
		return repository.save(oldUnAuthUser);
	}

	@Override
	public UnAuthUser getUnAuthUserByUsername(String name) throws UnAuthUserException {
		
		return repository.findByUsername(name);
	}

	@Override
	public List<String> getAllUnauthUsername() {
		return repository.findAllUsername();
	}

}
