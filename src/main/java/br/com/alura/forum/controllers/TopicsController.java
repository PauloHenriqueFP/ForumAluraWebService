package br.com.alura.forum.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum.controllers.dto.TopicDTO;
import br.com.alura.forum.controllers.dto.TopicDetailsDTO;
import br.com.alura.forum.controllers.form.TopicForm;
import br.com.alura.forum.models.Course;
import br.com.alura.forum.models.Topic;
import br.com.alura.forum.repository.CourseRepository;
import br.com.alura.forum.repository.TopicRepository;

@RestController
@RequestMapping("/topics")
public class TopicsController {
	
	@Autowired
	private TopicRepository topicRepo;
	
	@Autowired
	private CourseRepository courseRepo;

	@GetMapping
	public List<TopicDTO> getTopics() {
		List<Topic> topics = topicRepo.findAll();
		return TopicDTO.convertList(topics);
	}
	
	@GetMapping("/{id}")
	public TopicDetailsDTO details(@PathVariable("id") Long id) {
		
		Topic topic = this.topicRepo.getOne(id);
		
		return new TopicDetailsDTO(topic);
	}
	
	@PostMapping
	public ResponseEntity<TopicDTO> create(@RequestBody @Valid TopicForm form, UriComponentsBuilder uriBuilder) {
		Topic topic = this.topicRepo.save(form.toTopic(courseRepo));
		
		URI uri = uriBuilder
				.path("/topics/{id}")
				.buildAndExpand(topic.getId())
				.toUri();
		
		return ResponseEntity.created(uri).body(new TopicDTO(topic));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<TopicDTO> updateTopic(@PathVariable(value = "id") Long id, @RequestBody @Valid TopicForm form) {
		/*
		 * Transform this logic into a service later !!
		 */
		Optional<Topic> topicOptional = this.topicRepo.findById(id);
		Optional<Course> courseOptional = this.courseRepo.findByName(form.getCourseName());
		
		if(courseOptional.isPresent() && topicOptional.isPresent()) {
			
			Topic topic = topicOptional.get();
			topic.setTitle(form.getTitle());
			topic.setMessage(form.getMessage());
			
			Course course = courseOptional.get();
			topic.setCourse(course);
			
			Topic updatedTopic = null;
			try {
				
				updatedTopic = this.topicRepo.save(topic);
				
			} catch(Exception e) {
				
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
				
			}
			
			return ResponseEntity.ok( new TopicDTO( updatedTopic ) );
			
		}
		
		return ResponseEntity.notFound().build();
	}
	
}
