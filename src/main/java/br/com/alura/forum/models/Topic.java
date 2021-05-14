package br.com.alura.forum.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Topic {

	private Long id;
	
	private String title;
	
	private String message;
	
	private LocalDateTime openingDate = LocalDateTime.now();
	
	private TopicStatus status = TopicStatus.NOT_ANSWERED;
	
	private User author;
	
	private Course course;
	
	private List<TopicAnswer> answers = new ArrayList<>();
	
	public Topic() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(LocalDateTime openingDate) {
		this.openingDate = openingDate;
	}

	public TopicStatus getStatus() {
		return status;
	}

	public void setStatus(TopicStatus status) {
		this.status = status;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public List<TopicAnswer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<TopicAnswer> answers) {
		this.answers = answers;
	}
	
}
