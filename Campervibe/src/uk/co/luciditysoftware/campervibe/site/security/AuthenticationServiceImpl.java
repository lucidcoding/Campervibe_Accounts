package uk.co.luciditysoftware.campervibe.site.security;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Collection;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import uk.co.luciditysoftware.campervibe.domain.entities.User;
import uk.co.luciditysoftware.campervibe.domain.repositorycontracts.UserRepository;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	private SessionFactory sessionFactory;
	private UserRepository userRepository;
	
	//http://javainsimpleway.com/spring/spring-security-using-custom-authentication-provider/
		
    //private static final Logger log = LogManager.getLogger();
    @SuppressWarnings("unused")
	private static final SecureRandom RANDOM;
    @SuppressWarnings("unused")
	private static final int HASHING_ROUNDS = 10;
	
	@Inject 
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

    @Inject
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
    static
    {
        try
        {
            RANDOM = SecureRandom.getInstanceStrong();
        }
        catch(NoSuchAlgorithmException e)
        {
            throw new IllegalStateException(e);
        }
    }


    //@Inject UserRepository userRepository;

    @Override
    //@Transactional
    public Authentication  authenticate(Authentication authentication)
    {
        UsernamePasswordAuthenticationToken credentials =
                (UsernamePasswordAuthenticationToken)authentication;
        String username = credentials.getPrincipal().toString();
        String password = credentials.getCredentials().toString();
        credentials.eraseCredentials();

        /*UserPrincipal principal = this.userRepository.getByUsername(username);
        if(principal == null)
        {
            log.warn("Authentication failed for non-existent user {}.", username);
            return null;
        }

        if(!BCrypt.checkpw(
                password,
                new String(principal.getPassword(), StandardCharsets.UTF_8)
        ))
        {
            log.warn("Authentication failed for user {}.", username);
            return null;
        }

        principal.setAuthenticated(true);
        log.debug("User {} successfully authenticated.", username);

        return principal;*/

        User user = null;
        try {
			Session session = sessionFactory.getCurrentSession();
			Transaction transaction = session.beginTransaction();
	        user = userRepository.getByUsername(username);
			transaction.commit();
        } catch (Exception ex) {
        	throw ex;
        }
		
        if (user == null) {
        	return null;
        }
        
    	if (password.equals(user.getPassword())) {
    		final String currentUsername = user.getUsername();
    		UserPrincipal userPrincipal = new UserPrincipal() {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				{
    				setUsername(currentUsername);
    			}
    		};
    		
            Collection<? extends GrantedAuthority> authorities = userPrincipal.getAuthorities();
            
            return new UsernamePasswordAuthenticationToken(userPrincipal, password, authorities);
    	} else {
    		return null;
    	}	
    }

    @Override
    public boolean supports(Class<?> c)
    {
        return c == UsernamePasswordAuthenticationToken.class;
    }

    @Override
    //@Transactional
    public void saveUser(UserPrincipal principal, String newPassword)
    {
        /*if(newPassword != null && newPassword.length() > 0)
        {
            String salt = BCrypt.gensalt(HASHING_ROUNDS, RANDOM);
            principal.setPassword(BCrypt.hashpw(newPassword, salt).getBytes());
        }

        this.userRepository.save(principal);*/
    }
}
