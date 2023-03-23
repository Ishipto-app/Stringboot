package user.example.usermanager;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import user.example.usermanager.entity.User;
import user.example.usermanager.repository.UserRepository;
import user.example.usermanager.service.FileService;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserTests {
	@Autowired
	private UserRepository userRepository;

	@Test
	@Rollback(value = false)
	void save_user() {
		Faker faker = new Faker();
		for (int i = 0; i < 30; i++) {
			User user = User.builder()
					.name(faker.name().fullName())
					.email(faker.internet().emailAddress())
					.phone(faker.phoneNumber().phoneNumber())
					.address(faker.address().fullAddress())
					.avatar(null)
					.password("111")
					.build();

			userRepository.save(user);
		}
	}

}
