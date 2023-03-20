package hieu.example.todolist;

import com.github.javafaker.Faker;
import hieu.example.todolist.entity.Todo;
import hieu.example.todolist.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
// cau hinh
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TodoListApplicationTests {

	@Autowired
	private TodoRepository todoRepository;

	@Test
	@Rollback(value = false)
	void update_todo() {
		Optional<Todo> todoOptional = todoRepository.findById((1));
		if(todoOptional.isPresent()){
			Todo todo = todoOptional.get();
			todo.setTitle("New title");
			todoRepository.save(todo);
		} else {
			throw new RuntimeException("not found user");
		}
	}

	@Test
	@Rollback(value = false)
	void create_todo() {
			todoRepository.save(new Todo(null, "title1", false));
	}

}
