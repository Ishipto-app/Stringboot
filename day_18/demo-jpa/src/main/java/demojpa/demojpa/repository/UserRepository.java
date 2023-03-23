package demojpa.demojpa.repository;

import demojpa.demojpa.dto.UserDto;
import demojpa.demojpa.entity.User;
import demojpa.demojpa.projection.UserProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// thao tac voi bang nao
// kieu du lieu khoa chinh
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    //userRepository.save luu du lieu user (create update)
    //userRepository.findAll
    //userRepository.findById
    //userRepository.delete // xoa

    //1. method query (creation query): dat ten metho theo dung chuan duoc quy dinh
    //Spring boot => Spring data jpa
    List<User> findByName(String name);
    List<User> findByNameStartingWith(String prefix);
    List<User> findByNameContainingIgnoreCase(String name);
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    long countByName(String name);
    List<User> findByNameOrderByEmailDesc(String name);

    List<User> findByPasswordAndNameIgnoreCaseOrderByNameAsc(String password, String name);

    long countDistinctByEmailContains(String email);

    //2. JPQL query (JPA query language) : query dua theo entity (theo ten entity User)
    @Query("select u from User u where u.name = ?1")
    List<User> findByNameJPQL(String name);

    @Query("select u from User u where u.email = :email")
    Optional<User> findByEmailJPQL(@Param("email") String email);

//    @Query("select u from User u where u.name = ?1 limit 5")
//    List<User> findTop5ByNameJPQL(String name);

    //3. Native query : viet cau lenh native dua theo database (theo ten table user)
    @Query(nativeQuery = true, value="select * from user u where u.name = ?1")
    List<User> findByName_NativeQuery(String name);

    @Query(nativeQuery = true, value="select u from user u where u.email = :email")
    Optional<User> findByEmail_NativeQuery(@Param("email") String email);

    @Query(nativeQuery = true, value="select u from User u where u.name = ?1 limit 5")
    List<User> findTop5ByName_NativeQuery(String name);


    //Query DTO
    //1. query entity => mapper sang DTO

    //2. su dung JPQL
    @Query("select new demojpa.demojpa.dto.UserDto(u.id, u.name, u.email) from User u where u.name = ?1")
    List<UserDto> findUserDtoByName(String name);

    //3. su dung Native Query
    @Query(nativeQuery = true, name = "getUserDtoUsingNativeQuery")
    List<UserDto> getUserDtoUsingNativeQueryRp();

    //4. su dung Projection (inteface)
    UserProjection findUserProjectionByEmail(String email);

    //sap xep va phan trang
}
