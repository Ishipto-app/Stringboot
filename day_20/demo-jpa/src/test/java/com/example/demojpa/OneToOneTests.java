package com.example.demojpa;

import com.example.demojpa.one_to_one.IdentityCard;
import com.example.demojpa.one_to_one.User;
import com.example.demojpa.responsitory.IdentityCardRepository;
import com.example.demojpa.responsitory.UserRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.Id;
import javax.transaction.Transactional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class OneToOneTests {

    @Autowired
    private IdentityCardRepository identityCardRepository;

    @Autowired
    private UserRepository userRepository;

//    @Test
//    @Transactional
//    void save_user_card() {
//        Faker faker = new Faker();
//
//        User user = User.builder()
//                .name(faker.name().fullName())
//                .identityCard(
//                        IdentityCard.builder()
//                                .name("123456")
//                                .build())
//                .build();
//
//        userRepository.save(user);
//    }
//    @Test
//    void save_one_user_card() {
//        Faker faker = new Faker();
//            IdentityCard identityCard = IdentityCard.builder()
//                    .name(faker.name().fullName())
//                    .build();
//
//            identityCardRepository.save(identityCard);
//
//
//            User user = User.builder()
//                    .name(faker.name().fullName())
//                    .identityCard(identityCard)
//                    .build();
//
//            userRepository.save(user);
//        }
//    }
//    @Test
//    void get_user() {
//        User user = userRepository.findById(1)
//                .orElseThrow(() -> {
//                    throw new RuntimeException("hot found");
//                });
//    }
//
//    @Test
//    void delete_user(){
//        userRepository.deleteById(1);
//    }
}
