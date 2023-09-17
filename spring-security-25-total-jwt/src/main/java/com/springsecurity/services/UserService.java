package com.springsecurity.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springsecurity.entities.Otp;
import com.springsecurity.entities.User;
import com.springsecurity.repositories.OtpRepository;
import com.springsecurity.repositories.UserRepository;
import com.springsecurity.utils.GenerateCodeUtil;

@Service
@Transactional
public class UserService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OtpRepository otpRepository;
	
	public void addUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}
	
	public void auth(User user) {
		var u = userRepository.findUserByUsername(user.getUsername());
		// 1차 인증 성공, 존재하는 사용자로 2차 인증에 필요한 OTP 발급
		if (u.isPresent() && matchPassword(user, u)) {
			renewOtp(u.get());
		} else {
			throw new BadCredentialsException("Bad credentials !!!");
		}
	}
	
	private boolean matchPassword(User user, Optional<User> u) {
		return passwordEncoder.matches(user.getPassword(), u.get().getPassword());
	}
	
	private void renewOtp(User user) {
		String code = GenerateCodeUtil.generateCode();
		
		var o = otpRepository.findOtpByUsername(user.getUsername());
		
		if(o.isPresent()) {
			o.get().setCode(code);
		} else {
			newOtp(user, code);
		}
	}
	
	private void newOtp(User user, String code) {
		Otp otp = new Otp();
		otp.setUsername(user.getUsername());
		otp.setCode(code);
		otpRepository.save(otp);
	}
	
	public boolean check(Otp otp) {
		var o = otpRepository.findOtpByUsername(otp.getUsername());
		
		return o.isPresent() && matchCode(otp, o);
	}

	private boolean matchCode(Otp otp, Optional<Otp> o) {
		return otp.getCode().equals(o.get().getCode());
	}
}
