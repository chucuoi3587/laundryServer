package nhan.natc.laundry.controller;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import nhan.natc.laundry.data.local.TokenDAO;
import nhan.natc.laundry.data.local.UserDAO;
import nhan.natc.laundry.data.remote.LoginDto;
import nhan.natc.laundry.data.remote.RegisterDto;
import nhan.natc.laundry.data.remote.UserFilterRequest;
import nhan.natc.laundry.exception.UserNotFoundException;
import nhan.natc.laundry.payload.DefaultResponse;
import nhan.natc.laundry.payload.TokenResponse;
import nhan.natc.laundry.payload.UserResponse;
import nhan.natc.laundry.service.UserService;
import nhan.natc.laundry.util.CommonUtil;

@RestController
public class UserController {

	@Autowired
	UserService userService;	
		
	@PostMapping("/user/register")
	public ResponseEntity<DefaultResponse> userRegistration(@RequestBody RegisterDto user) {			
		return ResponseEntity.status(HttpStatus.OK).body(new DefaultResponse(userService.createUser(user)));
	}
	
	@PostMapping("/user/login")
	public ResponseEntity<DefaultResponse> login(@RequestBody LoginDto login) {
		TokenDAO tokenDao = userService.checkUserPassword(login.getPassword(), login.getEmail());
		if (tokenDao != null) {
			return ResponseEntity.status(HttpStatus.OK).body(new DefaultResponse(new TokenResponse(tokenDao.getToken())));
		}
		throw new UserNotFoundException("Invalid username or password!");
	}
		
	@PostMapping("/user/all")
	public ResponseEntity<DefaultResponse> getAll(@RequestBody UserFilterRequest filter) {
		return ResponseEntity.status(HttpStatus.OK).body(new DefaultResponse(userService.findAll(filter), filter.isHasMoreRecord()));		
	}
	
	@GetMapping("/user/me")
	public ResponseEntity<DefaultResponse> findMe(@RequestHeader(value = "Authorization") String authorizeHeader) {
		if (!CommonUtil.stringIsNullOrEmpty(authorizeHeader)) {
			UserResponse uResponse = userService.findUserByToken(StringUtils.removeStart(authorizeHeader, "Bearer").trim());
			if (uResponse != null)
				return ResponseEntity.status(HttpStatus.OK).body(new DefaultResponse(uResponse));			
		}
		throw new UserNotFoundException("User not found");
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<DefaultResponse> findUserById(@PathVariable("id") Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(new DefaultResponse(userService.findUserById(id)));	
	}
	
	@PostMapping("/user/update")
	public ResponseEntity<DefaultResponse> updateUser(@RequestBody RegisterDto user) {
		return ResponseEntity.status(HttpStatus.OK).body(new DefaultResponse(userService.updateUser(user)));
	}
		
	@GetMapping("/user/roles")
	public ResponseEntity<DefaultResponse> getAllRole() {
		return ResponseEntity.status(HttpStatus.OK).body(new DefaultResponse(userService.getAllRole()));
	}
}
