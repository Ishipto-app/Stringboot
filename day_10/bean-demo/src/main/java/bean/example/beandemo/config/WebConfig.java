package bean.example.beandemo.config;


import bean.example.beandemo.model.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebConfig {
    @Bean("user2")
    public User user(){
        System.out.println("user2");
        return null;
    }
}
