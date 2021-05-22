package br.com.alura.forum.controllers.form;

import javax.validation.constraints.NotBlank;

public class LoginForm {
	
	@NotBlank
	private String email;
	
	@NotBlank
	private String password;
	
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
		
}
