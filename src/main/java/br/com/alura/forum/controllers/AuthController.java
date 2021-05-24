package br.com.alura.forum.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.controllers.dto.JwtTokenDTO;
import br.com.alura.forum.controllers.form.LoginForm;
import br.com.alura.forum.utils.TokenGenerationUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authManager; // Bean provided in SecurityConfig.java
	
	@Autowired
	private TokenGenerationUtil tokenUtil;

	@PostMapping
	public ResponseEntity<JwtTokenDTO> authenticate(@RequestBody @Valid LoginForm loginForm) {
		
		var userCredentials = new UsernamePasswordAuthenticationToken(loginForm.getEmail(), loginForm.getPassword());
		
		try {
			
			Authentication auth = authManager.authenticate(userCredentials);
			JwtTokenDTO token = tokenUtil.generate(auth);
			
			return ResponseEntity.ok(token);

		} catch (AuthenticationException e) {
			
			return ResponseEntity.badRequest().build();
		
		}
		
	}
	
}
