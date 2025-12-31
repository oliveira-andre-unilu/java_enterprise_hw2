package unilu.jfe.hw2.graphql.resolvers;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import unilu.jfe.hw2.entities.Feedback;
import unilu.jfe.hw2.entities.Tweet;
import unilu.jfe.hw2.entities.User;
import unilu.jfe.hw2.graphql.types.CreateUserInput;
import unilu.jfe.hw2.graphql.types.PostFeedbackInput;
import unilu.jfe.hw2.graphql.types.PostTweetInput;
import unilu.jfe.hw2.services.TweetService;
import unilu.jfe.hw2.services.UserService;

/**
 * Class responsible for implementing all mutations that will be linked to GraphQL
 * 
 * @author Andre Martins
 */
@Controller
public class MutationResolvers {
    // Dependencies
    private UserService us;
    private TweetService ts;

    // Constructors
    public MutationResolvers(UserService us, TweetService ts) {
        this.us = us;
        this.ts = ts;
    }

    // User mutations
    @MutationMapping
    public User createUser(@Argument CreateUserInput input) {
        return us.createUser(input.getUserName(), input.getEmail());
    }

    // Tweet mutation
    @MutationMapping
    public Tweet postTweet(@Argument PostTweetInput input) {
        return ts.postTweet(input.getUserId(), input.getTitle(), input.getMessage(), input.getValidityLength());
    }

    @MutationMapping
    public Feedback postFeedback(@Argument PostFeedbackInput input) {
        return ts.postFeedback(input.getPostId(), input.getUserId(), input.getContent());
    }
}
