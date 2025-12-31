package unilu.jfe.hw2.graphql.resolvers;

import java.util.List;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import unilu.jfe.hw2.entities.Tweet;
import unilu.jfe.hw2.entities.User;
import unilu.jfe.hw2.services.TweetService;
import unilu.jfe.hw2.services.UserService;

/**
 * Class responsible for implementing all queries that will be used in GraphQL.
 * 
 * @author Andre Martins
 */
@Controller
public class QueryResolvers {

    // Dependencies
    private UserService us;
    private TweetService ts;

    // Constructors
    public QueryResolvers(UserService us, TweetService ts) {
        this.us = us;
        this.ts = ts;
    }

    // User queries
    @QueryMapping
    public User user(@Argument String id) {
        return us.getUser(id);
    }

    @QueryMapping
    public List<User> users() {
        return us.getAllUsers();
    }

    // Tweet queries
    @QueryMapping
    public List<Tweet> searchFor(@Argument String word) {
        return ts.searchFor(word);
    }

    @QueryMapping
    public Tweet tweet(@Argument String id) {
        return ts.getPost(id);
    }
}
