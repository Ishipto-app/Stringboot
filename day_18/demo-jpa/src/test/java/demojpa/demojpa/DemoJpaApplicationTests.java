package demojpa.demojpa;

import com.github.javafaker.Faker;
import demojpa.demojpa.dto.UserDto;
import demojpa.demojpa.entity.User;
import demojpa.demojpa.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

//@SpringBootTest

// chi lam viec voi database in-memory : h2- database
// tao cao bean can thiet repository khong chay ca ung dung nhu @SpringBootTest
// muon su dung Mysql thi can cau hinh @DataJdbcTest
// DataJpaTest tu dong rollback database
@DataJpaTest
// cau hinh
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class DemoJpaApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	@Rollback(value = false)
	void save_user(){
		Faker faker = new Faker();
		for (int i = 0; i < 30; i++) {
			User user = User.builder()
					.name(faker.name().fullName())
					.email(faker.internet().emailAddress())
					.password("111")
					.build();
			userRepository.save(user);
		}
	}

	@Test
	void get_all_user() {
		List<User> users = userRepository.findAll();
		users.forEach(System.out::println);
	}

	@Test
	void get_by_id() {
		Optional<User> userOptional = userRepository.findById((1));
		if(userOptional.isPresent()){
			System.out.println(userOptional.get());
		} else {
			throw new RuntimeException("not found user");
		}
	}
	@Test
	@Rollback(value = false)
	void update_user() {
		Optional<User> userOptional = userRepository.findById((1));
		if(userOptional.isPresent()){
			User user = userOptional.get();
			user.setName("New name");
			user.setEmail("abc@gmail.com");
			userRepository.save(user);
		} else {
			throw new RuntimeException("not found user");
		}
	}

	@Test
	void delete_user() {
		//theo id
		userRepository.deleteById(2);

		//theo object
		Optional<User> userOptional = userRepository.findById(3);
		if(userOptional.isPresent()){
			User user = userOptional.get();
			userRepository.delete(user);
		} else {
			throw new RuntimeException("Not found user");
		}
	}

	@Test
	void findUserDtoByName() {
		List<UserDto> userDtos = userRepository.findUserDtoByName("New name");

		userDtos.forEach(System.out::println);
	}
}
