package com.example.demojparelationship;

import com.example.demojparelationship.one_to_one.IdentityCard;
import com.example.demojparelationship.one_to_one.IdentityCardRepository;
import com.example.demojparelationship.one_to_one.User;
import com.example.demojparelationship.one_to_one.UserRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class OneToOneTests {

    @Autowired
    private IdentityCardRepository identityCardRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    void save_user_card() {
        Faker faker = new Faker();
        for (int i = 0; i < 30; i++) {
            // Lưu card
            IdentityCard identityCard = IdentityCard.builder()
                    .code(String.valueOf(faker.number().numberBetween(1000, 9000)))
                    .build();
            identityCardRepository.save(identityCard);

            // Lưu user
            User user = User.builder()
                    .name(faker.name().fullName())
                    .identityCard(identityCard)
                    .build();
            userRepository.save(user);
        }
    }

    @Test
    void save_user_card_other() {
        // Lưu user
        User user = User.builder()
                .name("Bùi Hiên")
                .identityCard(
                        IdentityCard.builder()
                                .code("123456")
                                .build()
                )
                .build();
        userRepository.save(user);
    }

    @Test
    void delete_user() {
        userRepository.deleteById(1);
    }

    @Test
    void get_user() {
        User user = userRepository.findById(1)
                .orElseThrow(() -> {
                    throw new RuntimeException("Not found");
                });
    }
}
