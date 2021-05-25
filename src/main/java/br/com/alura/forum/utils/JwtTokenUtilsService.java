package br.com.alura.forum.utils;

import java.util.Date;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.alura.forum.controllers.dto.JwtTokenDTO;
import br.com.alura.forum.models.User;
import br.com.alura.forum.repository.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtTokenUtilsService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Value("${forum.alura.jwt.secret}")
	private String secret;
	
	public JwtTokenDTO generate(Authentication auth) {
		
		User userDetails = (User) auth.getPrincipal();
		
		String type = "Bearer";
		Date now = new Date();
		long oneDay = 86400000L;
		String token = Jwts.builder()
							.setIssuer("Forum Alura")
							.setSubject(userDetails.getId().toString())
							.setIssuedAt(now)
							.setExpiration( new Date(now.getTime() + oneDay) )
							.signWith(SignatureAlgorithm.HS256, secret)
							.compact();
		
		return new JwtTokenDTO(type, token);
	}

	public boolean isTokenValid(String token) {
		/*
		 * Take a look at Andrew Hughes JJWT article
		 * https://developer.okta.com/blog/2018/10/31/jwts-with-java
		 */
		
		try {
			
			//This line will throw an exception if it is not a signed JWS (as expected)
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
			
			return true;
		
		} catch (Exception e) {
			
			return false;

		}
		
	}
	
	public User getUserByToken(String token) {
		
		Claims jwtBody = Jwts.parser()
				.setSigningKey(secret)
				.parseClaimsJws(token)
				.getBody();
	
	
		long userID = Long.parseLong( jwtBody.getSubject() );
		Optional<User> user = userRepo.findById( userID );
		
		if(user.isPresent()) {
			return user.get();
		}
	
		throw new EntityNotFoundException("Usuario de id: " + userID + " não foi encontrado");

	}
	
}
