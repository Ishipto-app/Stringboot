package demojpa.demojpa;

import static org.assertj.core.api.Assertions.*;

import com.github.javafaker.Faker;
import demojpa.demojpa.dto.UserDto;
import demojpa.demojpa.entity.Student;
import demojpa.demojpa.entity.User;
import demojpa.demojpa.projection.UserProjection;
import demojpa.demojpa.repository.StudentRepository;
import demojpa.demojpa.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


import java.sql.SQLException;
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
	@Autowired
	private StudentRepository studentRepository;
//
//	@Test
//	@Rollback(value = false)
//	void save_user(){
//		Faker faker = new Faker();
//		for (int i = 0; i < 30; i++) {
//			User user = User.builder()
//					.name(faker.name().fullName())
//					.email(faker.internet().emailAddress())
//					.password("111")
//					.build();
//			userRepository.save(user);
//		}
//	}
//	@Test
//	@Rollback(value = false)
//	void save_student(){
//		Faker faker = new Faker();
//		for (int i = 0; i < 30; i++) {
//			Student student = Student.builder()
//					.name(faker.name().fullName())
//					.age(faker.number().numberBetween(18, 50))
//					.email(faker.internet().emailAddress())
//					.build();
//			studentRepository.save(student);
//		}
//	}
//
//	@Test
//	void get_all_user() {
//		List<User> users = userRepository.findAll();
//		users.forEach(System.out::println);
//	}
//
//	@Test
//	void get_by_id() {
//		Optional<User> userOptional = userRepository.findById((1));
//		if(userOptional.isPresent()){
//			System.out.println(userOptional.get());
//		} else {
//			throw new RuntimeException("not found user");
//		}
//	}
//	@Test
//	@Rollback(value = false)
//	void update_user() {
//		Optional<User> userOptional = userRepository.findById((1));
//		if(userOptional.isPresent()){
//			User user = userOptional.get();
//			user.setName("New name");
//			user.setEmail("abc@gmail.com");
//			userRepository.save(user);
//		} else {
//			throw new RuntimeException("not found user");
//		}
//	}
//
//	@Test
//	void delete_user() {
//		//theo id
//		userRepository.deleteById(2);
//
//		//theo object
//		Optional<User> userOptional = userRepository.findById(3);
//		if(userOptional.isPresent()){
//			User user = userOptional.get();
//			userRepository.delete(user);
//		} else {
//			throw new RuntimeException("Not found user");
//		}
//	}
//
//	@Test
//	void findUserDtoByName() {
//		List<UserDto> userDtos = userRepository.findUserDtoByName("New name");
//
//		userDtos.forEach(System.out::println);
//	}
//	@Test
//	void getUserDtoUsingNativeQuery() {
//		List<UserDto> userDtos = userRepository.getUserDtoUsingNativeQueryRp();
//		userDtos.forEach(System.out::println);
//	}
//
//	@Test
//	void findUserProjectionByEmail(){
//		UserProjection userProjection = userRepository.findUserProjectionByEmail("pattie.weimann@gmail.com");
//		System.out.println(userProjection.getId() + "-" + userProjection.getName() + "-" + userProjection.getEmail());
//	}
//
//	@Test
//	void sort_user(){
//		List<User> users = userRepository.findAll(Sort.by("name").descending().and(Sort.by("email")));
//		users.forEach(System.out::println);
//	}
//
//	@Test
//	void pagination_user(){
////		Page<User> page = userRepository.findAll(PageRequest.of(1,10));
////		page.getContent().forEach(System.out::println);
//
//	}
//	@Test
//	void pagination_student(){
//		//phan trang su dung (method query, JPQR)
//		//tim kiem theo ten contain
//		Page<Student> page =  studentRepository.findByNameContainsIgnoreCase("a", PageRequest.of(2, 3));
//		page.getContent().forEach(System.out::println);
//		//phan trang su dung (Native query)
//
//		//unit text
//		assertThat(page.getContent()).isNotNull();
//		assertThat(page.getContent().size()).isEqualTo(3);
//		assertThat(page.getContent().get(0)).isEqualTo("Arthur Swaniawski");
//		assertThat(page.getContent().get(0)).isInstanceOf(Student.class);
//		assertThat(page.getContent()).matches(students -> students.size() > 0);
//	}
//	@Test
//	void pagination_student2(){
//		Page<Student> page = studentRepository.findByNameContainsIgnoreCase_Native("a", PageRequest.of(2, 3));
//		page.getContent().forEach(System.out::println);
//	}
//
//	@Test
//	//de trang la rollback all neu ko thi chi roll khi co cac loi trong danh sach
//	@Transactional(rollbackFor = {SQLException.class, IllegalArgumentException.class})
//	void save_students(){
//		Student std1 = Student.builder()
//				.name("nguyen van A")
//				.age(24)
//				.email("a@gmail.com")
//				.build();
//		studentRepository.save(std1);
//		Student std2 = Student.builder()
//				.name("nguyen van B")
//				.age(23)
//				.email("a@gmail.com")
//				.build();
//		studentRepository.save(std2);
//
//	}

	@Test
	@Rollback(value = false)
	void updateName(){
		studentRepository.updateStudentName(1, "AAA");
	}
}
