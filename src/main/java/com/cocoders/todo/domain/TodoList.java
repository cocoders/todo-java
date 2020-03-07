package com.cocoders.todo.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface TodoList extends JpaRepository<TodoListItem, UUID> {

}
