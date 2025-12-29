package unilu.jfe.hw2;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.RedisTemplate;

import unilu.jfe.hw2.entities.User;
import unilu.jfe.hw2.services.UserService;

@SpringBootApplication
public class Hw2Application implements CommandLineRunner{

	private final RedisTemplate<String, User> rt;


	public Hw2Application(RedisTemplate<String, User> rt){
		this.rt = rt;
	}

	public static void main(String[] args) {
		SpringApplication.run(Hw2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception{

		UserService us = new UserService(this.rt);

		System.out.println("Adding users....");
		us.createUser("Test1", "Test1@email.com");
		us.createUser("Test2", "Test2@email.com");
		us.createUser("Test3", "Test3@email.com");

		System.out.println("Printing all users....");
		List<User> allUsers = us.getAllUsers();
		for (User temp : allUsers){
			System.out.println(temp);
		}

		System.out.println("Getting first user by id....");
		String id = allUsers.get(0).getId();
		User foundUser = us.getUser(id);
		System.out.println(foundUser);
	}

}
