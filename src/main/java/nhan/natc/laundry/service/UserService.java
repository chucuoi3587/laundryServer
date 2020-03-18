package nhan.natc.laundry.service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import nhan.natc.laundry.data.local.RoleDAO;
import nhan.natc.laundry.data.local.TokenDAO;
import nhan.natc.laundry.data.local.UserDAO;
import nhan.natc.laundry.data.remote.RegisterDto;
import nhan.natc.laundry.data.remote.UserFilterRequest;
import nhan.natc.laundry.data.repository.DBFileRepository;
import nhan.natc.laundry.data.repository.RoleRepository;
import nhan.natc.laundry.data.repository.UserRepository;
import nhan.natc.laundry.data.repository.UserStastusRepository;
import nhan.natc.laundry.exception.DefaultException;
import nhan.natc.laundry.exception.UserNotFoundException;
import nhan.natc.laundry.payload.UserResponse;
import nhan.natc.laundry.util.CommonUtil;

@Service("userService")
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired 
	TokenService tokenService;
	
	@Autowired
	DBFileRepository dbFileRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserStastusRepository userStatusRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public UserResponse createUser(RegisterDto user) {
		UserDAO userDao = new UserDAO(user.getFirstName(), user.getLastName(), user.getEmail(), passwordEncoder.encode(user.getPassword()), 
				userStatusRepository.findById(1).orElse(null), roleRepository.findById(user.getRoleId()).orElse(null));
		if (!CommonUtil.stringIsNullOrEmpty(user.getAvatar()))
			userDao.setAvatar(dbFileRepository.findById(user.getAvatar()).orElse(null));
		try {
			userDao = userRepository.save(userDao);			
			return new UserResponse(userDao.getId(), userDao.getEmail(), userDao.getFirstName(), userDao.getLastName(), userDao.getUserStatus(), userDao.getRole(), userDao.getAvatar());
		} catch (Exception e) {
			if (e instanceof DataIntegrityViolationException) {
				throw new DefaultException("Email " + userDao.getEmail() + " existed.");
			} else {
				throw new DefaultException("Error " + e.getMessage());
			}
		}
	}
	
	public TokenDAO checkUserPassword(String password, String email) {
		System.out.println("=== check User Pass ");
		Optional<UserDAO> user = userRepository.findUserPassword(email);
		if (user.isPresent()) {
			System.out.println("=== check User : " + user.get().getEmail());
			if (user != null && passwordEncoder.matches(password, user.get().getPassword())) {			
				String token = tokenService.generateNewToken();
				System.out.println("=== token : " + token);
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.MINUTE, +30);
//				cal.set(Calendar.ZONE_OFFSET, TimeZone.getTimeZone("UTC").getRawOffset());
				TokenDAO tokenDao = new TokenDAO(token, user.get(), cal.getTimeInMillis());
//				tokenService.storeToken(token, new UsernamePasswordAuthenticationToken(token, token));
//				tokenService.storeToken(token, new PreAuthenticatedAuthenticationToken(email, password, AuthorityUtils.commaSeparatedStringToAuthorityList("USER")));
				return tokenService.saveToken(tokenDao);
			}
		}		
		return null;
	}
	
	public List<UserResponse> findAll(UserFilterRequest filter) {
		Pageable page = PageRequest.of(filter.getFetchPage(), filter.getFetchLimit());
		Page<UserDAO> userList = userRepository.findAll(page); 
		filter.setHasMoreRecord(filter.getFetchPage() < userList.getTotalPages() - 1);
		return userList.stream()
				.map(UserResponse::fromEntity)
				.collect(Collectors.toList());
	}
	
	public UserResponse updateUser(RegisterDto register) {
		Optional<UserDAO> user = userRepository.findById(register.getId());
		if (user.isPresent()) {
			UserDAO userDao = user.get();
			if (!CommonUtil.stringIsNullOrEmpty(register.getEmail()))
				userDao.setEmail(register.getEmail());
			if (!CommonUtil.stringIsNullOrEmpty(register.getFirstName()))
				userDao.setFirstName(register.getFirstName());
			if (!CommonUtil.stringIsNullOrEmpty(register.getLastName()))
				userDao.setLastName(register.getLastName());
			if (!CommonUtil.stringIsNullOrEmpty(register.getPassword()))
				userDao.setPassword(passwordEncoder.encode(register.getPassword()));
			if (!CommonUtil.stringIsNullOrEmpty(register.getAvatar()))
				userDao.setAvatar(dbFileRepository.findById(register.getAvatar()).orElse(null));
			userDao = userRepository.save(userDao);
			return new UserResponse(userDao.getId(), userDao.getEmail(), userDao.getFirstName(), userDao.getLastName(), userDao.getUserStatus(), userDao.getRole(), userDao.getAvatar());
		}
		throw new DefaultException("User not found");
	}
	
	public UserResponse findUserByToken(String token) {
		Optional<TokenDAO> tokenOpt = tokenService.findTokenByToken(token);
		if (tokenOpt.isPresent()) {
			TokenDAO tokenDao = tokenOpt.get();
			return new UserResponse(tokenDao.getUser().getId(), tokenDao.getUser().getEmail(), tokenDao.getUser().getFirstName(), tokenDao.getUser().getLastName(), tokenDao.getUser().getUserStatus(), tokenDao.getUser().getRole(), tokenDao.getUser().getAvatar());
		}
		return null;
	}
	
	public UserResponse findUserById(Long id) {
		Optional<UserDAO> user = userRepository.findById(id);
		if (user.isPresent()) {
			UserDAO userDao = user.get();
			return new UserResponse(userDao.getId(), userDao.getEmail(), userDao.getFirstName(), userDao.getLastName(), userDao.getUserStatus(), userDao.getRole(), userDao.getAvatar());
		}
		throw new UserNotFoundException("User not found.");
	}
	
	public List<RoleDAO> getAllRole() {
		return roleRepository.findAll();
	}
}
