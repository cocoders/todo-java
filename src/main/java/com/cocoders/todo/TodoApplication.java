package com.cocoders.todo;

import com.cocoders.todo.domain.TodoList;
import com.cocoders.todo.domain.TodoListItem;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class TodoApplication {
	private final TodoList repository;

	public TodoApplication(TodoList repository) {
		this.repository = repository;
	}

	@GetMapping("/todo-list-item")
	public List<TodoListItem> getItems() {
		return repository.findAll();
	}
	@PostMapping("/todo-list-item")
	public TodoListItem saveItem(@RequestBody TodoListItem listItem) {
		return this.repository.save(listItem);
	}
	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}
}
