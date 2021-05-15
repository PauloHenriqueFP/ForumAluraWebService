package br.com.alura.forum.controllers.dto;

import java.time.LocalDateTime;

import br.com.alura.forum.models.TopicAnswer;

public class TopicAnswerDTO {

	private Long id;
	private String message;
	private String authorName;
	private LocalDateTime answerDate; 
	
	public TopicAnswerDTO(TopicAnswer answer) {
		this.id = answer.getId();
		this.message = answer.getMessage();
		this.authorName = answer.getAutor().getName();
		this.answerDate = answer.getAnswerDate();
	}

	public Long getId() {
		return id;
	}

	public String getMessage() {
		return message;
	}

	public String getAuthorName() {
		return authorName;
	}

	public LocalDateTime getAnswerDate() {
		return answerDate;
	}
		
}
