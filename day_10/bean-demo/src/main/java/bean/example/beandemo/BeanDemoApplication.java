package bean.example.beandemo;

import bean.example.beandemo.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Random;

//khai niem Bean
// la cac doi tuong duy nhat dc tao ra quan ly boi Spring IOC container
// su dung annocation hoac file cau hinh
// 1 danh dau len class 2 danh dau len methol
//class @Component @Controller @RestController
//methol @Bean => tra ve cac doi tuong (return Object)
// class phai danh dau @Configulation ben trong moi co the danh dau @Bean
//@SpringBootApplication da bao gom @Configulation

@SpringBootApplication
public class BeanDemoApplication {

	@Bean
	public Random random(){
		return new Random();
	}
	//dat ten bean trong dau ngoac kep de phan biet cac bean trung class
	@Bean("user1")
	public User user(){
		return new User();
	}

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(BeanDemoApplication.class, args);

		//User user = context.getBean(User.class);
		User user = context.getBean("user1" ,User.class);
		System.out.println(user.getName());
		user.sayHello();
		user.randomNumber();

		user.showVehicle();

		Random rd = context.getBean(Random.class);
		System.out.println(rd.nextInt(1000));
	}

}
