package br.com.alura.forum.controllers.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.forum.models.Topic;
import br.com.alura.forum.models.TopicStatus;

public class TopicDetailsDTO {
	
	private Long id;
	private String title;
	private String message;
	private String authorName;
	private LocalDateTime openingDate;
	private TopicStatus status;
	private List<TopicAnswerDTO> answers = new ArrayList<>();
	
	public TopicDetailsDTO(Topic topic) {
		this.id = topic.getId();
		this.title = topic.getTitle();
		this.message = topic.getMessage();
		this.authorName = topic.getAuthor().getName();
		this.openingDate = topic.getOpeningDate();
		this.status = topic.getStatus();
		
		this.answers = topic.getAnswers().stream()
				.map(TopicAnswerDTO::new)
				.collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getMessage() {
		return message;
	}

	public String getAuthorName() {
		return authorName;
	}

	public LocalDateTime getOpeningDate() {
		return openingDate;
	}

	public TopicStatus getStatus() {
		return status;
	}

	public List<TopicAnswerDTO> getAnswers() {
		return answers;
	}
	
}
