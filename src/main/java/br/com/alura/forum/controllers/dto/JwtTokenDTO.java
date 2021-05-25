package br.com.alura.forum.controllers.dto;

@SuppressWarnings("unused")
public class JwtTokenDTO {
	
	private String type;

	private String token;
		
	public JwtTokenDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public JwtTokenDTO(String type, String token) {
		this.type = type;
		this.token = token;
	}
	
	public String getToken() {
		return token;
	}
	
	public String getType() {
		return type;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
