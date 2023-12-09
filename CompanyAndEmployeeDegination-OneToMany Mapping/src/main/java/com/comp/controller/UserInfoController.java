package com.comp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.comp.model.UserInfo;
import com.comp.service.UserInfoService;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserInfoController {

	@Autowired
	private UserInfoService service;

	@PostMapping("/save")
	public String addNewUser(@RequestBody UserInfo userInfo) {
		service.addUser(userInfo);
		return "User Saved";
	}

}
