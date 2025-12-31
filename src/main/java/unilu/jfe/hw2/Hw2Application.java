package unilu.jfe.hw2;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;

import unilu.jfe.hw2.entities.Feedback;
import unilu.jfe.hw2.entities.Tweet;
import unilu.jfe.hw2.entities.User;
import unilu.jfe.hw2.services.TweetService;
import unilu.jfe.hw2.services.UserService;

/**
 * Main Spring application class. Responsible for starting the main server as
 * well as to initialize the data.
 * 
 * @author Andre Martins
 */
@SpringBootApplication
public class Hw2Application {

    public static void main(String[] args) {
        SpringApplication.run(Hw2Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserService userService, TweetService tweetService) {
        return args -> {
            System.out.println("=== Initializing Test Data ===");

            System.out.println("Adding users....");
            User user1 = userService.createUser("Test1", "Test1@email.com");
            User user2 = userService.createUser("Test2", "Test2@email.com");
            User user3 = userService.createUser("Test3", "Test3@email.com");

            System.out.println("Printing all users....");
            userService.getAllUsers().forEach(System.out::println);

            System.out.println("Getting first user by id....");
            User foundUser = userService.getUser(user1.getId());
            System.out.println(foundUser);

            System.out.println("Adding Tweets...");
            Tweet tweet1 = tweetService.postTweet(user1.getId(), "First tweet", "Posting tweets is awesome", 30);
            System.out.println(tweet1);

            Tweet tweet2 = tweetService.postTweet(user2.getId(), "Second tweet",
                    "Creating some new posts with other opinion", 30);
            System.out.println(tweet2);

            System.out.println("Adding feedback....");
            Feedback f1 = tweetService.postFeedback(tweet1.getId(), user3.getId(), "I really like your opinion");
            System.out.println(f1);

            Tweet updatedTweet = tweetService.getPost(tweet1.getId());
            System.out.println(updatedTweet);

            System.out.println("=== Test Data Initialization Complete ===");
            System.out.println("GraphQL available at: http://localhost:8080/graphql");
            System.out.println("GraphiQL UI available at: http://localhost:8080/graphiql");
        };
    }

}
