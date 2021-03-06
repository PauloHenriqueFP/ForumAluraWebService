package br.com.alura.forum.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Topic {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String title;
	
	private String message;
	
	private LocalDateTime openingDate = LocalDateTime.now();
	
	@Enumerated(EnumType.STRING)
	private TopicStatus status = TopicStatus.NOT_ANSWERED;
	
	@ManyToOne
	private User author;
	
	@ManyToOne
	private Course course;
	
	@OneToMany(mappedBy = "topic", cascade = CascadeType.ALL)
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
