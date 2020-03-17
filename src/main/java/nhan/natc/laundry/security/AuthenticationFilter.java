package nhan.natc.laundry.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.apache.commons.lang3.StringUtils;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class AuthenticationFilter extends AbstractAuthenticationProcessingFilter{

	protected AuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
		super(requiresAuthenticationRequestMatcher);
		// TODO Auto-generated constructor stub
	}

	 @Override

	    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
	        //Optional<String> tokenParam = Optional.ofNullable(httpServletRequest.getHeader(AUTHORIZATION)); //Authorization: Bearer TOKEN
			 if (httpServletRequest.getRequestURL().toString().contains("/user/register") ||
					 httpServletRequest.getRequestURL().toString().contains("/user/login") ||
					 httpServletRequest.getRequestURL().toString().contains("/file/download")) {
				 return new UsernamePasswordAuthenticationToken("", "", AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ANONYMOUS"));
			 }
	        String token= StringUtils.isNotEmpty(httpServletRequest.getHeader(AUTHORIZATION))? httpServletRequest.getHeader(AUTHORIZATION) : "";
	        token= StringUtils.removeStart(token, "Bearer").trim();
	        Authentication requestAuthentication = new UsernamePasswordAuthenticationToken(token, token);
	        return getAuthenticationManager().authenticate(requestAuthentication);
	    }
	
	@Override
    protected void successfulAuthentication(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain, final Authentication authResult) throws IOException, ServletException {
        SecurityContextHolder.getContext().setAuthentication(authResult);
        chain.doFilter(request, response);
    }

}
