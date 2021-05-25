package br.com.alura.forum.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.alura.forum.models.User;
import br.com.alura.forum.utils.JwtTokenUtilsService;

public class JwtAuthFilter extends OncePerRequestFilter {  
	
	private JwtTokenUtilsService tokenUtil;
	
	public JwtAuthFilter(JwtTokenUtilsService tokenGenerationUtil) {
		this.tokenUtil = tokenGenerationUtil;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
				
		String token = getToken(request);
		
		boolean isValid = tokenUtil.isTokenValid(token);
		if(isValid) {
			authenticateUser(token);	
		}
		
		/*
		 * If token is not valid continue the filter. A 403 error will be sent to the user by Spring Security
		 */
		filterChain.doFilter(request, response); 
	}

	private void authenticateUser(String token) {
		
		User user = tokenUtil.getUserByToken(token);
		
		var userCredentials = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
		
		SecurityContextHolder
				.getContext()
				.setAuthentication(userCredentials);
		
	}

	private String getToken(HttpServletRequest request) {
		String fullToken = request.getHeader("Authorization");
		
		// Validating token
		if ( fullToken != null && fullToken.startsWith("Bearer ") && ( !fullToken.isBlank() ) ) {
			
			return fullToken.substring(7, fullToken.length());
		}
		
		return null;
		
	}

}
