package demojpa.demojpa;


import com.github.javafaker.Faker;
import demojpa.demojpa.entity.Student;
import demojpa.demojpa.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class StudentTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    void save_student() {
        Faker faker = new Faker();
        for (int i = 0; i < 30; i++) {
            Student student = Student.builder()
                    .name(faker.name().fullName())
                    .email(faker.internet().emailAddress())
                    .age(faker.number().numberBetween(10, 60))
                    .build();

            studentRepository.save(student);
        }
    }

    // Phân trang sử dụng (Method query, JPQL)
    // Phân trang sử dụng (Native Query)
    // Câu hỏi : Tìm kiếm các student có chứa ký tự nào đó trong tên (Có phân trang)
    @Test
    void findStudentByName_Method() {
        Page<Student> page = studentRepository
                .findByNameContainsIgnoreCase("o", PageRequest.of(1,5));
        page.getContent().forEach(System.out::println);

//        Page<Student> page1 = studentRepository
//                .findByNameContainsIgnoreCase_NativeQuery("o", PageRequest.of(1,5));
//        page1.getContent().forEach(System.out::println);

        assertThat(page.getContent()).isNotNull();
        assertThat(page.getContent().size()).isEqualTo(5);
        assertThat(page.getContent().get(0).getName()).isEqualTo("Margorie Raynor");
        assertThat(page.getContent().get(0)).isInstanceOf(Student.class);
        assertThat(page.getContent()).matches(students -> students.size() > 0);
    }


//    @Test
//    void findStudentByName_Native() {
//        Page<Student> page = studentRepository
//                .findByNameContainsIgnoreCase_NativeQuery("o", PageRequest.of(0,5));
//        page.getContent().forEach(System.out::println);
//    }

    @Test
    @Rollback(value = false)
    void updateName(){
        studentRepository.updateStudentName(1, "AAA");
    }
}


