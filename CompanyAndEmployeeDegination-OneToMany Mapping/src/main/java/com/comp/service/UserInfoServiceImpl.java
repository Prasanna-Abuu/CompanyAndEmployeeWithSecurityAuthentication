package com.comp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.comp.model.UserInfo;
import com.comp.repository.UserInfoRepository;

@Service
public class UserInfoServiceImpl implements UserInfoService {
	
	@Autowired
	private UserInfoRepository repository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	boolean flag;
	
	@Override
	public UserInfo addUser(UserInfo userInfo) {
		userInfo.setPassword(encoder.encode(userInfo.getPassword()));
		return repository.save(userInfo);
	}
	

	
}