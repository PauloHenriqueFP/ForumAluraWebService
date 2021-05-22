package br.com.alura.forum.controllers.dto;

@SuppressWarnings("unused")
public class JwtTokenDTO {
	
	private String token;
	
	private String type;
	
	public void setToken(String token) {
		this.token = token;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
