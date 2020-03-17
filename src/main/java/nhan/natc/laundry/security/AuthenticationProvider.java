package nhan.natc.laundry.security;

import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import nhan.natc.laundry.service.TokenService;

@Component
public class AuthenticationProvider extends AbstractUserDetailsAuthenticationProvider{

	@Autowired
	TokenService tokenService;
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		// TODO Auto-generated method stub
		Object token = authentication.getCredentials();
		Calendar cal = Optional.ofNullable(token)
		.map(String::valueOf)
		.flatMap(tokenService::findExpiredTimeByToken)		
		.orElseThrow(() -> new UsernameNotFoundException("Cannot find user with accessToken : " + token));
		return null;
	}

}
