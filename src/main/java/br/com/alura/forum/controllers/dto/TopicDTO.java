package br.com.alura.forum.controllers.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.alura.forum.models.Topic;

public class TopicDTO {
	
	private Long id;
	private String title;
	private String message;
	private LocalDateTime openingDate;
	
	public TopicDTO(Topic topic) {
		this.id = topic.getId();
		this.title = topic.getTitle();
		this.message = topic.getMessage();
		this.openingDate = topic.getOpeningDate();
	}
	
	public static List<TopicDTO> convertList(List<Topic> topics) {
		return topics.stream().map(TopicDTO::new).collect(Collectors.toList());
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

	public LocalDateTime getOpeningDate() {
		return openingDate;
	}
	
	
}
