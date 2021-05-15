package br.com.alura.forum.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class TopicAnswer {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String message;
	
	@ManyToOne
	private Topic topic;
	
	private LocalDateTime answerDate  = LocalDateTime.now();
	
	@ManyToOne
	private User author;
	
	private Boolean solution = false;
	
	public TopicAnswer() {
		// TODO Auto-generated constructor stub
	}
	
	public TopicAnswer(String message, Topic topic, LocalDateTime answerDate, User author, Boolean solution) {
		super();
		this.message = message;
		this.topic = topic;
		this.answerDate = answerDate;
		this.author = author;
		this.solution = solution;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public LocalDateTime getAnswerDate() {
		return answerDate;
	}

	public void setAnswerDate(LocalDateTime answerDate) {
		this.answerDate = answerDate;
	}

	public User getAutor() {
		return author;
	}

	public void setAutor(User author) {
		this.author = author;
	}

	public Boolean getSolution() {
		return solution;
	}

	public void setSolution(Boolean solution) {
		this.solution = solution;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TopicAnswer other = (TopicAnswer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
