package br.com.alura.forum.controllers.form;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.alura.forum.models.Course;
import br.com.alura.forum.models.Topic;
import br.com.alura.forum.repository.CourseRepository;

public class TopicForm {
	
	@NotBlank @Length(min = 5)
	private String title;
	
	@NotBlank @Length(min = 10)
	private String message;
	
	@NotBlank
	private String courseName;
	
	public Topic toTopic(CourseRepository courseRepo) {
		
		Course course = courseRepo.findByName(this.courseName)
				.orElseThrow(() -> new RuntimeException("Course with name: " + this.courseName + " was not found!"));
		
		Topic topic = new Topic();
		topic.setTitle(this.title);
		topic.setMessage(this.message);
		topic.setCourse(course);
		
		return topic;
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
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
		
}
