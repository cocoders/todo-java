package com.cocoders.todo;

import com.cocoders.todo.domain.TodoListItem;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TodoApplicationTests {
	@LocalServerPort
	private int port;
	@Autowired
	private TestRestTemplate restTemplate;
	@Autowired
	private DataSource dataSource;

	@Before()
	public void setUp() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.execute("DELETE FROM todolist");
	}

	@Test
	public void shouldSaveTodoListItemsOnList() throws Exception {
		TodoListItem item1 = new TodoListItem(UUID.randomUUID(),"test");
		TodoListItem item2 = new TodoListItem(UUID.randomUUID(),"test2");

		HttpEntity<TodoListItem> request = new HttpEntity<TodoListItem>(item1);
		restTemplate.postForObject(
				"http://localhost:" + port + "/todo-list-item", request,
				TodoListItem.class);

		HttpEntity<TodoListItem> request2 = new HttpEntity<TodoListItem>(item2);
		restTemplate.postForObject(
				"http://localhost:" + port + "/todo-list-item", request2,
				TodoListItem.class);


		ResponseEntity<TodoListItem[]> response = restTemplate.getForEntity(
				"http://localhost:" + port + "/todo-list-item",
				TodoListItem[].class
		);

		List<TodoListItem> items = response != null ? Arrays.asList(response.getBody()) :
				Collections.emptyList();
		assertThat(response).isNotNull();
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(items.size()).isEqualTo(2);
		assertThat(items.get(0)).isEqualTo(item1);
		assertThat(items.get(1)).isEqualTo(item2);
	}

}
