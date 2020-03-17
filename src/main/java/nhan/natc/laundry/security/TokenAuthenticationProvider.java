package nhan.natc.laundry.security;

import java.util.Calendar;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import nhan.natc.laundry.service.TokenService;

public class TokenAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	TokenService tokenService;
	
//	public TokenAuthenticationProvider(TokenService tokenService) {
//		// TODO Auto-generated constructor stub
//		this.tokenService = tokenService;
//	}

	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		String token = (String) authentication.getPrincipal();
        if (token == null || token.equals("")) {
            throw new BadCredentialsException("Invalid token");
        }
//        if (!tokenService.contains(token)) {
//            throw new BadCredentialsException("Invalid token or token expired");
//        }
        Optional<Calendar> calOpt = tokenService.findExpiredTimeByToken(token);
        if (calOpt.isPresent()) {
        	if (calOpt.get() == null)
        		throw new BadCredentialsException("Invalid token or token expired");
        	else {
        		Calendar cal = calOpt.get();
        		if (cal.before(Calendar.getInstance()))
        			throw new BadCredentialsException("Invalid token or token expired");
        		else
        			return new UsernamePasswordAuthenticationToken("", "", AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER"));
        	}
        }
        return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
//		return authentication.equals(PreAuthenticatedAuthenticationToken.class);
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
