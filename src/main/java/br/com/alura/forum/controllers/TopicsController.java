package br.com.alura.forum.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.controllers.dto.TopicDTO;
import br.com.alura.forum.models.Topic;
import br.com.alura.forum.repository.TopicRepository;

@RestController
@RequestMapping("/topics")
public class TopicsController {
	
	@Autowired
	private TopicRepository topicRepo;

	@GetMapping
	public List<TopicDTO> getTopics() {
		List<Topic> topics = topicRepo.findAll();
		return TopicDTO.convertList(topics);
	}
	
}
