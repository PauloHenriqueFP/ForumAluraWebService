package br.com.alura.forum.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.alura.forum.controllers.dto.TopicDTO;
import br.com.alura.forum.controllers.form.TopicForm;
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
	
	@PostMapping
	public ResponseEntity<TopicDTO> create(@RequestBody @Valid TopicForm form, UriComponentsBuilder uriBuilder) {
		Topic topic = this.topicRepo.save(form.toTopic(courseRepo));
		
		URI uri = uriBuilder
				.path("/topics/{id}")
				.buildAndExpand(topic.getId())
				.toUri();
		
		return ResponseEntity.created(uri).body(new TopicDTO(topic));
	}
}
