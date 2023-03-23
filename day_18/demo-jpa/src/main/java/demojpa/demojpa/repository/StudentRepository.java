package demojpa.demojpa.repository;

import demojpa.demojpa.entity.Student;
import demojpa.demojpa.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    //su dung method query
    Page<Student> findByNameContainsIgnoreCase(String name, Pageable pageable);

    //su dung jpql (khong ho tro limit  -> khong thuc hien duoc)
    //Page<Student> findByNameContainsIgnoreCase_JPQL(String name, Pageable pageable);

    //Su dung native query
    @Query(
            value="select * from student s where upper(s.name) like upper(concat('%', ?1, '%'))",
            countQuery = "select count(s.id) from student s where upper(s.name) like upper(concat('%', ?1, '%'))",
            nativeQuery = true
    )
    Page<Student> findByNameContainsIgnoreCase_Native(String name, Pageable pageable);
}