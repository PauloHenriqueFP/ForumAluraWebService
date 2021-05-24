package br.com.alura.forum.utils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;

import br.com.alura.forum.controllers.dto.JwtTokenDTO;
import br.com.alura.forum.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class TokenGenerationUtil {
	
	@Value("${forum.alura.jwt.secret}")
	private static String secret;
	
	public static JwtTokenDTO generate(Authentication auth) {
		
		User userDetails = (User) auth.getPrincipal();
		
		String type = "Bearer";
		Date now = new Date();
		long oneDay = 86400000L;
		String token = Jwts.builder()
							.setIssuer("Forum Alura")
							.setSubject(userDetails.getId().toString())
							.setIssuedAt(now)
							.setExpiration( new Date(now.getTime() + oneDay) )
							.signWith(SignatureAlgorithm.HS256, "pass")
							.compact();
		
		return new JwtTokenDTO(type, token);
	}
	
}
