package br.com.alura.forum.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.forum.controllers.dto.TopicDTO;
import br.com.alura.forum.models.Topic;

@RestController
@RequestMapping("/topics")
public class TopicsController {

	@GetMapping
	public List<TopicDTO> getTopics() {
		Topic topic = new Topic();
		topic.setTitle("Spring Rest");
		topic.setMessage("How can I learn Spring Rest?");
		
		return TopicDTO.convertList(Arrays.asList(topic, topic));
	}
	
}
