package com.hexaware.eventmanagement.service;

import com.hexaware.eventmanagement.dto.JWTAuthResponse;
import com.hexaware.eventmanagement.dto.LoginDto;
import com.hexaware.eventmanagement.dto.RegisterDto;

public interface AuthService {
	JWTAuthResponse login(LoginDto dto);
	String register(RegisterDto dto);
}