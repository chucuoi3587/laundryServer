package nhan.natc.laundry.service;

import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import nhan.natc.laundry.data.local.TokenDAO;
import nhan.natc.laundry.data.repository.TokenRepository;

@Service("tokenService")
public class TokenService {
	
	private static final Logger logger = LoggerFactory.getLogger(TokenService.class);
    private static Cache restApiAuthTokenCache;// = CacheManager.getInstance().getCache("restApiAuthTokenCache");
    public static final int HALF_AN_HOUR_IN_MILLISECONDS = 30 * 60 * 1000;

    public TokenService() {
    	if (restApiAuthTokenCache == null) {
    		CacheManager.getInstance().addCache("restApiAuthTokenCache");
    		restApiAuthTokenCache = CacheManager.getInstance().getCache("restApiAuthTokenCache");
    	}
    }
    
    @Scheduled(fixedRate = HALF_AN_HOUR_IN_MILLISECONDS)
    public void evictExpiredTokens() {
        logger.info("Evicting expired tokens");
        restApiAuthTokenCache.evictExpiredElements();
    }
    
	@Autowired
	private TokenRepository tokenRepository;
	
	public Optional<Calendar> findExpiredTimeByToken(String token) {
		Optional<Long> expiredTime = tokenRepository.findExpiredTimeByToken(token);
		if (expiredTime.isPresent()) {
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(expiredTime.get());
			return expiredTime.of(cal);
		}
		return Optional.empty();
	}
	
	public TokenDAO saveToken(TokenDAO token) {
		return tokenRepository.save(token);
	}
	
	public void storeToken(String token, Authentication authentication) {
		restApiAuthTokenCache.put(new Element(token, authentication));
	}
	
	public Authentication retrieve(String token) {
		return (Authentication) restApiAuthTokenCache.get(token).getObjectValue();
	}
	
	public boolean contains(String token) {
		return restApiAuthTokenCache.get(token) != null;
	}
	
	public String generateNewToken() {
        return UUID.randomUUID().toString();
    }
	
	public Optional<TokenDAO> findTokenByToken(String token) {
		return tokenRepository.findUserByToken(token);
	}
}
