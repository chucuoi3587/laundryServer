package nhan.natc.laundry.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableScheduling
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

//	@Resource(name = "userService")
//	private UserDetailsService mUserDetailService;
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		// TODO Auto-generated method stub
//		http.authorizeRequests().antMatchers("/api").permitAll().and().formLogin().defaultSuccessUrl("/welcome", true);
//	}
//	
//	@Bean
//	public DaoAuthenticationProvider authProvider() {
//		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//		provider.setUserDetailsService(mUserDetailService);
//		provider.setPasswordEncoder(passwordEncoder());
//		return provider;
//	}
//	
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return new BCryptPasswordEncoder(20);
//	}
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		// TODO Auto-generated method stub
//		auth.authenticationProvider(authProvider());
//	}
	
	private static final RequestMatcher PROTECTED_URLS = new OrRequestMatcher(
            new AntPathRequestMatcher("/**")
    );
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http.sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .exceptionHandling()
        .and()
        .authenticationProvider(tokenAuthenticationProvider())
		.addFilterBefore(authenticationFilter(),
						AbstractAuthenticationProcessingFilter.class /* BasicAuthenticationFilter.class */)
        .authorizeRequests()
        .antMatchers(HttpMethod.POST, "/user/register").permitAll()//.hasRole("ROLE_ANONYMOUS")
//        .requestMatchers(PROTECTED_URLS)        
        .anyRequest()        
        .authenticated()        
        .and()
        .csrf().disable()
        .formLogin().disable()
        .httpBasic().disable()
        .logout().disable();

//        http.addFilterBefore(new AuthenticationFilter(authenticationManager()), BasicAuthenticationFilter.class).
//                addFilterBefore(new ManagementEndpointAuthenticationFilter(authenticationManager()), BasicAuthenticationFilter.class);
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.authenticationProvider(tokenAuthenticationProvider());
	}
	
	@Override
		public void configure(WebSecurity web) throws Exception {
			// TODO Auto-generated method stub
			web.ignoring().antMatchers("/user/register");
		}
	
//	@Autowired
//	TokenService tokenService;
	
	@Bean
	public AuthenticationProvider tokenAuthenticationProvider() {
		return new TokenAuthenticationProvider();
	}
	
	@Bean
    public AuthenticationEntryPoint unauthorizedEntryPoint() {
        return (request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }
	
	@Bean
    AuthenticationFilter authenticationFilter() throws Exception {
      final AuthenticationFilter filter = new AuthenticationFilter(PROTECTED_URLS);
      filter.setAuthenticationManager(authenticationManager());
      //filter.setAuthenticationSuccessHandler(successHandler());
      return filter;
  }
}
