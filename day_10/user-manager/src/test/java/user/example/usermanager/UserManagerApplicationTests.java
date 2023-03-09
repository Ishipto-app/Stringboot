package user.example.usermanager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import user.example.usermanager.service.FileService;

@SpringBootTest
class UserManagerApplicationTests {
	@Autowired
	private FileService fileService;

	@Test
	void contextLoads() {
		System.out.println(fileService.getFileExtension("abc.png"));
	}

}
